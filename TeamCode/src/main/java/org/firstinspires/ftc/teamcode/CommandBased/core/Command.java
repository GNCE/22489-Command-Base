package org.firstinspires.ftc.teamcode.CommandBased.core;

import java.util.Set;

public interface Command {
    void initialize();
    void execute();
    void end(boolean interrupted);
    boolean isFinished();
    Set<Subsystem> getRequirements();
}