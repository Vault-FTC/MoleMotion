package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Main extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while(true) {
            Localization.getInstance().periodic();
        }
    }
}
