package edu.brown.cs.student.stars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SearchCenter {

    private ArrayList<Star> data;

    public SearchCenter(ArrayList<Star> data) {
        this.data = data;
    }


    /**
     * by name
     * @param redius
     * @param starName
     * @return
     */
    public ArrayList<Star> naiveRadius(double redius,String starName){
        Star targetStar = findOneByName(starName);
        Point p = targetStar.getPoint();
        return naiveRadius(redius,p);
    }

    public ArrayList<Star> naiveRadius(double redius,Point p){
        ArrayList<StarWithDistance> result = new ArrayList<>();
        for (Star star : data) {
            Double distance=p.distanceTo(star.getPoint());
            if( distance <= redius){
                result.add(new StarWithDistance(distance,star));
            }
        }
        Collections.sort(result);
        ArrayList<Star> res = new ArrayList<>();
        for (StarWithDistance s : result) {
            res.add(s.getStar());
        }
        return res;
    }

    // a1 b1 c2 d2 e2 f3
    // a1 b1 e2

    public ArrayList<Star> naiveNeighbors(int topN,String starName){
        Star targetStar = findOneByName(starName);
        Point p = targetStar.getPoint();
        return naiveNeighbors(topN,p);
    }

    public ArrayList<Star> naiveNeighbors(int topN,Point p){
        DistanceStarMap m = new DistanceStarMap();
        for (Star star : data) {
            if(star.getPoint()==p){// one object
                continue;
            }
            Double distance=p.distanceTo(star.getPoint());
            m.put(distance,star);
        }
        return m.topN(topN);
    }

    private Star findOneByName(String starName) {
        Star targetStar = null;
        for (Star star : data) {
            if(star.getProperName().equals(starName)){
                if(targetStar==null){
                    targetStar = star;
                }else{
                    throw new RuntimeException("error:repeat name");
                }
            }
        }
        return targetStar;
    }


    public static void main(String[] args) {
        StarCsvParser parser = new StarCsvParser("D:\\ts\\wanglu\\20210124\\stars-lw2403-master\\data\\stars\\ten-star.csv");
        //StarCsvParser parser = new StarCsvParser("D:\\ts\\wanglu\\20210124\\stars-lw2403-master\\data\\stars\\three-star.csv");
        //StarCsvParser parser = new StarCsvParser("D:\\ts\\wanglu\\20210124\\stars-lw2403-master\\data\\stars\\tie-star.csv");
        try {
            ArrayList<Star> result = parser.read();
            SearchCenter searchCenter = new SearchCenter(result);
            //result = searchCenter.naiveRadius(10, new Point(0, 0, 0));
            //result = searchCenter.naiveRadius(10, "Sol");

            result = searchCenter.naiveNeighbors(5, "Sol");

            for (Star star :    result            ) {
                System.out.println(star);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
