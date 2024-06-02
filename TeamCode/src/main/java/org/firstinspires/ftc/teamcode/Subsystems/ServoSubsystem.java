package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoSubsystem extends SubsystemBase {

        ServoEx servoDerecho,servoIzquierdo;
    HardwareMap hardwareMap;
    Telemetry telemetry;

    public ServoSubsystem(Telemetry telemetry, HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        servoDerecho = new SimpleServo(hardwareMap, "servoDer",0,180);
        servoIzquierdo = new SimpleServo(hardwareMap, "servoIzq",0,180);
        servoDerecho.setInverted(true);
    }
    public void close(){
     servoDerecho.setPosition(0.35);
     servoIzquierdo.setPosition(0.35);
    }
    public void open(){
        servoDerecho.setPosition(0.45);
        servoIzquierdo.setPosition(0.45);
    }
    public void customPose(double pose1,double pose2){
        servoDerecho.setPosition(pose1);
        servoIzquierdo.setPosition(pose2);
    }

}