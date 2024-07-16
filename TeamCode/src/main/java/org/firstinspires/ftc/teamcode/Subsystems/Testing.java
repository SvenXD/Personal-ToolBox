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


        motor2 = hardwareMap.get(DcMotorEx.class,"motor2");
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        motor3 = hardwareMap.get(DcMotorEx.class,"motor3");
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        motor4 = hardwareMap.get(DcMotorEx.class,"motor4");
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }



    public void setPower(){
        motor1.setPower(1);
        motor2.setPower(2);
        motor3.setPower(3);
        motor4.setPower(4);
    }



    @Override
    public void periodic(){
        telemetry.addData("Testingtps 1",motor1.getVelocity());
        telemetry.addData("testingtps 2",motor2.getVelocity());
        telemetry.addData("Testingtps 3",motor3.getVelocity());
        telemetry.addData("testingtps 4",motor4.getVelocity());
    }

}