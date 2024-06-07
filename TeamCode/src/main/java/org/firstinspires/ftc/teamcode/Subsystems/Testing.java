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

public class Testing extends SubsystemBase {

    DcMotorEx motor1; DcMotorEx motor2; DcMotorEx motor3; DcMotorEx motor4;

    HardwareMap hardwareMap;
    Telemetry telemetry;


    //TWO NEGATIVE

    public Testing(Telemetry telemetry, HardwareMap hardwareMap){
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        motor1 = hardwareMap.get(DcMotorEx.class,"motor1");
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor2 = hardwareMap.get(DcMotorEx.class,"motor2");
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor3 = hardwareMap.get(DcMotorEx.class,"motor3");
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor4 = hardwareMap.get(DcMotorEx.class,"motor4");
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }



    public void setPosition(int pos, double power){
        motor1.setTargetPosition(pos);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor1.setPower(power);


        motor2.setTargetPosition(pos);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setPower(power);

        motor3.setTargetPosition(pos);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setPower(power);

        motor4.setTargetPosition(pos);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setPower(power);

    }



    @Override
    public void periodic(){
        telemetry.addData("Testingtps 1",motor1.getVelocity());
        telemetry.addData("testingtps 2",motor2.getVelocity());
        telemetry.addData("Testingtps 3",motor3.getVelocity());
        telemetry.addData("testingtps 4",motor4.getVelocity());
    }

}