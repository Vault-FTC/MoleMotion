package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.follower.Drive;
import org.firstinspires.ftc.teamcode.geometry.Pose2D;

public class Localization { //for mecanum drive bases ONLY
    private static Localization instance  = null;
    private Pose2D pose = new Pose2D();
    private Localization() {

    }
    public static Localization getInstance() {
        if (instance == null) {
            instance = new Localization();
        }
        return instance;
    }
    //TODO
    //test localization
    public void periodic() {
        if(DriveBase.IMU_LOCALIZATION) {
            imuLocalize();
        } else {
            mecanEncoderLocalize(0, 0, 0, 0);
        }
    }
    //TODO
    //implement IMU
    private void imuLocalize() {
        pose = new Pose2D();
    }
    private void mecanEncoderLocalize(double deltaLF, double deltaRF, double deltaLB, double deltaRB) {
        final double conversionConstant = 1 / DriveBase.TICKS_PER_REV * DriveBase.WHEEL_DIAMETER * Math.PI;
        double deltaRight = (deltaRF + deltaRB) * conversionConstant / 2 * DriveBase.DRIVE_MULTIPLIER;
        double deltaLeft = (deltaLF + deltaLB) * conversionConstant / 2 * DriveBase.DRIVE_MULTIPLIER;
        double deltaFront = (deltaRF - deltaLF) * DriveBase.STRAFE_MULTIPLIER;
        double deltaBack = (deltaRB - deltaLB) * DriveBase.STRAFE_MULTIPLIER;
        double deltaStrafe = (deltaFront + deltaBack) / 2;
        double deltaDriveHeading = deltaRight - deltaLeft;
        double deltaStrafeHeading = deltaBack-deltaFront;
        double newHeading = pose.getHeading() + deltaDriveHeading + deltaStrafeHeading;
        double driveRadius = (deltaRight/deltaDriveHeading) - (DriveBase.TRACK_WIDTH / 2);
        double driveX = driveRadius * Math.cos(newHeading) - pose.getX();
        double driveY = driveRadius * Math.sin(newHeading) - pose.getY();
        double strafeRadius = (deltaStrafe/deltaDriveHeading);
        double strafeX = strafeRadius * Math.cos(newHeading) - pose.getX();
        double strafeY = strafeRadius * Math.sin(newHeading) - pose.getY();
        pose = new Pose2D(pose.getX() + driveX + strafeX, pose.getY() + driveY + strafeY, newHeading);
    }
    public Pose2D getPose() {
        return this.pose;
    }
}
