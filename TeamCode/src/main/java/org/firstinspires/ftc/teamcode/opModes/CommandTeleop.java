package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CommandBased.DeLGamepad.DeLButton;
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
        driveSubsys.init();
        DeLButton.init(gamepad1, gamepad2);
        DeLButton.gamepad1().a().onTrue(new DriveForwardCommand(driveSubsys, 2000));
    }
    @Override
    public void loop(){
        driveSubsys.setDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        DeLButton.update();
    }

}
