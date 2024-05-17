package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoSubsystem extends SubsystemBase {

    ServoEx servo;
    HardwareMap hardwareMap;
    Telemetry telemetry;

    public ServoSubsystem(Telemetry telemetry, HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        servo = new SimpleServo(hardwareMap,"servo",0,180);

        //Recommended to set the invertion
        servo.setInverted(false);
    }
        public void setPose(double pose){
        servo.setPosition(pose);
        }

        public void openPose() {          //Recommended values, 0.6
            servo.setPosition(1);
        }
        public void closePose(){        //Recommended values 0.2
        servo.setPosition(0);
        }

}