package org.firstinspires.ftc.teamcode.follower;

import org.firstinspires.ftc.teamcode.Localization;
import org.firstinspires.ftc.teamcode.geometry.Pose2D;

public class Drive {
    private static Drive instance = null;
    private PIDFollower follower;
    Localization localization;
    private Drive() {
        follower = new PIDFollower();
        localization = Localization.getInstance();
    }
    public void periodic() {
        localization.periodic();
        Pose2D pose = localization.getPose();
    }
    public static Drive getInstance() {
        if(instance == null) {
            instance = new Drive();
        }
        return instance;
    }
}
