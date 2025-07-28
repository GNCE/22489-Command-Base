package org.firstinspires.ftc.teamcode.CommandBased.CustomGamepad;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.CommandBased.core.CommandScheduler;

public class DeLButton {
    private static Gamepad gamepad1;
    private static Gamepad gamepad2;

    public static void init(Gamepad gp1, Gamepad gp2) {
        gamepad1 = gp1;
        gamepad2 = gp2;
    }

    public static GamepadTriggerWrapper gamepad1() {
        return new GamepadTriggerWrapper(gamepad1);
    }

    public static GamepadTriggerWrapper gamepad2() {
        return new GamepadTriggerWrapper(gamepad2);
    }

    public static void update() {
        Triggers.updateAll();
    }
}
