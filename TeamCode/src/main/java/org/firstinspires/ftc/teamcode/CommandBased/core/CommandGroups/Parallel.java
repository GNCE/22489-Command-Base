package org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups;
import org.firstinspires.ftc.teamcode.CommandBased.core.Command;
import org.firstinspires.ftc.teamcode.CommandBased.core.Subsystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.*;

public class Parallel implements Command {
    private final List<Command> commands;
    private final List<Command> runningCommands = new ArrayList<>();

    public Parallel(Command... commands) {
        this.commands = Arrays.asList(commands);
    }

    @Override
    public void initialize() {
        runningCommands.clear();
        for (Command cmd : commands) {
            cmd.initialize();
            runningCommands.add(cmd);
        }
    }

    @Override
    public void execute() {
        Iterator<Command> iter = runningCommands.iterator();
        while (iter.hasNext()) {
            Command cmd = iter.next();
            cmd.execute();
            if (cmd.isFinished()) {
                cmd.end(false);
                iter.remove();
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        for (Command cmd : runningCommands) {
            cmd.end(interrupted);
        }
        runningCommands.clear();
    }

    @Override
    public boolean isFinished() {
        return runningCommands.isEmpty();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        Set<Subsystem> reqs = new HashSet<>();
        for (Command cmd : commands) {
            reqs.addAll(cmd.getRequirements());
        }
        return reqs;
    }
}
