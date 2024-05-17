package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RollerSubsystem extends SubsystemBase {

    DcMotorEx rollerMotor;

    Telemetry telemetry;

    HardwareMap hardwareMap;

    public RollerSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        rollerMotor = hardwareMap.get(DcMotorEx.class, "roller");
        rollerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rollerMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void intakeRoller(){
        rollerMotor.setPower(1);
    }

    public void outakeRoller(){
        rollerMotor.setPower(-1);
    }

    public void stopRoller(){
        rollerMotor.setPower(0);
    }
}
