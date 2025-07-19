package org.firstinspires.ftc.teamcode.CommandBased;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CommandBased.commands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandScheduler;
import org.firstinspires.ftc.teamcode.CommandBased.core.Subsystem;
import org.firstinspires.ftc.teamcode.CommandBased.subsys.DriveSubsys;

@TeleOp (name = "Command Based Tele")
public class CommandTeleop extends OpMode {
    private DriveSubsys driveSubsys;
    @Override
    public void init(){
        Subsystem.setGlobalParameters(hardwareMap, this);
        driveSubsys = new DriveSubsys();
    }
    @Override
    public void loop(){
        CommandScheduler.getInstance().run();
        if (gamepad1.a) {
            CommandScheduler.getInstance().schedule(new DriveForwardCommand(driveSubsys, 2000));
        }
    }

}
