package org.firstinspires.ftc.teamcode.CommandBased.core;

import java.util.*;

public class CommandScheduler {

    private static final CommandScheduler instance = new CommandScheduler();

    private final Set<Command> scheduledCommands = new HashSet<>();
    private final Map<Subsystem, Command> _requirements = new HashMap<>();
    private final Map<Subsystem, Command> _subsystems = new LinkedHashMap<>();

    private CommandScheduler() {}
    public static CommandScheduler getInstance() {
        return instance;
    }

    public void registerSubsystems(Subsystem... subsystems){
        for(Subsystem s: subsystems) _subsystems.put(s, null);
    }
    public void setDefaultCommand(Subsystem s, Command c){
        if(c.isFinished()) throw new IllegalArgumentException("Default Command cannot end!");
        // TODO: Should Default command require Subsystem? It is not checked in run(); For convention it might be better to force require
        _subsystems.put(s, c);
    }

    public void schedule(Command command) {
        if (isScheduled(command)) return;

        for (Subsystem subsystem : command.getRequirements()) {
            if (_requirements.containsKey(subsystem)) {
                cancel(_requirements.get(subsystem));
            }
        }

        for (Subsystem subsystem : command.getRequirements()) {
            _requirements.put(subsystem, command);
        }

        scheduledCommands.add(command);
        command.initialize();
    }

    public void run() {
        for (Subsystem subsystem : _subsystems.keySet()) {
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
                    _requirements.remove(subsystem);
                }
            }
        }

        // TODO: Default Commands?
    }

    public void cancel(Command command) {
        if (scheduledCommands.contains(command)) {
            command.end(true);
            scheduledCommands.remove(command);
            for (Subsystem subsystem : command.getRequirements()) {
                _requirements.remove(subsystem);
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
        return _requirements.containsKey(subsystem);
    }

    public boolean isOwnedBy(Subsystem subsystem, Command command) {
        return _requirements.get(subsystem) == command;
    }

    public void scheduleAll(Command[] commands) {
        for(Command c: commands) schedule(c);
    }
}