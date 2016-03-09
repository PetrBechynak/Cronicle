package wsdl;

import connector.HbaseConn;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by pbechynak on 7.3.2016.
 */
public class WeatherHistory extends ArrayList<WeatherHistory.Row> {


    public class Row{
        String time;
        String summary;
        String temperature;
        String apparenttemperature;
        String humidity;
        String cloudcover;

        public Row(String time, String summary, String temperature, String apparenttemperature, String humidity, String cloudcover){
            this.time=time;
            this.summary=summary;
            this.temperature=temperature;
            this.apparenttemperature=apparenttemperature;
            this.humidity=humidity;
            this.cloudcover=cloudcover;
        }
        public String getTime() { return time; }
        public String getSummary() { return summary; }
        public String getTemperature() { return temperature; }
        public String getApparenttemperature() { return apparenttemperature; }
        public String getHumidity() { return humidity; }
        public String getCloudcover() { return cloudcover; }
    }

    public WeatherHistory() {

    }

    public void loadDataBetween(String startdate, String enddate){
        String time;
        String summary;
        String temperature;
        String apparenttemperature;
        String humidity;
        String cloudcover;

        HbaseConn hbaseConn = null;
        try {
            hbaseConn = new HbaseConn();
            HTable table = new HTable(hbaseConn.config, "Cr");

            Scan scan = new Scan();
            scan.addColumn(Bytes.toBytes("weather"), Bytes.toBytes("time"));
            scan.addColumn(Bytes.toBytes("weather"), Bytes.toBytes("summary"));
            scan.addColumn(Bytes.toBytes("weather"), Bytes.toBytes("temperature"));
            scan.addColumn(Bytes.toBytes("weather"), Bytes.toBytes("apparenttemperature"));
            scan.addColumn(Bytes.toBytes("weather"), Bytes.toBytes("humidity"));
            scan.addColumn(Bytes.toBytes("weather"), Bytes.toBytes("cloudcover"));
            scan.setStartRow(Bytes.toBytes("0"));
            scan.setStopRow(Bytes.toBytes("99999999999999"));

            ResultScanner scanner = table.getScanner(scan);
            try {
                // Scanners return Result instances.
                // Now, for the actual iteration. One way is to use a while loop like so:
                for (Result result = scanner.next(); result != null; result = scanner.next()) {
                    // print out the row we found and the columns we were looking for
                    System.out.println("Found row: " + result);
                    byte [] bTime = result.getRow();
                    byte [] bSummary = result.getValue(Bytes.toBytes("weather"),Bytes.toBytes("summary"));
                    byte [] bTemperature = result.getValue(Bytes.toBytes("weather"),Bytes.toBytes("temperature"));
                    byte [] bApparenttemperature = result.getValue(Bytes.toBytes("weather"),Bytes.toBytes("apparenttemperature"));
                    byte [] bHumidity = result.getValue(Bytes.toBytes("weather"),Bytes.toBytes("humidity"));
                    byte [] bCloudcover = result.getValue(Bytes.toBytes("weather"),Bytes.toBytes("cloudcover"));
                    // Printing the values
                    long unixSeconds = Long.parseLong(Bytes.toString(bTime));
                    Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
                    time = sdf.format(date);
                    summary = Bytes.toString(bSummary);
                    temperature = Bytes.toString(bTemperature);
                    apparenttemperature = Bytes.toString(bApparenttemperature);
                    humidity = Bytes.toString(bHumidity);
                    cloudcover = Bytes.toString(bCloudcover);
                    Row row = new Row(time,summary,temperature,apparenttemperature,humidity,cloudcover);
                    this.add(row);
                }

                // The other approach is to use a foreach loop. Scanners are iterable!
                // for (Result rr : scanner) {
                //   System.out.println("Found row: " + rr);
                // }
            } finally {
                // Make sure you close your scanners when you are done!
                // Thats why we have it inside a try/finally clause
                scanner.close();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public WeatherHistory getWeatherHistory(){
        return this;
    }

}
