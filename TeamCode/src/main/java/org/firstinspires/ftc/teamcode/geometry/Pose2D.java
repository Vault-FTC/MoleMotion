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
        this.x = 0;
        this.y = 0;
        this.heading = 0;
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
}
