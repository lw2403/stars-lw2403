package edu.brown.cs.student.stars;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StarsRepl extends Repl{
    private ArrayList<Star> allStarData;

    private Action starsAction = args -> {
        if(args.length==0){
            System.err.println("ERROR:no star file");
            return;
        }
        String fileName = args[0];
        File file = new File(fileName);
        if(!file.exists()){
            System.err.println("ERROR:"+fileName+" not exists!");
            return;
        }
        if(!file.isFile()){
            System.err.println("ERROR:"+fileName+" is not a file!");
            return;
        }
        StarCsvParser parser = new StarCsvParser(fileName);
        try {
            ArrayList<Star> result = parser.read();
            System.out.printf("Read %d stars from %s\n",result.size(),fileName);
            allStarData = result;
        } catch (IOException e) {
            //e.printStackTrace();
            System.err.println("ERROR:parse filed!");
        }
    };

    private Action naiveNeighborsAction = args -> {
        SearchCenter searchCenter = new SearchCenter(allStarData);
        if(args.length==4||args.length==2){
            if(allStarData==null){
                System.err.println("ERROR:haven't load file yet!");
                return;
            }
            ArrayList<Star> result = null;
            if(args.length==4){
                int n = Integer.parseInt(args[0]);
                Point p = new Point(
                        Double.parseDouble(args[1]),
                        Double.parseDouble(args[2]),
                        Double.parseDouble(args[3])
                );
                result= searchCenter.naiveNeighbors(n,p);
            }else if(args.length==2){
                int n = Integer.parseInt(args[0]);
                String name = args[1];//.substring(1,args[1].length()-1);
                result = searchCenter.naiveNeighbors(n,name);
            }
            if(result.size()==0){
                //System.out.println("single star!");
            }
            for (Star star : result) {
                System.out.println(star.getStarID());
            }

        }else{
            System.err.println("ERROR:invalid input");
        }
    };

    private Action naiveRadiusAction = args -> {
        SearchCenter searchCenter = new SearchCenter(allStarData);
        if(args.length==4||args.length==2){
            if(allStarData==null){
                System.err.println("ERROR:haven't load file yet!");
                return;
            }
            ArrayList<Star> result = null;
            double r = Double.parseDouble(args[0]);
            if(r<0){
                System.err.println("ERROR:redius must be positive");
                return;
            }
            if(args.length==4){
                Point p = new Point(
                        Double.parseDouble(args[1]),
                        Double.parseDouble(args[2]),
                        Double.parseDouble(args[3])
                );
                result= searchCenter.naiveRadius(r,p);
            }else if(args.length==2){
                String name = args[1];//.substring(1,args[1].length()-1);
                result = searchCenter.naiveRadius(r,name);
            }
            if(result.size()==0){
                System.out.println("single star!");
            }
            for (Star star : result) {
                System.out.println(star.getStarID());
            }

        }else{
            System.err.println("ERROR:invalid input");
        }
    };

    private Action mockAction = args -> {
        if(args.length==0){
            System.err.println("ERROR:no star file");
            return;
        }
        String fileName = args[0];
        File file = new File(fileName);
        if(!file.exists()){
            System.err.println("ERROR:"+fileName+" not exists!");
            return;
        }
        if(!file.isFile()){
            System.err.println("ERROR:"+fileName+" is not a file!");
            return;
        }
        StarCsvParser parser = new StarCsvParser(fileName);
        try {
            ArrayList<Star> result = parser.read();
            System.out.printf("Read %d stars from %s\n",result.size(),fileName);
            for(Star star:result){
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty("StarID",star.getStarID());
                jsonObj.addProperty("ProperName",star.getProperName());
                jsonObj.addProperty("X",star.getPoint().getX());
                jsonObj.addProperty("Y",star.getPoint().getY());
                jsonObj.addProperty("Z",star.getPoint().getZ());
                System.out.println(jsonObj);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.err.println("ERROR:parse filed!");
        }
    };

    public StarsRepl() {
        registAction("stars",starsAction);
        registAction("naive_neighbors",naiveNeighborsAction);
        registAction("naive_radius",naiveRadiusAction);
        registAction("mock",mockAction);
    }
}
