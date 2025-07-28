package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Arrays;
import java.util.List;

public class MotorGroup {
    private List<Motor> _motors;
    public MotorGroup(Motor... motors) {
        _motors = Arrays.asList(motors);
    }
    public int getPosition(){ return _motors.get(0).getPosition(); }
    public double getVelocity(){ return _motors.get(0).getVelocity(); }
    public void setPower(double pwr){
        for(Motor m: _motors) m.setPower(pwr);
    }
    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zpb){
        for(Motor m: _motors) m.setZeroPowerBehavior(zpb);
    }
    public double getPower(){ return _motors.get(0).getPower(); }
    public double getCurrent(){ double sum=0; for(Motor m: _motors) sum+=m.getCurrent(); return sum; }
}