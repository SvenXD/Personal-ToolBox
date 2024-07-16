package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MotorSubsystem  extends SubsystemBase {

    DcMotorEx motor1;
    HardwareMap hardwareMap;
    Telemetry telemetry;

    public MotorSubsystem(Telemetry telemetry, HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        motor1 = hardwareMap.get(DcMotorEx.class, "Arm");
            motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor1.setDirection(DcMotorSimple.Direction.REVERSE);
            motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setPower(double power){
        motor1.setPower(power);
    } //Cant be used with the mode of run using encoder

    public void setPosition(int pos, double power){
        motor1.setTargetPosition(pos);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor1.setPower(power);
    }

    public int getMotorPosition(){
        return motor1.getCurrentPosition();
    }

}
