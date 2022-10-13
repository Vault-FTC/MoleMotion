package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class DriveBase {
    public static final boolean IMU_LOCALIZATION = false;
    public static DcMotor leftFront;
    public static DcMotor rightFront;
    public static DcMotor leftBack;
    public static DcMotor rightBack;
    protected static double TRACK_WIDTH = 16.5;
    protected static double VERTICAL_DISTANCE = 16.5;
    protected static double DRIVE_MULTIPLIER = 1;
    protected static double STRAFE_MULTIPLIER = 1;
    protected static int TICKS_PER_REV = 1000;
    protected static double WHEEL_DIAMETER = 4;
    protected static double GEAR_RATIO = 1;
}
