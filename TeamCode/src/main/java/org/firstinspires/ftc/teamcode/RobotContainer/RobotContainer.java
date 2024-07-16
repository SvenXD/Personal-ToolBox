package org.firstinspires.ftc.teamcode.RobotContainer;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.TankDriveCommand;

import org.firstinspires.ftc.teamcode.Subsystems.TankDriveSubsystem;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.util.CheesyDriveHelper;

@TeleOp
public class RobotContainer extends CommandOpMode {
    @Override
    public void initialize() {
        SampleTankDrive sampleTankDrive = new SampleTankDrive(hardwareMap);
        CheesyDriveHelper cheese = new CheesyDriveHelper();
        TankDriveSubsystem m_drive = new TankDriveSubsystem(sampleTankDrive,cheese);

        GamepadEx chassisDriver = new GamepadEx(gamepad1);
        GamepadEx subsystemsDriver = new GamepadEx(gamepad2);

        //Tank----------------------------------

        m_drive.setDefaultCommand(new TankDriveCommand(m_drive, chassisDriver::getLeftY
         ,chassisDriver::getRightX,gamepad1));

        //------------------------------------------

        schedule(new RunCommand(() -> {
            m_drive.update();
            telemetry.addData("Heading", m_drive.getPoseEstimate().getHeading());
            telemetry.update();
        }));
//Test
    }



}
