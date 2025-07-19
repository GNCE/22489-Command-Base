package org.firstinspires.ftc.teamcode.CommandBased.commands;
import org.firstinspires.ftc.teamcode.CommandBased.core.Command;
import org.firstinspires.ftc.teamcode.CommandBased.core.Subsystem;
import org.firstinspires.ftc.teamcode.CommandBased.subsys.DriveSubsys;

import java.util.Set;
import java.util.Collections;

public class DriveForwardCommand implements Command {
//EXAMPLE COMMAND, I DONT HAVE OTHER SUBSYSTEMS
    private final DriveSubsys drive;
    private final long durationMillis;
    private long startTime;

    public DriveForwardCommand(DriveSubsys drive, long durationMillis) {
        this.drive = drive;
        this.durationMillis = durationMillis;
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        drive.setPower(0.5, 0.5, 0.5, 0.5);
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime >= durationMillis;
    }

    @Override
    public void end(boolean interrupted) {
        drive.setPower(0, 0,0,0);
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Collections.singleton(drive);
    }
}
