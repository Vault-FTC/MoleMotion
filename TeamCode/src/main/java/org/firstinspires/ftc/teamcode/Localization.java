package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.geometry.Pose2D;

public class Localization { //for mecanum drive bases ONLY
    private static Localization instance  = null;
    private static Pose2D pose = new Pose2D();
    private Localization() {

    }
    public static Localization getInstance() {
        if (instance == null) {
            instance = new Localization();
        }
        return instance;
    }
    //TODO
    //work out the math for localization using the 3 variables in the periodic function
    public static void periodic(double deltaLF, double deltaRF, double deltaLB, double deltaRB) {
        double deltaX;
        double deltaY_right;
        double deltaY_left;
        pose = new Pose2D(); //should add to the previous pose value
    }
    public Pose2D getPose() {
        return this.pose;
    }
}
