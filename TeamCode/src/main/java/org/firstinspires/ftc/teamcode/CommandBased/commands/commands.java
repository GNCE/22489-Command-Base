package org.firstinspires.ftc.teamcode.CommandBased.commands;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.CommandBased.core.Command;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups.Parallel;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups.Sequential;
import org.firstinspires.ftc.teamcode.CommandBased.core.Wait;
import org.firstinspires.ftc.teamcode.CommandBased.subsys.DriveSubsys;

public class commands {
    public static OpMode opMode;
    private static DriveSubsys driveSubsys;
    //add with more subsystems when they come
    public static void setGlobalParameters(OpMode opMode, DriveSubsys driveSubsys) {
        commands.driveSubsys = driveSubsys;
        commands.opMode = opMode;
    }
    public static Command waitSeconds(double sec) {
        return new Wait(sec);
    }
    public static Command driveForward() {
        return driveSubsys.driveForwardCommand(0.5, 2.0);
    }
    public static Command waitThenDrive() {
        return new Sequential(
                new Wait(0.1),
                driveSubsys.driveForwardCommand(0.5, 2)
        );
    }
    public static Command complexWaitDrive() {
        return new Sequential(
                new Parallel(
                    new Wait(1), driveSubsys.driveForwardCommand(0.2, 0.5)
                ),
                new Wait(0.1),
                driveSubsys.driveForwardCommand(0.5, 2)
        );
    }
}
