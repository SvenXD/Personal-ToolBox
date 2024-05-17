package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class ArmSubsystem extends SubsystemBase {

    DcMotorEx armMotor;
    HardwareMap hardwareMap;
    Telemetry telemetry;


    public ArmSubsystem(Telemetry telemetry, HardwareMap hardwareMap){
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        armMotor = hardwareMap.get(DcMotorEx.class,"brazo");
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //Important to comment RUN AND STOP ENCODER to do it using power and not by positions

    }

    public void upArm(int pos, double power){
        armMotor.setTargetPosition(pos);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(power);
    }

    public void lowerArm(int pos, double power){
        armMotor.setTargetPosition(pos);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(power);
    }
    public void setPosition(int pos, double power){
        armMotor.setTargetPosition(pos);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(power);
    }

    public int getArmPose(){
        return armMotor.getCurrentPosition();
    }

        //Only works without encoder, delete line of code 26 and 28 in case to use
   /* public void setPower(double power){
        armMotor.setPower(power);
    }*/


    @Override
    public void periodic(){
        telemetry.addData( "brazo", armMotor.getCurrentPosition());
    }

}