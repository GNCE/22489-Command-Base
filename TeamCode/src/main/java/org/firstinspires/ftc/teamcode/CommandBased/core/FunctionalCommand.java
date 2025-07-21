package org.firstinspires.ftc.teamcode.CommandBased.core;

import java.util.Collections;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class FunctionalCommand implements Command {
    private final Runnable onInitialize;
    private final Runnable onExecute;
    private final Consumer<Boolean> onEnd; // interrupted flag
    private final BooleanSupplier isFinished;
    private final Set<Subsystem> requirements;

    public FunctionalCommand(
            Runnable onInitialize,
            Runnable onExecute,
            Consumer<Boolean> onEnd,
            BooleanSupplier isFinished,
            Set<Subsystem> requirements
    ) {
        this.onInitialize = onInitialize != null ? onInitialize : () -> {};
        this.onExecute = onExecute != null ? onExecute : () -> {};
        this.onEnd = onEnd != null ? onEnd : (interrupted) -> {};
        this.isFinished = isFinished != null ? isFinished : () -> false;
        this.requirements = requirements != null ? requirements : Collections.emptySet();
    }

    @Override
    public void initialize() {
        onInitialize.run();
    }

    @Override
    public void execute() {
        onExecute.run();
    }

    @Override
    public void end(boolean interrupted) {
        onEnd.accept(interrupted);
    }

    @Override
    public boolean isFinished() {
        return isFinished.getAsBoolean();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return requirements;
    }
}
