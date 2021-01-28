package edu.brown.cs.student.stars;

import java.lang.reflect.Array;
import java.util.*;

public class DistanceStarMap {

    private TreeMap<Double, ArrayList<Star>> map=new TreeMap<>();

    public void put(Double distance,Star star){
        ArrayList<Star> stars=map.get(distance);
        if(stars==null){
            stars = new ArrayList<>();
            map.put(distance,stars);
        }
        stars.add(star);
    }

    public ArrayList<Star> topN(int n){
        ArrayList<Star> result=new ArrayList<>();
        for(Double dis:map.keySet()){
            ArrayList<Star> stars=map.get(dis);
            if(result.size()+stars.size()<=n){
                result.addAll(stars);
            }else{
                result.addAll( chooseRandomNStar(stars,n-result.size()) );
                break;
            }
        }
        return result;
    }

    private static List<Star> chooseRandomNStar(ArrayList<Star> stars, int n) {
        Collections.shuffle(stars); // a b c
        return stars.subList(0,n);
    }

    public static void main(String[] args) {
        DistanceStarMap m = new DistanceStarMap();
        m.put(1.0,new Star(1,"",0,0,1));
        m.put(1.0,new Star(2,"",0,0,1));
        m.put(1.0,new Star(3,"",0,0,1));
        m.put(2.0,new Star(4,"",0,0,1));
        m.put(2.0,new Star(5,"",0,0,1));
        m.put(2.0,new Star(6,"",0,0,1));
        for(Star s:m.topN(5)){
            System.out.println(s);
        }
    }

}
