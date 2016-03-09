package connector;

import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HbaseConn {
public static Configuration config;

    public HbaseConn() throws ServiceException, IOException {
        System.setProperty("hadoop.home.dir", "c:\\winutil\\");
        config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "cz-dc1-s-133.mall.local:2181,cz-dc1-s-135.mall.local:2181,cz-dc1-s-132.mall.local:2181,cz-dc1-s-134.mall.local:2181," +
                "cz-dc1-s-136.mall.local:2181 ");
        config.set("hbase.master", "cz-dc1-v-197.mall.local:60010");
        config.set("hbase.zookeeper.property.clientport", "2181");
        HBaseAdmin.checkHBaseAvailable(config);
    }

    public static void main(String[] args) throws Exception {
       HbaseConn hbaseConn = new HbaseConn();
        String[] colfamilys = {"fam1","fam2"};
        //hbaseConn.creatTable("JavaTable", colfamilys);
        //addRecord("JavaTable","row1","fam2","xxx1","vvv");
        //getOneRecord("JavaTable","row1");
    }

    public static void creatTable(String tableName, String[] familys) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(config);
        if (admin.tableExists(tableName)) {
            System.out.println("table already exists!");
        } else {
            HTableDescriptor tableDesc = new HTableDescriptor(tableName);
            for (int i = 0; i < familys.length; i++) {
                tableDesc.addFamily(new HColumnDescriptor(familys[i]));
            }
            admin.createTable(tableDesc);
            System.out.println("create table " + tableName + " ok.");
        }
    }

    /**
     * Delete a table
     */
    public static void deleteTable(String tableName) throws MasterNotRunningException {
        try {
            HBaseAdmin admin = new HBaseAdmin(config);
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            System.out.println("delete table " + tableName + " ok.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Put (or insert) a row
     */
    public static void addRecord(String tableName, String rowKey,
                                 String family, String qualifier, String value) throws Exception {
        try {
            HTable table = new HTable(config, tableName);
            Put put = new Put(Bytes.toBytes(rowKey));
            put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes
                    .toBytes(value));
            table.put(put);
            System.out.println("insert recored " + rowKey + " to table "
                    + tableName + " ok.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a row
     */
    public static void delRecord(String tableName, String rowKey)
            throws IOException {
        HTable table = new HTable(config, tableName);
        List<Delete> list = new ArrayList<Delete>();
        Delete del = new Delete(rowKey.getBytes());
        list.add(del);
        table.delete(list);
        System.out.println("del recored " + rowKey + " ok.");
    }

    /**
     * Get a row
     */
    public static void getOneRecord (String tableName, String rowKey) throws IOException{
        HTable table = new HTable(config, tableName);
        Get theGet = new Get(Bytes.toBytes("row1"));
        Result result = table.get(theGet);
        //get value first column
        String inValue1 = Bytes.toString(result.value());
        //get value by ColumnFamily and ColumnName
        byte[] inValueByte = result.getValue(Bytes.toBytes("fam1"), Bytes.toBytes("xxx"));
        String inValue2 = Bytes.toString(inValueByte);

        //loop for result
        for (Cell cell : result.listCells()) {
            String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
            String value = Bytes.toString(CellUtil.cloneValue(cell));
            System.out.printf("Qualifier : %s : Value : %s%n", qualifier, value);
        }


    }

}