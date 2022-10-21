package org.firstinspires.ftc.teamcode.trajectory;

import java.util.ArrayList;

public class TrajectorySequence {
    private TrajectorySequence() {}
    private static ArrayList<Trajectory> trajectories = new ArrayList();
    public static void addTrajectory(Trajectory trajectory) {
        trajectories.add(trajectory);
    }
    public static Trajectory getTrajectory(int index) {
        return trajectories.get(index);
    }
    public static int getSize() {
        return trajectories.size();
    }
    public static int getIndex(Trajectory trajectory) {
        int i = 0;
        for (Trajectory traj : trajectories) {
            if (traj == trajectory) { //may have to use .equals() method
                return i;
            }
            i ++;
        }
        throw new IllegalArgumentException("Trajectory doesn't match anything in TrajectorySequence");
    }
}
