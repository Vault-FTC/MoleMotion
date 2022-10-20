package org.firstinspires.ftc.teamcode.follower;

public interface Controller {
    void setSetpoint(double setpoint);
    double getError();
    double getOutput(double pose);
}
