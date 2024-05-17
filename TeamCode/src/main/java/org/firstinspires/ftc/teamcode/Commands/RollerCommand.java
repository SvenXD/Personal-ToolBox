package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Subsystems.RollerSubsystem;

public class RollerCommand extends CommandBase {

    RollerSubsystem roller;

    GamepadEx chassisDriver;

    public RollerCommand(RollerSubsystem roller, GamepadEx chassisDriver){
        this.roller = roller;
        this.chassisDriver = chassisDriver;

        addRequirements(roller);
    }

    @Override
    public void execute(){
        if(chassisDriver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5){
            roller.intakeRoller();
        }
        else if (chassisDriver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.0) {
           roller.stopRoller();
        }
        if(chassisDriver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.5){
            roller.outakeRoller();
        }
    else if (chassisDriver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.0) {
        roller.stopRoller();
        }

    }
}



