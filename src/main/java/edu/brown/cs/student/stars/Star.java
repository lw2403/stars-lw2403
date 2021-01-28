package edu.brown.cs.student.stars;

import java.util.Objects;

/**
 * star entity
 */
public class Star {

    private Integer starID;
    private String properName;
    private Point point;

    public Star(Integer starID, String properName, double x, double y, double z) {
        this.starID = starID;
        this.properName = properName;
        this.point = new Point(x,y,z);
    }

    public Star() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return Objects.equals(starID, star.starID) &&
                Objects.equals(properName, star.properName) &&
                Objects.equals(point, star.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(starID, properName, point);
    }

    public Integer getStarID() {
        return starID;
    }

    public void setStarID(Integer starID) {
        this.starID = starID;
    }

    public String getProperName() {
        return properName;
    }

    public void setProperName(String properName) {
        this.properName = properName;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Star{" +
                "starID=" + starID +
                ", properName='" + properName + '\'' +
                ", point=" + point +
                '}';
    }
}
