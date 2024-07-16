package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.TankDriveSubsystem;

import java.util.function.DoubleSupplier;

public class TankDriveCommand extends CommandBase {

    private final TankDriveSubsystem drive;
    private final Gamepad gamepad;
    private final DoubleSupplier leftY, rightX;

    public TankDriveCommand(TankDriveSubsystem drive, DoubleSupplier leftY, DoubleSupplier rightX, Gamepad gamepad){
        this.drive = drive;
        this.leftY = leftY;
        this.rightX = rightX;
        this.gamepad = gamepad;


        addRequirements(drive);
    }

    @Override
    public void execute(){
        drive.openLoop(gamepad);
    }


}