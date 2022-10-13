package org.firstinspires.ftc.teamcode.follower;

import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.function.DoubleSupplier;

public class PID implements Controller {
    private double P;
    private double I;
    private double D;
    private double lastErr = 0;
    private double setPoint;
    private double kP;
    private double kI;
    private double kD;
    private DoubleSupplier feedforward;
    private boolean initiated  = false;
    private double dT;
    private double error;
    ElapsedTime timer;
    public PID(double kP, double kI, double kD, DoubleSupplier feedforward) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.feedforward = feedforward;
        this.dT = 0.05;
        timer = new ElapsedTime();
    }
    public PID(double kP, double kI, double kD) {
        this(kP, kI, kD, () -> 0);
    }
    public PID() { //sets default values
        this(1, 0, 0.1, () -> 0);
    }
    public void setCoefficients(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }
    public void setFeedforward(DoubleSupplier feedforward) {
        this.feedforward = feedforward;
    }
    public void setSetpoint(double setPoint) {
        this.setPoint = setPoint;
    }
    @Override
    public double getOutput(double pose) {
        if (!initiated) {
            initiated = true;
        } else {
            dT = timer.nanoseconds() / 1e9;
        }
        timer.reset();
        double error = setPoint - pose;
        lastErr = error;
        this.P = kP * error;
        this.I += kI * (error-lastErr) * dT;
        this.D = kD * (error-lastErr) / dT;
        return P + I + D + feedforward.getAsDouble();
    }
    public void cancel() {
        initiated = false;
        dT = 0.05;
    }
}
