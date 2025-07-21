package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CommandBased.DeLGamepad.DeLButton;
import org.firstinspires.ftc.teamcode.CommandBased.commands.commands;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups.Advancing;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups.Parallel;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups.Sequential;
import org.firstinspires.ftc.teamcode.CommandBased.core.Subsystem;
import org.firstinspires.ftc.teamcode.CommandBased.core.Wait;
import org.firstinspires.ftc.teamcode.CommandBased.subsys.DriveSubsys;
import static org.firstinspires.ftc.teamcode.CommandBased.commands.commands.*;

@TeleOp (name = "Command Based Tele")
public class CommandTeleop extends OpMode {
    private DriveSubsys driveSubsys;
    @Override
    public void init(){
        Subsystem.setGlobalParameters(hardwareMap, this);
        driveSubsys = new DriveSubsys();
        driveSubsys.init();
        commands.setGlobalParameters(this, driveSubsys);
        DeLButton.init(gamepad1, gamepad2);
        DeLButton.gamepad1().a().onTrue(new Sequential(new Wait(0.1), driveSubsys.driveForwardCommand(0.5, 0.1)));
        DeLButton.gamepad1().a().onTrue(
                new Advancing(DeLButton.gamepad1().a().getCondition(),
                        driveForward(),
                        waitSeconds(0.2),
                        complexWaitDrive()
                )
        );

    }
    @Override
    public void loop(){
        driveSubsys.setDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        DeLButton.update();
    }

}
