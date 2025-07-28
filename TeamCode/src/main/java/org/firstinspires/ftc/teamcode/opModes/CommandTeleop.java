package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.CommandBased.CustomGamepad.DeLButton;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.CommandBased.core.Subsystem;

public abstract class CommandTeleop extends OpMode {
    @Override
    public void init(){
        Subsystem.setGlobalParameters(hardwareMap, this);
        Robot.setGlobalParameters(this);
        DeLButton.init(gamepad1, gamepad2);
    }
    @Override
    public void loop() {
        DeLButton.update();
    }
}