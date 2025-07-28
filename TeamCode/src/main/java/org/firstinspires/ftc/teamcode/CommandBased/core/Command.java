package org.firstinspires.ftc.teamcode.CommandBased.core;

import org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups.ParallelRace;

import java.util.Set;

public interface Command {
    void initialize();
    void execute();
    void end(boolean interrupted);
    boolean isFinished();
    Set<Subsystem> getRequirements();
    default Command setTimeout(long ms){
        return new ParallelRace(this, new Wait(ms));
    }
}