package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class DriveBase {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    protected static double TRACK_WIDTH = 16.5;
    protected static double VERTICAL_DISTANCE = 16.5;
    protected static double DRIVE_MULTIPLIER = 1;
    protected static double STRAFE_MULTIPLIER = 1;
    protected static int TICKS_PER_REV = 1000;
    protected static double WHEEL_DIAMETER = 4;
    protected static double GEAR_RATIO = 1;
    public void Periodic() {
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }
}
