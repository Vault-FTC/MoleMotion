package org.firstinspires.ftc.teamcode.follower;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.DriveBase;
import org.firstinspires.ftc.teamcode.Localization;
import org.firstinspires.ftc.teamcode.trajectory.Trajectory;

public class Drive {
    private static Drive instance = null;
    private PIDFollower follower;
    Localization localization;
    private Trajectory currentTrajectory;
    private int waypointNum = 0;
    private Drive() {
        follower = new PIDFollower();
        localization = Localization.getInstance();
    }


    public void periodic(@NonNull Trajectory trajectory) {
        localization.periodic();
        double[] motorOutput = follower.getOutput(localization.getPose(), trajectory.getWaypoints().get(waypointNum));
        DriveBase.leftFront.setPower(motorOutput[0]);
        DriveBase.rightFront.setPower(motorOutput[0]);
        DriveBase.leftBack.setPower(motorOutput[2]);
        DriveBase.rightBack.setPower(motorOutput[3]);
        if (follower.getError() < PIDFollower.minErr && waypointNum < trajectory.getSize() - 1) {
            waypointNum ++;
        }
    }
    public boolean isFinished(Trajectory trajectory) {
        if ((this.currentTrajectory == trajectory) && trajectory.getSize() - 1 == waypointNum) {
            return true;
        }
        return false;
    }
    public static Drive getInstance() {
        if(instance == null) {
            instance = new Drive();
        }
        return instance;
    }
}
