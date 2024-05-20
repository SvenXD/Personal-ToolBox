/*package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;

public class ArmCommand extends CommandBase {

    ArmSubsystem arm;

    GamepadEx chassisDriver;

    public ArmCommand(ArmSubsystem arm, GamepadEx chassisDriver){
        this.arm = arm;
        this.chassisDriver = chassisDriver;

        addRequirements(arm);
    }

    @Override
    public void execute(){
        if(chassisDriver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5){
            arm.setPower(1);
        }
        else if (chassisDriver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.0) {
            arm.setPower(0);
        }
        if(chassisDriver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.5){
        arm.setPower(-1)
;        }
        else if (chassisDriver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.0) {
           arm.setPower(0);
        }

    }
}*/