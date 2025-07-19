package org.firstinspires.ftc.teamcode.CommandBased.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class Subsystem {
    public static HardwareMap hardwareMap;
    public static OpMode opMode;
    public static void setGlobalParameters(HardwareMap hardwareMap, OpMode opMode){
        Subsystem.hardwareMap = hardwareMap;
        Subsystem.opMode = opMode;
    }

    public abstract void init();

    public abstract void loop();
}