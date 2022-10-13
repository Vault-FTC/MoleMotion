package org.firstinspires.ftc.teamcode.follower;

import org.firstinspires.ftc.teamcode.geometry.Pose2D;
import org.firstinspires.ftc.teamcode.geometry.Vector2D;

public class PIDFollower {
    Controller followerX;
    Controller followerY;
    Controller followerHeading;
    public PIDFollower() {
        followerX = new PID();
        followerY = new PID();
        followerHeading = new PID();
    }

    /***
     * Returns the output power for 4 motors: left front, right front, left back and right back
     * @return The output power for each of the 4 motors.
     */
    public double[] getOutput(Pose2D pose, Pose2D target) {
        followerX.setSetpoint(target.getX());
        followerY.setSetpoint(target.getY());
        followerHeading.setSetpoint(target.getHeading());
        double x = followerX.getOutput(pose.getX());
        double y = followerY.getOutput(pose.getY());
        Vector2D outputVector = new Vector2D(x, y);
        Vector2D outputVectorRelative = outputVector.rotate(-outputVector.getAngle()); //gets the vector relative to the bot, not the coordinate plane
        double turn = followerHeading.getOutput(pose.getHeading());
        return new double[] {};
    }
}
