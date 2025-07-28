package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.CommandBased.core.Command;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandScheduler;
import org.firstinspires.ftc.teamcode.CommandBased.core.Wait;
import org.firstinspires.ftc.teamcode.subsys.DriveSubsys;

public class Robot {
    public static OpMode opMode;
    private static DriveSubsys driveSubsys;
    public static void setGlobalParameters(OpMode opMode) {
        Robot.driveSubsys = driveSubsys;
        Robot.opMode = opMode;
    }
    public static Command waitSeconds(double sec) {
        return new Wait(sec);
    }
    public void reset(){
        CommandScheduler.getInstance().cancelAll();
    }
    public void run(){
        CommandScheduler.getInstance().run();
    }
    public void schedule(Command... commands){
        CommandScheduler.getInstance().scheduleAll(commands);
    }

}
