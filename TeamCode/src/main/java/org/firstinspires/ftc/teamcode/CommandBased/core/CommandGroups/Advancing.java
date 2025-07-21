package org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups;

import org.firstinspires.ftc.teamcode.CommandBased.core.Command;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandScheduler;
import org.firstinspires.ftc.teamcode.CommandBased.core.Subsystem;

import java.util.List;
import java.util.Set;
import java.util.Collections;
import java.util.function.BooleanSupplier;

public class Advancing implements Command {
    private final List<Command> commands;
    private int currentIndex = -1;
    private Command currentCommand = null;
    private boolean lastButtonState = false;

    // The button state supplier must be passed so Advancing can detect presses internally
    private final BooleanSupplier buttonSupplier;

    public Advancing(BooleanSupplier buttonSupplier, Command... commands) {
        if (commands == null || commands.length == 0) {
            throw new IllegalArgumentException("Must provide at least one command");
        }
        this.commands = List.of(commands);
        this.buttonSupplier = buttonSupplier;
    }

    private void advance() {
        if (currentCommand != null && CommandScheduler.getInstance().isScheduled(currentCommand)) {
            CommandScheduler.getInstance().cancel(currentCommand);
        }
        currentIndex = (currentIndex + 1) % commands.size();
        currentCommand = commands.get(currentIndex);
        CommandScheduler.getInstance().schedule(currentCommand);
    }

    @Override
    public void initialize() {
        lastButtonState = buttonSupplier.getAsBoolean();
    }

    @Override
    public void execute() {
        boolean currentButtonState = buttonSupplier.getAsBoolean();
        if (currentButtonState && !lastButtonState) {
            // rising edge detected
            advance();
        }
        lastButtonState = currentButtonState;

        if (currentCommand != null) {
            currentCommand.execute();
            if (currentCommand.isFinished()) {
                currentCommand.end(false);
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
        return false; // never finishes on its own
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Collections.emptySet(); // you can improve this if needed
    }
}

