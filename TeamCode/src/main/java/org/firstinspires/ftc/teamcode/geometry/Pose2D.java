package org.firstinspires.ftc.teamcode.geometry;

public class Pose2D {
    private double x;
    private double y;
    private double heading; //radians
    public Pose2D(double x, double y, double heading) {
        this.x = x;
        this.y = y;
        this.heading = heading;
    }
    public Pose2D() {
        this(0,0,0);
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getHeading() {
        return this.heading;
    }
    public double[] getPose() {
        return new double[]{x, y, heading};
    }
    public double getMagnitude() { //A Pose2D can be represented as a 3D vector.  This returns the magnitude of said vector
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(heading, 2));
    }
}
