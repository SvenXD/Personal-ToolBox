/*package org.firstinspires.ftc.teamcode.Unused;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Unused.RollerSubsystem1;

public class RollerCommand1 extends CommandBase {

    RollerSubsystem1 roller1;

    GamepadEx chassisDriver;

    public RollerCommand1(RollerSubsystem1 roller1, GamepadEx chassisDriver){
        this.roller1 = roller1;
        this.chassisDriver = chassisDriver;

        addRequirements(roller1);
    }

    @Override
    public void execute(){
        if(chassisDriver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5){
            roller1.intakeRoller();
        }
        else if (chassisDriver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.0) {
           roller1.stopRoller();
        }
        if(chassisDriver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.5){
            roller1.outakeRoller();
        }
    else if (chassisDriver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.0) {
        roller1.stopRoller();
        }


    }

}


        //ROLLER 2 --------------------------------------------------------------------------

     //   m_roller1.setDefaultCommand(new RollerCommand1(m_roller1,chassisDriver));

        //Intake
        /*58
        chassisDriver.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenPressed(m_roller2::intakeRoller, m_roller1::stopRoller);

        //Outake
        chassisDriver.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(m_roller2::outakeRoller)
                .whenReleased(m_roller2::stopRoller);*/


