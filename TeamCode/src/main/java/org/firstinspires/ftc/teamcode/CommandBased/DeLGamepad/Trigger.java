package org.firstinspires.ftc.teamcode.CommandBased.DeLGamepad;
import org.firstinspires.ftc.teamcode.CommandBased.core.Command;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandScheduler;
import java.util.function.BooleanSupplier;

public class Trigger {
    private final BooleanSupplier condition;
    private boolean lastState = false;

    private Command onTrueCommand = null;
    private Command onFalseCommand = null;
    private Command whileTrueCommand = null;
    private Command whileFalseCommand = null;
    private Command toggleCommand = null;
    private boolean toggleState = false;
    private boolean toggleScheduled = false;

    public Trigger(BooleanSupplier condition) {
        this.condition = condition;
    }

    public Trigger onTrue(Command command) {
        this.onTrueCommand = command;
        return this;
    }

    public Trigger onFalse(Command command) {
        this.onFalseCommand = command;
        return this;
    }

    public Trigger whileTrue(Command command) {
        this.whileTrueCommand = command;
        return this;
    }

    public Trigger whileFalse(Command command) {
        this.whileFalseCommand = command;
        return this;
    }

    public Trigger toggleWhenPressed(Command command) {
        this.toggleCommand = command;
        return this;
    }

    public void update() {
        boolean current = condition.getAsBoolean();

        // Edge detection
        if (current && !lastState && onTrueCommand != null) {
            CommandScheduler.getInstance().schedule(onTrueCommand);
        }

        if (!current && lastState && onFalseCommand != null) {
            CommandScheduler.getInstance().schedule(onFalseCommand);
        }

        // While held
        if (current && whileTrueCommand != null) {
            CommandScheduler.getInstance().schedule(whileTrueCommand);
        }

        if (!current && whileFalseCommand != null) {
            CommandScheduler.getInstance().schedule(whileFalseCommand);
        }

        // Toggle
        if (toggleCommand != null && current && !lastState) {
            toggleState = !toggleState;
            toggleScheduled = toggleState;
        }

        if (toggleScheduled) {
            CommandScheduler.getInstance().schedule(toggleCommand);
            toggleScheduled = false;
        }

        lastState = current;
    }
}