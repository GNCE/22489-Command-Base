package org.firstinspires.ftc.teamcode.CommandBased.DeLGamepad;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadTriggerWrapper {
    private final Gamepad gamepad;

    public GamepadTriggerWrapper(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public Trigger a() { return Triggers.on(() -> gamepad.a); }
    public Trigger b() { return Triggers.on(() -> gamepad.b); }
    public Trigger x() { return Triggers.on(() -> gamepad.x); }
    public Trigger y() { return Triggers.on(() -> gamepad.y); }

    public Trigger leftBumper()  { return Triggers.on(() -> gamepad.left_bumper); }
    public Trigger rightBumper() { return Triggers.on(() -> gamepad.right_bumper); }

    public Trigger dpadUp()    { return Triggers.on(() -> gamepad.dpad_up); }
    public Trigger dpadDown()  { return Triggers.on(() -> gamepad.dpad_down); }
    public Trigger dpadLeft()  { return Triggers.on(() -> gamepad.dpad_left); }
    public Trigger dpadRight() { return Triggers.on(() -> gamepad.dpad_right); }

    // Extend with triggers, sticks, etc.
}
