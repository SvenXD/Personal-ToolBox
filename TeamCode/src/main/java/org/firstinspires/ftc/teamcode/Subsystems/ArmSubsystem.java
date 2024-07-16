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
            armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }



    public void setPosition(int pos, double power){
        armMotor.setTargetPosition(pos);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(power);
    }

    public void setPower(){
        armMotor.setPower(1);   //Doesnt work if its using the position mode
    }


    public int  getArmPose(){
        return armMotor.getCurrentPosition();
    }
@Override
    public void periodic(){
        telemetry.addData("brazo",armMotor.getCurrentPosition());
        telemetry.addData("Tps",armMotor.getVelocity());
    }

}