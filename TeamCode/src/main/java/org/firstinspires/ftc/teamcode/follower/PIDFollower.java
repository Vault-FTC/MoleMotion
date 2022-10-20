package org.firstinspires.ftc.teamcode.follower;

import org.firstinspires.ftc.teamcode.geometry.Pose2D;
import org.firstinspires.ftc.teamcode.geometry.Vector2D;

public class PIDFollower {
    public static double minErr = 1.0;
    Controller followerX;
    Controller followerY;
    Controller followerHeading;
    public PIDFollower() {
        followerX = new PID(0.5, 0, 0.1);
        followerY = new PID(0.5, 0, 0.1);
        followerHeading = new PID(0.5, 0, 0.1);
    }
    /***
     * Returns the output power for 4 motors: left front, right front, left back and right back
     * @return The output power for each of the 4 motors.
     */
    public double getError() { //returns the magnitude of the total error (which is a 3D vector)
        Pose2D error = new Pose2D(followerX.getError(), followerY.getError(), followerHeading.getError());
        return error.getMagnitude();
    }
    public double[] getOutput(Pose2D pose, Pose2D target) {
        followerX.setSetpoint(target.getX());
        followerY.setSetpoint(target.getY());
        followerHeading.setSetpoint(target.getHeading());
        double x = followerX.getOutput(pose.getX());
        double y = followerY.getOutput(pose.getY());
        Vector2D outputVector = new Vector2D(x, y);
        Vector2D outputVectorRelative = outputVector.rotate(-pose.getHeading()); //gets the vector relative to the bot, not the coordinate plane
        double turn = followerHeading.getOutput(pose.getHeading());
        double lf = outputVectorRelative.getY() - outputVectorRelative.getX() - turn;
        double rf = outputVectorRelative.getY() + outputVectorRelative.getX() + turn;
        double lb = outputVectorRelative.getY() + outputVectorRelative.getX() - turn;
        double rb = outputVectorRelative.getY() - outputVectorRelative.getX() + turn;
        return clampedMotorOutput(lf, rf, lb, rb);
    }
    private double[] clampedMotorOutput(double lf, double rf, double lb, double rb) {
        double largestFront = Math.max(Math.abs(lf), Math.abs(rf));
        double largestBack = Math.max(Math.abs(lb), Math.abs(rb));
        double largest = Math.max(largestFront, largestBack);
        if (largest > 1.0) {
            double divisor = largest / 1.0;
            lf /= divisor;
            rf /= divisor;
            lb /= divisor;
            rb /= divisor;
        }
        return new double[] {lf, rf, lb, rb};
    }
}
