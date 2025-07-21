package org.firstinspires.ftc.teamcode.CommandBased.commands;

import org.firstinspires.ftc.teamcode.CommandBased.core.Command;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups.Parallel;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups.Sequential;
import org.firstinspires.ftc.teamcode.CommandBased.core.Wait;
import org.firstinspires.ftc.teamcode.CommandBased.subsys.DriveSubsys;

public class commands {
    public static Command driveForward(DriveSubsys drive) {
        return drive.driveForwardCommand(0.5, 2.0);
    }
    public static Command waitThenDrive(DriveSubsys drive) {
        return new Sequential(
                new Wait(0.1),
                drive.driveForwardCommand(0.5, 2)
        );
    }
    public static Command complexWaitDrive(DriveSubsys drive) {
        return new Sequential(
                new Parallel(
                    new Wait(1), drive.driveForwardCommand(0.2, 0.5)
                ),
                new Wait(0.1),
                drive.driveForwardCommand(0.5, 2)
        );
    }
}
