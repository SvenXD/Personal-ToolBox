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

import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TankDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Testing;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

@TeleOp
@Disabled
public class TestingContainer extends CommandOpMode {
    @Override
    public void initialize() {
        Testing testing = new Testing(telemetry,hardwareMap);
        GamepadEx chassisDriver = new GamepadEx(gamepad1);
        GamepadEx subsystemsDriver = new GamepadEx(gamepad2);

        //Tank----------------------------------


        chassisDriver.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(() -> testing.setPower());

        //------------------------------------------

        schedule(new RunCommand(() -> {

            telemetry.update();
        }));
//Test
    }



}
