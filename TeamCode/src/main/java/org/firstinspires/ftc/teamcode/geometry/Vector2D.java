package org.firstinspires.ftc.teamcode.geometry;

public class Vector2D {
    private double x;
    private double y;
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2D() {
        this(0,0);
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double[] getVector() {
        return new double[]{x, y};
    }
    public Vector2D rotate(double angle) { //rotates the vector by the specified amount in radians
        double x = this.x * Math.cos(angle) - this.y * Math.sin(angle);
        double y = this.x * Math.sin(angle) + this.y * Math.cos(angle);
        return new Vector2D(x, y);
    }
    public double getAngle() {
        double angle;
        if (x == 0) {
            if (y > 0) {
                angle = Math.PI / 2;
            } else if (y < 0) {
                angle = -Math.PI / 2;
            } else {
                angle = Double.NaN;
            }
        } else {
            angle = Math.atan(x / y);
            if (x < 0) {
                angle += Math.PI;
            } else if ((x > 0) && (y < 0)) {
                angle += Math.PI * 2;
            }

        }
        return angle;
    }
}
