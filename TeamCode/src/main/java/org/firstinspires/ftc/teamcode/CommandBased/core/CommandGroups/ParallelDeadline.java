package org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups;

import org.firstinspires.ftc.teamcode.CommandBased.core.Command;
import org.firstinspires.ftc.teamcode.CommandBased.core.Subsystem;

import java.util.*;

public class ParallelDeadline implements Command {
    private final Command deadline;
    private final List<Command> others;
    private final List<Command> activeCommands = new ArrayList<>();
    private final Set<Subsystem> requirements = new HashSet<>();

    public ParallelDeadline(Command deadline, Command... others) {
        this.deadline = deadline;
        this.others = Arrays.asList(others);
        requirements.addAll(deadline.getRequirements());
        for (Command c : others) {
            requirements.addAll(c.getRequirements());
        }
    }

    @Override
    public void initialize() {
        deadline.initialize();
        activeCommands.clear();
        activeCommands.add(deadline);
        for (Command c : others) {
            c.initialize();
            activeCommands.add(c);
        }
    }

    @Override
    public void execute() {
        if (!deadline.isFinished()) {
            deadline.execute();
        }
        Iterator<Command> iter = others.iterator();
        while (iter.hasNext()) {
            Command c = iter.next();
            if (!c.isFinished()) {
                c.execute();
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        deadline.end(interrupted);
        for (Command c : others) {
            // Only interrupt others if still running
            if (!c.isFinished()) {
                c.end(true);
            } else {
                c.end(false);
            }
        }
        activeCommands.clear();
    }

    @Override
    public boolean isFinished() {
        return deadline.isFinished();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return requirements;
    }
}
