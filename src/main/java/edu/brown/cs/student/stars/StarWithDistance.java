package edu.brown.cs.student.stars;

public class StarWithDistance implements Comparable<StarWithDistance>{
    private Star star;
    private Double distance;

    public StarWithDistance(Double distance,Star star) {
        this.star = star;
        this.distance = distance;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "StarWithDistance{" +
                "star=" + star +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int compareTo(StarWithDistance o) {
        return this.distance.compareTo(o.distance);
    }
}
