package com.company;

import com.ibm.icu.text.NumberFormat;
import org.apache.commons.lang3.StringEscapeUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

import java.io.*;

import java.util.*;



public class Main {

    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        // Write your code here.

        if(sequence.size()>array.size())
            return false;
        
        int pointer = 0;
        int pointer2 = 0;
        int sum2 = 0;
        for(int i = 0; i < array.size() ; i++ )
        {
            if(sequence.size()==pointer)
                break;
            if (array.get(i) == sequence.get(pointer))
            {
                pointer=pointer+1;
                sum2=sum2+1;
            }
        }
        if(sum2==sequence.size())
        {
            return true;
        }else return false;
       /* for(int i = 0; i < sequence.size();i++)
        {
            for(int j = pointer  ; j < array.size(); j ++)
            {
                if(sequence.get(i)==array.get(j))
                {
                    pointer=j+1;

                    break;
                }else{
                    array.remove(j);
                    j--;
                }
            }
        }
        
        return returnTrue(array, sequence);*/
    }
    
    public static boolean returnTrue(List<Integer> array, List<Integer> sequence)
    {
            if(array.size()<sequence.size())
                return false;
            
            for(int i =0 ; i<sequence.size(); i++)
            {
                if(array.get(i)!=sequence.get(i))
                    return false;
            }
            return true;
     
    }

    public static void main(String[] args) throws IOException {

        
	// write your code here
        System.out.println(new  File(".").getCanonicalPath());
        File myFileToRemoveNoise = new File("gc.log.0");
        Writer writer = new BufferedWriter(new FileWriter("test.csv"));
        Scanner scanner = new Scanner(myFileToRemoveNoise);
        double seconds=0;
        int counter;
        double totalSeconds=0;
      
       while (scanner.hasNextLine()) {
            String theNewLine = scanner.nextLine();
            if (theNewLine.contains("Total time for which application")) {

                try{
                    String[] myNewLine = theNewLine.split(":");
                    String[] myNewLines=myNewLine[1].split(" ");
                    seconds = Double.parseDouble(myNewLines[1]);
                    if(seconds>1.0f)
                    {
                        totalSeconds=totalSeconds+seconds;
                        writer.write(seconds + System.lineSeparator());
                    }
                    seconds=0;
                }catch (Exception e)
                {
                    System.out.println("test");
                }
            
            }
        }
        writer.write("TOTAL SECONDS: "+totalSeconds + System.lineSeparator());
      
     /*  String myNewLine="CurrentCamelTime,EventTime,DiffTime,Offset,Sending Bulk Request,Http Server Response,ERROR";
        writer.write(myNewLine+System.lineSeparator());
        myNewLine="";
        
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateTimeFormatter format2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss,SSSZ");

        while (scanner.hasNextLine())
        {
            String theNewLine = scanner.nextLine();
            if(theNewLine.contains("kafkaToHttp2") || theNewLine.contains("vaf27p1001"))
            {
                if(theNewLine.contains("Aggreagte "))
            {
                String[] jsonValue = theNewLine.split("Event: ");
                String[] dateTimeStamp=theNewLine.split(" ");
                String myCurrentDate= dateTimeStamp[1];
                String myNewJson = jsonValue[1];
                JSONObject jsonObject = new JSONObject(myNewJson);
                String timeStamp = jsonObject.getJSONObject("header").getString("eventTime");
                DateTime dateTime =  format.withOffsetParsed().parseDateTime(timeStamp);
                DateTime dateTime2 =  format2.withOffsetParsed().parseDateTime(dateTimeStamp[0]+" "+myCurrentDate+"Z");
                Duration duration = new Duration(dateTime2,dateTime);
                myNewLine= StringEscapeUtils.escapeCsv(myCurrentDate)+","+StringEscapeUtils.escapeCsv(timeStamp)+","+(dateTime2.getMillis()-dateTime.getMillis())+",";
                // writer.write(theNewLine+System.lineSeparator());
            }
                if(theNewLine.contains("Offset Event:"))
                {
                    String[] jsonValue = theNewLine.split("Offset Event:");
                    String myNewJson = jsonValue[1];
                    myNewLine=myNewLine+myNewJson;
                    writer.write(myNewLine+","+System.lineSeparator());
                    myNewLine="";
                }
                if(theNewLine.contains("Sending"))
                {
                    String[] dateTimeStamp=theNewLine.split(" ");
                    String myCurrentDate= dateTimeStamp[1];
                    myNewLine=",,,," + StringEscapeUtils.escapeCsv(myCurrentDate);
                }
                if(theNewLine.contains("response:") || theNewLine.contains("ERROR"))
                {
                    String[] dateTimeStamp=theNewLine.split(" ");
                    String myCurrentDate= dateTimeStamp[1];
                    if(theNewLine.contains("ERROR"))
                    {
                        writer.write( myNewLine+","+StringEscapeUtils.escapeCsv(myCurrentDate)+",true"+System.lineSeparator());
                        myNewLine="";
                    }else{
                        writer.write( myNewLine+","+StringEscapeUtils.escapeCsv(myCurrentDate)+",false"+System.lineSeparator());
                        myNewLine=""; 
                    }
                   
                }
                
            }
           
        }
        */
        writer.close();
    }
}
