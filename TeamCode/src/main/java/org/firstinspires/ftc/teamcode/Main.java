package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.follower.Drive;
import org.firstinspires.ftc.teamcode.trajectory.Trajectory;

public class Main extends OpMode {

    Trajectory trajectory1 = Trajectory.getBuilder().build();
    Drive drive = Drive.getInstance();

    @Override
    public void init() {

    }
    @Override
    public void loop() {
        drive.periodic();
        if (drive.getCurrentTrajectory().isFinished()) {
            drive.nextTrajectory();
        }
    }
}
