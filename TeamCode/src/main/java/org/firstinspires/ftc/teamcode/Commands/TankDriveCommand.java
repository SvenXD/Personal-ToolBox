package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.TankDriveSubsystem;

import java.util.function.DoubleSupplier;

public class TankDriveCommand extends CommandBase {

    private final TankDriveSubsystem drive;

    private final DoubleSupplier leftY, rightX;

    public TankDriveCommand(TankDriveSubsystem drive, DoubleSupplier leftY, DoubleSupplier rightX){
        this.drive = drive;
        this.leftY = leftY;
        this.rightX = rightX;



        addRequirements(drive);
    }

    @Override
    public void execute(){
        drive.drive(leftY.getAsDouble(), rightX.getAsDouble());
    }


}