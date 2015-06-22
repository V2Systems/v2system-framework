package uk.co.v2systems.framework.utils;

/**
 * Created by I&T Lab User on 22/06/2015.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


//import cucumber.api.DataTable;
//import cucumber.runtime.table.TableConverter;
//import jodd.datetime.JDateTime;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by I&T Lab User on 26/02/2015.
 */
public abstract class Methods {

    public static void printConditional(String printString, boolean print){
        if(print)
            System.out.println(printString);
    }
    public static void printConditional(String printString){
        System.out.println(printString);
    }
    public static String getLocalIP(){
        try {
            InetAddress ipAddress = InetAddress.getLocalHost();
            return ipAddress.getHostAddress();
        }catch(Exception e){
            printConditional("Exception in Methods.getLocalIP :" + e);
            return null;
        }
    }
    public static String getApplicationPath(){
        try {
            return URLDecoder.decode(new File(".").getCanonicalPath(), "UTF-8");
        }catch(Exception e){
            System.out.println("Exception in Methods.getApplicationPath: " + e);
            return null;
        }

    }

    public static List<List<String>> resultResetToListOfList(ResultSet resultSet){
        try {
            int rowNumber = 0; boolean setHeader=false;
            ResultSetMetaData resultSetMetadata=resultSet.getMetaData();
            List<List<String>> table = new ArrayList<List<String>>();
            List<String> row = new ArrayList<String>();

            //Set Header
            if(setHeader==false)
            {
                for(int i=1; i<= resultSetMetadata.getColumnCount();i++)
                    row.add(0,resultSetMetadata.getColumnName(i));
                setHeader=true;
                table.add(rowNumber,row);
                rowNumber++;
            }

            while(resultSet.next()) {
                row=new ArrayList<String>();
                for(int i=1; i<= resultSetMetadata.getColumnCount();i++)
                {
                    row.add(i-1,resultSet.getString(i));
                }
                table.add(rowNumber,row);
                rowNumber++;
            }
            return table;

        }catch(Exception e){
            printConditional("Exception in Methods.resultResetToListOfList :" + e);
            return null;
        }

    }

    /*
    public static List<List<String>> dataTableToListOfList(DataTable dataTable){
        try {
            return dataTable.raw();
        }catch(Exception e){
            printConditional("Exception in Methods.dataTableToListOfList :" + e);
            return null;
        }

    }
*/
    public static List<List<String>> arrayToListOfList(String[] stringArray){
        try {
            List<String> row;
            List<List<String>> table = new ArrayList<List<String>>();

            for(int i=0; i< stringArray.length; i++)
            {
                row=new ArrayList<String>();
                row.add(0,stringArray[i].toString());
                table.add(i,row);
            }
            return table;
        }catch(Exception e){
            printConditional("Exception in Methods.arrayToListOfList :" + e);
            return null;
        }

    }

    public static List<List<String>> arrayToListOfList(String[] stringArray, int clashGroup[], int clashCount[]){
        try {
            List<String> row;
            List<List<String>> table = new ArrayList<List<String>>();

            for(int i=0; i< stringArray.length; i++)
            {
                row=new ArrayList<String>();
                row.add(0,stringArray[i].toString());
                int k=1;
                for(int j=0; j < clashCount.length && clashCount.length==clashGroup.length;j++) {
                    row.add(k++, Integer.toString(clashGroup[j]));
                    row.add(k++, Integer.toString(clashCount[j]));
                }
                table.add(i,row);
            }
            return table;
        }catch(Exception e){
            printConditional("Exception in Methods.arrayToListOfList :" + e);
            return null;
        }

    }

    public static String[] generateNumericArrayList(String Header, int startingId, int requestedNumber) {

        String [] NumericArrayList = new String[requestedNumber+1];
        NumericArrayList[0]=Header;

        for(int i=1; i<=requestedNumber; i++)
        {
            NumericArrayList[i]=Integer.toString(startingId+i-1);
        }

        return NumericArrayList;
    }

    public static int[] intTointArray(int value) {
        int [] integer = {value};
        return integer;
    }

    public static int[]  intArrayGenerator(int length, int maxValue){
        int [] integerArray = new int[length];
        for(int i=0; i<length;i++)
            integerArray[i]= (int) (Math.random()* maxValue+1);

        return integerArray;

    }

    public static long getLongDateTime(){
        Date referenceDateTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(referenceDateTime);
        long longReferenceDateTime = referenceDateTime.getTime();
        return longReferenceDateTime;
    }
    public static long getLongDateTime(Date referenceDateTime){
        Calendar cal = Calendar.getInstance();
        cal.setTime(referenceDateTime);
        return referenceDateTime.getTime();
    }

    public static long getLongSeconds(long hhmmssMsMs){
        long longSeconds=0; long MsMs=0; long ss=0; long mm=0; long hh=0;
        for(int i=0; hhmmssMsMs>0; i++){
            long quotient = hhmmssMsMs/100;
            if(i==0)
                MsMs = (hhmmssMsMs - quotient *100);
            if(i==1)
                ss = (hhmmssMsMs - quotient *100);
            if(i==2)
                mm = (hhmmssMsMs - quotient *100);
            if(i==3)
                hh = (hhmmssMsMs - quotient *100);
            hhmmssMsMs=quotient;
        }

        longSeconds = hh * 3600000 + mm * 60000 + ss *1000;
        return longSeconds;
    }

    public static Date getDateTime(){
        Date referenceDateTime = new Date();
        return referenceDateTime;
    }
    public static Date getDateTime(String strDateTime, String strDateTimeFormat){
        try {
            DateFormat dateFormat = new SimpleDateFormat(strDateTimeFormat);
            return dateFormat.parse(strDateTime);

        }catch(Exception e){
            return new Date();
        }
    }

    public static Date getDateTime(long longDateTime){
        try {
            return new Date(longDateTime);
        }catch(Exception e){
            return new Date();
        }
    }

    public static String getDateTime(String strDateTimeFormat){
        DateFormat dateFormat = new SimpleDateFormat(strDateTimeFormat);
        return dateFormat.format(Methods.getDateTime(Methods.getLongDateTime()));
    }
    public static String getDateTime(String strDateTimeFormat, long longDateTime){
        DateFormat dateFormat = new SimpleDateFormat(strDateTimeFormat);
        return dateFormat.format(Methods.getDateTime(longDateTime));
    }

    /*
    public static String getMJDDate(long longDateTime){
    /*
        Double doubleDateTime;
        doubleDateTime= (double)  longDateTime * 25 / ( 86400000 * 25) +  2440587.5 - 2400001;
        return String.format("%.7f",doubleDateTime);
        //String.valueOf(doubleDateTime);
        */
 /*       JDateTime jdt = new JDateTime(longDateTime);
        jdt.addMillisecond(30000);
        return jdt.getJulianDate().toString();

    }
*/
    public static void writeToFile(String content, String fileName){
        try {
            File file = new File(fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();


        }catch(Exception e){
            System.out.println("Exception in Methos.writeToFile()");
        }

    }
    public static void deleteFile(String fileName){
        File file = new File(fileName);
        // if file doesnt exists, then create it
        if (file.exists()) {
            file.delete();
        }

    }
}
