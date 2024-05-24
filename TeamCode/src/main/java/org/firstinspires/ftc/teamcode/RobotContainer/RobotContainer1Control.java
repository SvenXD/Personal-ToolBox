package org.firstinspires.ftc.teamcode.RobotContainer;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.Commands.ArmCommand;
import org.firstinspires.ftc.teamcode.Commands.TankDriveCommand;

import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ServoSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TankDriveSubsystem;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

@TeleOp
public class RobotContainer1Control extends CommandOpMode {
    @Override
    public void initialize() {
        SampleTankDrive sampleTankDrive = new SampleTankDrive(hardwareMap);
        TankDriveSubsystem m_drive = new TankDriveSubsystem(sampleTankDrive);
        ArmSubsystem m_arm = new ArmSubsystem(telemetry, hardwareMap);
        ServoSubsystem m_servo = new ServoSubsystem(telemetry,hardwareMap);

        GamepadEx chassisDriver = new GamepadEx(gamepad1);
        GamepadEx subsystemsDriver = new GamepadEx(gamepad2);

        //Tank----------------------------------

        m_drive.setDefaultCommand(new TankDriveCommand(m_drive, chassisDriver::getLeftY
         ,chassisDriver::getRightX));

        //Arm------------------------------------

        chassisDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(() -> m_arm.setPosition(3350,0.7));

        chassisDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> m_arm.setPosition(50,0.7));

        chassisDriver.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(() -> m_arm.setPosition(m_arm.getArmPose()+3000,0.5))
                        .whenReleased(() -> m_arm.setPosition(m_arm.getArmPose(),0));


        chassisDriver.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(() -> m_arm.setPosition(m_arm.getArmPose()-3000,0.8))
                                .whenReleased(() -> m_arm.setPosition(m_arm.getArmPose(),0));

        chassisDriver.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(() -> m_arm.setPosition(m_arm.getArmPose()+100,0.5));

        chassisDriver.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new InstantCommand(m_drive::bajarVel));


        //Servo -------------------------------------------------------------

        new GamepadButton(new GamepadEx(gamepad1), GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(m_servo::open);

        new GamepadButton(new GamepadEx(gamepad1), GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(m_servo::close);

        new GamepadButton(new GamepadEx(gamepad1), GamepadKeys.Button.B)
                .toggleWhenPressed(m_servo::openPose, m_servo::closePose);
        //-------------------------------------------------------------------------------
        schedule(new RunCommand(() -> {
            m_drive.update();
            telemetry.addData("Heading", m_drive.getPoseEstimate().getHeading());
            telemetry.update();
        }));

    }



}
