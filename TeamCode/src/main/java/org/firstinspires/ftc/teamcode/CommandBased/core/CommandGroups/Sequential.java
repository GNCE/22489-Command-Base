package org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups;
import org.firstinspires.ftc.teamcode.CommandBased.core.Command;
import org.firstinspires.ftc.teamcode.CommandBased.core.Subsystem;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.*;

public class Sequential implements Command {
    private final List<Command> commands;
    private Iterator<Command> iterator;
    private Command currentCommand;

    public Sequential(Command... commands) {
        this.commands = Arrays.asList(commands);
    }

    @Override
    public void initialize() {
        iterator = commands.iterator();
        if (iterator.hasNext()) {
            currentCommand = iterator.next();
            currentCommand.initialize();
        }
    }

    @Override
    public void execute() {
        if (currentCommand == null) return;

        currentCommand.execute();

        if (currentCommand.isFinished()) {
            currentCommand.end(false);
            if (iterator.hasNext()) {
                currentCommand = iterator.next();
                currentCommand.initialize();
            } else {
                currentCommand = null;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (currentCommand != null) {
            currentCommand.end(interrupted);
        }
    }

    @Override
    public boolean isFinished() {
        return currentCommand == null;
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
