package org.firstinspires.ftc.teamcode.CommandBased.core;

import java.util.*;

public class CommandScheduler {

    private static final CommandScheduler instance = new CommandScheduler();

    private final Set<Command> scheduledCommands = new HashSet<>();
    private final Map<Subsystem, Command> requirements = new HashMap<>();

    private CommandScheduler() {}

    public static CommandScheduler getInstance() {
        return instance;
    }

    public void schedule(Command command) {
        if (isScheduled(command)) return;

        for (Subsystem subsystem : command.getRequirements()) {
            if (requirements.containsKey(subsystem)) {
                cancel(requirements.get(subsystem));
            }
        }

        for (Subsystem subsystem : command.getRequirements()) {
            requirements.put(subsystem, command);
        }

        scheduledCommands.add(command);
        command.initialize();
    }

    public void run() {
        for (Subsystem subsystem : requirements.keySet()) {
            subsystem.loop();
        }

        Iterator<Command> iterator = scheduledCommands.iterator();

        while (iterator.hasNext()) {
            Command command = iterator.next();
            command.execute();

            if (command.isFinished()) {
                command.end(false);
                iterator.remove();
                for (Subsystem subsystem : command.getRequirements()) {
                    requirements.remove(subsystem);
                }
            }
        }
    }

    public void cancel(Command command) {
        if (scheduledCommands.contains(command)) {
            command.end(true);
            scheduledCommands.remove(command);
            for (Subsystem subsystem : command.getRequirements()) {
                requirements.remove(subsystem);
            }
        }
    }

    public void cancelAll() {
        for (Command command : new HashSet<>(scheduledCommands)) {
            cancel(command);
        }
    }

    public boolean isScheduled(Command command) {
        return scheduledCommands.contains(command);
    }

    public boolean isOwned(Subsystem subsystem) {
        return requirements.containsKey(subsystem);
    }

    public boolean isOwnedBy(Subsystem subsystem, Command command) {
        return requirements.get(subsystem) == command;
    }
}