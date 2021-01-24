package edu.brown.cs.student.stars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

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
                String[] arr = line.split("\\s"); //正则表达式
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

    public static void main(String[] aa) {
        Repl repl = new Repl();
        repl.registAction("xxx",args->{
            System.out.println(Arrays.toString(args));
        });
        repl.run();
    }
}
