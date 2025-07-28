package org.firstinspires.ftc.teamcode.subsys;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandBased.core.Command;
import org.firstinspires.ftc.teamcode.CommandBased.core.CommandGroups.Sequential;
import org.firstinspires.ftc.teamcode.CommandBased.core.FunctionalCommand;
import org.firstinspires.ftc.teamcode.CommandBased.core.Subsystem;
import org.firstinspires.ftc.teamcode.CommandBased.core.Wait;

import java.util.Set;

public class DriveSubsys extends Subsystem {
    private DcMotor frontRight, frontLeft, backRight, backLeft;

    public DriveSubsys() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
    }
    public void init() {
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    double axial = -0;
    double lateral = 0;
    double yaw = 0;
    public void setDrive(double a, double l, double yw) {
        axial = a; lateral = l; yaw = yw;
    }
    public void loop() {
        double max;
        double frontLeftPower  = axial + lateral + yaw;
        double frontRightPower = axial - lateral - yaw;
        double backLeftPower   = axial - lateral + yaw;
        double backRightPower  = axial + lateral - yaw;
        max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));
        if (max > 1.0) {
            frontLeftPower  /= max;
            frontRightPower /= max;
            backLeftPower   /= max;
            backRightPower  /= max;
        }
        setPower(frontRightPower,frontLeftPower,backRightPower,backLeftPower);
    }

    public void setPower(double fr, double fl, double br, double bl) {
        frontRight.setPower(fr);
        frontLeft.setPower(fl);
        backRight.setPower(br);
        backLeft.setPower(bl);
    }
    public Command driveForwardCommand(double power, double seconds) {
        Command setPower = new FunctionalCommand(
                () -> setPower(power, power, power, power),
                () -> {},
                interrupted -> setPower(0, 0, 0, 0),
                () -> false,
                Set.of(this)
        );

        Command stopPower = new FunctionalCommand(
                () -> {},
                () -> {},
                interrupted -> setPower(0, 0, 0, 0),
                () -> true,
                Set.of(this)
        );

        return new Sequential(
                setPower,
                new Wait(seconds),
                stopPower
        );
    }
}
