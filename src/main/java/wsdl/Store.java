package wsdl;

import connector.HbaseConn;

/**
 * Created by pbechynak on 5.3.2016.
 */
public class Store {
    String time;
    String summary;
    String temperature;
    String apparenttemperature;
    String humidity;
    String cloudcover;

    public Store(String time, String summary, String temperature, String apparenttemperature, String humidity, String cloudcover) {
        this.time = time;
        this.summary = summary;
        this.temperature = temperature;
        this.apparenttemperature = apparenttemperature;
        this.humidity = humidity;
        this.cloudcover = cloudcover;
    }

    public String getInput() {
        return time +" "+ summary+" " + temperature+" " + apparenttemperature+" "+ humidity+" "+ cloudcover ;
    }

    public String getHbaseResult() throws Exception {
        HbaseConn hbaseConn = new HbaseConn();
        hbaseConn.addRecord("Cr", time, "weather", "summary", summary);
        hbaseConn.addRecord("Cr", time, "weather", "temperature", temperature);
        hbaseConn.addRecord("Cr", time, "weather", "apparenttemperature", apparenttemperature);
        hbaseConn.addRecord("Cr", time, "weather", "humidity", humidity);
        hbaseConn.addRecord("Cr", time, "weather", "cloudcover", cloudcover);
        return "Saved in Hbase. ";
    }
}