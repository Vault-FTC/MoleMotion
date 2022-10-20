package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.teamcode.geometry.Pose2D;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

@Disabled
public class Localization extends OpMode { //for mecanum drive bases ONLY
    private static Localization instance  = null;
    private Pose2D pose = new Pose2D();
    BNO055IMU.Parameters parameters;
    Orientation angles;
    Acceleration gravity;
    BNO055IMU imu;
    HardwareMap hardwareMap;
    private Localization() {
        parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
    }
    public void initialize() {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        imu.startAccelerationIntegration(new Position(), new Velocity(), 50); //dunno if a small poll interval will reduce program cycle time?  May have to sacrifice accuracy and increase it
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

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
        Orientation heading = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
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
