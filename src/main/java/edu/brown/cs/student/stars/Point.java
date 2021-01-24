package edu.brown.cs.student.stars;

public class Point {

    double x;
    double y;
    double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public double distanceTo(Point p){
       return Math.sqrt(
                  Math.pow(this.x-p.x,2)+
                  Math.pow(this.y-p.y,2)+
                  Math.pow(this.z-p.z,2)
       );
    }
}
