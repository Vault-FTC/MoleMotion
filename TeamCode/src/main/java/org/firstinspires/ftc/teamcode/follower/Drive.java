package org.firstinspires.ftc.teamcode.follower;

import org.firstinspires.ftc.teamcode.DriveBase;
import org.firstinspires.ftc.teamcode.Localization;
import org.firstinspires.ftc.teamcode.trajectory.Trajectory;
import org.firstinspires.ftc.teamcode.trajectory.TrajectorySequence;

public class Drive {
    private static Drive instance = null;
    private PIDFollower follower;
    Localization localization;
    private Trajectory currentTrajectory;
    private int waypointNum = 0;
    private int trajectoryNum = 0;
    private Drive() {
        follower = new PIDFollower();
        localization = Localization.getInstance();
    }


    public void periodic() {
        currentTrajectory = TrajectorySequence.getTrajectory(trajectoryNum);
        localization.periodic();
        double[] motorOutput = follower.getOutput(localization.getPose(), currentTrajectory.getWaypoints().get(waypointNum));
        DriveBase.leftFront.setPower(motorOutput[0]);
        DriveBase.rightFront.setPower(motorOutput[0]);
        DriveBase.leftBack.setPower(motorOutput[2]);
        DriveBase.rightBack.setPower(motorOutput[3]);
        if (follower.getError() < PIDFollower.minErr) {
            if (waypointNum < currentTrajectory.getSize() - 1) {
                waypointNum ++;
            }
        }
    }
    public void beginTrajectory(int trajectoryNum) {
        this.trajectoryNum = trajectoryNum;
        waypointNum = 0;
        follower.reset();
    }
    public void beginTrajectory(Trajectory trajectory) {
        beginTrajectory(TrajectorySequence.getIndex(trajectory));
    }
    public void nextTrajectory() {
        beginTrajectory(trajectoryNum + 1);
    }
    public Trajectory getCurrentTrajectory () {
        return TrajectorySequence.getTrajectory(trajectoryNum);
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
