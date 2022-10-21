package org.firstinspires.ftc.teamcode.trajectory;

import org.firstinspires.ftc.teamcode.follower.Drive;
import org.firstinspires.ftc.teamcode.geometry.Pose2D;

import java.util.ArrayList;

public class Trajectory {
    private ArrayList<Pose2D> waypoints = new ArrayList();
    Drive drive = Drive.getInstance();
    private String key = null;
    private Trajectory(Builder builder){
        this.waypoints = builder.waypoints;
        this.key = builder.key;
        TrajectorySequence.addTrajectory(this);
    }
    public static class Builder {
        private Builder() {}
        private ArrayList<Pose2D> waypoints = new ArrayList();
        private String key;
        public Builder setWaypoint(Pose2D waypoint) {
            this.waypoints.add(waypoint);
            return this;
        }
        public Builder setKey(String key) {
            this.key = key;
            return this;
        }
        public Trajectory build() {
            if (key == null) {
                key = "trajectory" + (TrajectorySequence.getSize() + 1);
            }
            return new Trajectory(this);
        }
    }
    public static Builder getBuilder() {
        return new Builder();
    }
    public ArrayList<Pose2D> getWaypoints() {
        return this.waypoints;
    }
    public int getSize() {
        return this.waypoints.size();
    }
    public void followTrajectory() {
        drive.periodic();
    }
    public boolean isFinished() {
        return drive.isFinished(this);
    }
}
