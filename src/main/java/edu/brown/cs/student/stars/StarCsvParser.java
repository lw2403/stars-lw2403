package edu.brown.cs.student.stars;

import java.io.*;
import java.util.ArrayList;

/**
 * StarCsvParser parser = new StarCsvParser("xxx/xxx/xxx/xxx.cvs");
 * ArrayList<Star> result = parser.read();
 */
public class StarCsvParser {

    private String fileName;

    public StarCsvParser(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Star> read() throws IOException {
        ArrayList<Star> arrayList = new ArrayList<>();
        try( // when end of try code  or throw exception   call close of fileIns,isr,reader
            FileInputStream fileIns = new FileInputStream(fileName); // read bytes
            InputStreamReader isr = new InputStreamReader(fileIns); // read chars
            BufferedReader reader = new BufferedReader(isr); // read lines
        ) {
            String head = reader.readLine();
            //System.out.println("read head:" + head);
            String line = null;
            while ((line = reader.readLine()) != null) {
                //parse line to star
                arrayList.add(line2Star(line));
            }
        }
        return arrayList;
    }

    // make a star object from a line
    public static Star line2Star(String line){
        String[] arr=line.split(",");
        return new Star(
                Integer.parseInt(arr[0]),
                arr[1],
                Double.parseDouble(arr[2]),
                Double.parseDouble(arr[3]),
                Double.parseDouble(arr[4])
                );
    }

    public static void main(String[] args) {
        //System.out.println(line2Star("71457,Rigel Kentaurus A,-0.50362,-0.42139,-1.17665"));

        //StarCsvParser parser = new StarCsvParser("D:\\ts\\wanglu\\20210124\\stars-lw2403-master\\data\\stars\\one-star.csv");
        //StarCsvParser parser = new StarCsvParser("D:\\ts\\wanglu\\20210124\\stars-lw2403-master\\data\\stars\\stardata.csv");
        //StarCsvParser parser = new StarCsvParser("D:\\ts\\wanglu\\20210124\\stars-lw2403-master\\data\\stars\\ten-star.csv");
        //StarCsvParser parser = new StarCsvParser("D:\\ts\\wanglu\\20210124\\stars-lw2403-master\\data\\stars\\three-star.csv");
        StarCsvParser parser = new StarCsvParser("D:\\ts\\wanglu\\20210124\\stars-lw2403-master\\data\\stars\\tie-star.csv");
        try {
            ArrayList<Star> result = parser.read();
            for (Star star :    result            ) {
                System.out.println(star);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
