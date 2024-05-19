package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoSubsystem extends SubsystemBase {

        ServoEx servoDerecho,servoIzquierdo,servo3;
    HardwareMap hardwareMap;
    Telemetry telemetry;

    public ServoSubsystem(Telemetry telemetry, HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        servoDerecho = new SimpleServo(hardwareMap, "servoDer",0,180);
        servoIzquierdo = new SimpleServo(hardwareMap, "servoIzq",0,180);
        servo3 = new SimpleServo(hardwareMap,"servo3",0,180);

        servo3.setInverted(false);
        servoDerecho.setInverted(true);
    }
    public void close(){
     servoDerecho.setPosition(0.7);
     servoIzquierdo.setPosition(0.6);
    }
    public void open(){
        servoDerecho.setPosition(1);
        servoIzquierdo.setPosition(0.9);
    }
    public void customPose(double pose1,double pose2){
        servoDerecho.setPosition(pose1);
        servoIzquierdo.setPosition(pose2);
    }

    public void openPose() {          //Recommended values, 0.6
        servo3.setPosition(1);
    }
    public void closePose(){        //Recommended values 0.2
        servo3.setPosition(0);
    }
}