package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoSubsystem extends SubsystemBase {

    ServoEx servo3;
    HardwareMap hardwareMap;
    Telemetry telemetry;

    public ServoSubsystem(Telemetry telemetry, HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
     //   servoDerecho = new SimpleServo(hardwareMap, "servoDer",0,180);
       // servoIzquierdo = new SimpleServo(hardwareMap, "servoIzq",0,180);
        servo3 = new SimpleServo(hardwareMap,"servo3",0,180);

    //    servoDerecho.setInverted(true);
    }
    /*public void close(){
     servoDerecho.setPosition(0.2);
     servoIzquierdo.setPosition(0.2);
    }
    public void open(){
        servoDerecho.setPosition(0.6);
        servoIzquierdo.setPosition(0.6);
    }
*/
    public void grabFundation(){
        servo3.setPosition(1);
    }

    public void leaveFoundation(){
        servo3.setPosition(0.5);
    }


}