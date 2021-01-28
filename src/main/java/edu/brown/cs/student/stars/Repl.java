package edu.brown.cs.student.stars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Repl {
    public void registAction(String actionType, Action action) {
        actionMap.put(actionType,action);
    }

    private HashMap<String,Action>  actionMap=new HashMap<>();


    public void run() {
        //Scanner
        try(
                InputStreamReader in = new InputStreamReader(System.in);
                BufferedReader buf =new BufferedReader(in);//read from console
        ) {
            String line = null;
            while ( (line=buf.readLine())!=null ) {
                //String[] arr = line.split("\\s"); //正则表达式
                String[] arr = parseArgsFromLine(line);
                String actionType=arr[0];
                String[] args = Arrays.copyOfRange(arr,1,arr.length);
                Action action = actionMap.get(actionType);
                if(action!=null){
                    action.action(args);
                }else{
                    System.out.println("ERROR: not supported action!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] parseArgsFromLine(String line) {
        ArrayList<String> arr = new ArrayList<>();
        while(line.length()!=0){
            if(line.startsWith("\"")){
                int nextYh = line.indexOf("\"",1);
                String order = line.substring(1,nextYh);
                //System.out.println(order);
                arr.add(order);
                line = line.substring(nextYh+1);
            }else{
                int nextBlank = line.indexOf(" ",1);
                if(nextBlank==-1){
                    String order = line;
                    //System.out.println(order);
                    arr.add(order);
                    line = "";
                }else {
                    String order = line.substring(0, nextBlank);
                    //System.out.println(order);
                    arr.add(order);
                    line = line.substring(nextBlank + 1);
                }
            }
            line = line.trim();
        }
        String[] result = new String[arr.size()];
        return arr.toArray(result);
    }

    public static void main(String[] aa) {
//        Repl repl = new Repl();
//        repl.registAction("xxx",args->{
//            System.out.println(Arrays.toString(args));
//        });
//        repl.run();
        parseArgsFromLine("12 23 \"a a\" \" _b\"");
    }
}
