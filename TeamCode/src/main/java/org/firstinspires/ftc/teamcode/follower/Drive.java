package org.firstinspires.ftc.teamcode.follower;

import org.firstinspires.ftc.teamcode.DriveBase;
import org.firstinspires.ftc.teamcode.Localization;
import org.firstinspires.ftc.teamcode.geometry.Pose2D;

public class Drive {
    private static Drive instance = null;
    private PIDFollower follower;
    Localization localization;
    private Drive() {
        follower = new PIDFollower();
        localization = Localization.getInstance();
    }
    public void periodic() {
        localization.periodic();
        double[] motorOutput = follower.getOutput(localization.getPose(), new Pose2D());
        DriveBase.leftFront.setPower(motorOutput[0]);
        DriveBase.rightFront.setPower(motorOutput[0]);
        DriveBase.leftBack.setPower(motorOutput[2]);
        DriveBase.rightBack.setPower(motorOutput[3]);
    }
    public static Drive getInstance() {
        if(instance == null) {
            instance = new Drive();
        }
        return instance;
    }
}
