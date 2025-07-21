package org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups;

import org.firstinspires.ftc.teamcode.CommandBased.core.Command;
import org.firstinspires.ftc.teamcode.CommandBased.core.Subsystem;

import java.util.*;

public class ParallelRace implements Command {
    private final List<Command> allCommands;
    private final List<Command> activeCommands = new ArrayList<>();
    private final Set<Subsystem> requirements = new HashSet<>();
    private boolean finished = false;

    public ParallelRace(Command... commands) {
        this.allCommands = Arrays.asList(commands);
        for (Command c : commands) {
            requirements.addAll(c.getRequirements());
        }
    }

    @Override
    public void initialize() {
        activeCommands.clear();
        for (Command c : allCommands) {
            c.initialize();
            activeCommands.add(c);
        }
    }

    @Override
    public void execute() {
        Iterator<Command> iter = activeCommands.iterator();
        while (iter.hasNext()) {
            Command c = iter.next();
            if (!c.isFinished()) {
                c.execute();
                if (c.isFinished()) {
                    finished = true;
                    break;
                }
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        for (Command c : activeCommands) {
            c.end(true);
        }
        activeCommands.clear();
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return requirements;
    }
}
