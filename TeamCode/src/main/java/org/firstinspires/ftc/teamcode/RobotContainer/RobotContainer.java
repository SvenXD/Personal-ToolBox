package org.firstinspires.ftc.teamcode.RobotContainer;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.TankDriveCommand;

import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TankDriveSubsystem;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

@TeleOp
public class RobotContainer extends CommandOpMode {
    @Override
    public void initialize() {
        SampleTankDrive sampleTankDrive = new SampleTankDrive(hardwareMap);
        TankDriveSubsystem m_drive = new TankDriveSubsystem(sampleTankDrive);
        ArmSubsystem m_arm = new ArmSubsystem(telemetry, hardwareMap);

        GamepadEx chassisDriver = new GamepadEx(gamepad1);
        GamepadEx subsystemsDriver = new GamepadEx(gamepad2);

        //Tank----------------------------------

        m_drive.setDefaultCommand(new TankDriveCommand(m_drive, chassisDriver::getLeftY
         ,chassisDriver::getRightX));

        //Arm------------------------------------
            //Arm with poses
              chassisDriver.getGamepadButton(GamepadKeys.Button.A)
                        .whenPressed(() -> m_arm.upArm(4000,1));

              chassisDriver.getGamepadButton(GamepadKeys.Button.B)
                          .whenPressed(() -> m_arm.lowerArm(0,1));

        //Manual arm with encoder

               chassisDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                     .whenPressed(() -> m_arm.setPosition(m_arm.getArmPose()+1000,1));

               chassisDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                       .whenPressed(() -> m_arm.setPosition(m_arm.getArmPose()-1000,1));

        /*      In case needed power and / or positions not working, nor backup code dead
        chassisDriver.getGamepadButton(GamepadKeys.Button.X)
                .whileHeld(() -> m_arm.setPower(1))
                .whenReleased(() -> m_arm.setPower(0));

        chassisDriver.getGamepadButton(GamepadKeys.Button.X)
                .whileHeld(() -> m_arm.setPower(-1))
                .whenReleased(() -> m_arm.setPower(0));
        */

        //-------------------------------------------------------------


        schedule(new RunCommand(() -> {
            m_drive.update();
            telemetry.addData("Heading", m_drive.getPoseEstimate().getHeading());
            telemetry.update();
        }));
//Test
    }



}
