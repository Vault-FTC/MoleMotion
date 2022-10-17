package org.firstinspires.ftc.teamcode.trajectory;

import org.firstinspires.ftc.teamcode.geometry.Pose2D;

import java.util.ArrayList;

public class Trajectory {
    private ArrayList<Pose2D> waypoints = new ArrayList();
    private Trajectory(Builder builder){
        this.waypoints = builder.waypoints;
    }
    public static class Builder {
        private Builder() {}
        private ArrayList<Pose2D> waypoints = new ArrayList();
        public Builder setWaypoint(Pose2D waypoint) {
            this.waypoints.add(waypoint);
            return this;
        }
        public Trajectory build() {
            return new Trajectory(this);
        }
    }
    public static Builder getBuilder() {
        return new Builder();
    }
}
