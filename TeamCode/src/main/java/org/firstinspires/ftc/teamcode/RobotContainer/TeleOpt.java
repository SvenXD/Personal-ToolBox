package org.firstinspires.ftc.teamcode.RobotContainer;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.TankDriveCommand;

import org.firstinspires.ftc.teamcode.Subsystems.MotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TankDriveSubsystem;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

@TeleOp

public class TeleOpt extends CommandOpMode {
    @Override
    public void initialize() {

        MotorSubsystem m_motor = new MotorSubsystem(telemetry,hardwareMap);
        SampleTankDrive sampleTankDrive = new SampleTankDrive(hardwareMap);
        TankDriveSubsystem m_drive = new TankDriveSubsystem(sampleTankDrive);

        GamepadEx driver1 = new GamepadEx(gamepad1);
        GamepadEx driver2 = new GamepadEx(gamepad2);

        driver2.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(() -> m_motor.setPosition(50,0.4));

        driver2.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> m_motor.setPosition(m_motor.getMotorPosition()-3000,1))
                .whenReleased(() -> m_motor.setPosition(m_motor.getMotorPosition(),0));

        driver2.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(() -> m_motor.setPosition(m_motor.getMotorPosition()+3000,1))
                .whenReleased(() -> m_motor.setPosition(m_motor.getMotorPosition(),0));

        m_drive.setDefaultCommand(new TankDriveCommand(m_drive, driver1::getLeftY
                ,driver1::getRightX));

    }
}
