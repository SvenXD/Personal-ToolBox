package org.firstinspires.ftc.teamcode.RoboContainer;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.RollerCommand;
import org.firstinspires.ftc.teamcode.Commands.TankDriveCommand;

import org.firstinspires.ftc.teamcode.Subsystems.RollerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TankDriveSubsystem;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

@TeleOp
public class RobotContainer extends CommandOpMode {
    @Override
    public void initialize() {
        SampleTankDrive sampleTankDrive = new SampleTankDrive(hardwareMap);
        TankDriveSubsystem m_drive = new TankDriveSubsystem(sampleTankDrive);
        RollerSubsystem m_roller = new RollerSubsystem(hardwareMap,telemetry);

        GamepadEx chassisDriver = new GamepadEx(gamepad1);
        GamepadEx subsystemsDriver = new GamepadEx(gamepad2);

        //Tank-----------------------------------------------------------------------

        m_drive.setDefaultCommand(new TankDriveCommand(m_drive, chassisDriver::getLeftY
         ,chassisDriver::getRightX));

        //Roller---------------------------------------------------------------------
          m_roller.setDefaultCommand(new RollerCommand(m_roller,chassisDriver));  //Roller with bumpers
          //Intake Roller
          chassisDriver.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenPressed(m_roller::intakeRoller, m_roller::stopRoller);

        //Outake
        chassisDriver.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(m_roller::outakeRoller)
                .whenReleased(m_roller::stopRoller);
        //End----------------------------------------------------------------------------
        schedule(new RunCommand(() -> {
            m_drive.update();
            telemetry.addData("Heading", m_drive.getPoseEstimate().getHeading());
            telemetry.update();
        }));
    }
}
