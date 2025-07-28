package org.firstinspires.ftc.teamcode.CommandBased.core;
import java.util.Collections;
import java.util.Set;

public class Wait implements Command {
    private final long durationMs;
    private long startTime;

    public Wait(double seconds) {
        if(seconds < 0) throw new IllegalArgumentException("Wait cannot be negative!");
        this.durationMs = (long)(seconds * 1000);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime >= durationMs;
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Collections.emptySet();
    }
}