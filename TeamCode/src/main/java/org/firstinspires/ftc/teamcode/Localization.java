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
    public static void periodic(double deltaLF, double deltaRF, double deltaLB, double deltaRB) {
        double deltaX =
    }
}
