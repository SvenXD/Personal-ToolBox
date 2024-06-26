package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.Localizer;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

import java.util.List;


public class TankDriveSubsystem extends SubsystemBase {


    private final SampleTankDrive drive;

    private boolean vel = true;
    private boolean invert = true;

    public TankDriveSubsystem(SampleTankDrive drive) {
        this.drive = drive;
    }
    public void setPIDFCoefficients(DcMotorEx.RunMode mode, PIDFCoefficients coefficients) {
        drive.setPIDFCoefficients(mode, coefficients);
    }

    public void setPoseEstimate(Pose2d pose) { drive.setPoseEstimate(pose); }

    public SampleTankDrive getDrive(){
        return drive;
    }

    public void bajarVel(){
        this.vel = !vel;
    }

    public void invertFront(){
        this.invert = !invert;
    }



    public void update() { drive.update(); }

    public void updatePoseEstimate(){
        drive.updatePoseEstimate();
    }
    public void drive(double leftY, double rightX) {
        Pose2d poseEstimate = getPoseEstimate();

        drive.setWeightedDrivePower(
                new Pose2d(
                        vel ? leftY : leftY*.5,
                        0,
                        vel ? -rightX : -rightX*.45
                )
        );

    }
    public void setDrivePower(Pose2d drivePower) {
        drive.setDrivePower(drivePower);
    }

    public Pose2d getPoseEstimate() {
        return drive.getPoseEstimate();
    }

    public double rightTps(){
        return drive.getRightSpeed();
    }

    public double leftTps(){
        return  drive.getLeftSpeed();
    }



    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose) {
        return drive.trajectoryBuilder(startPose);
    }

    public  TrajectoryBuilder trajectoryBuilder(Pose2d startPose, boolean reversed){
        return drive.trajectoryBuilder(startPose, reversed);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d starpose, double starHeading){
        return drive.trajectoryBuilder(starpose, starHeading);
    }

    public void followTrajectory(Trajectory trajectory){
        drive.followTrajectoryAsync(trajectory);
    }

    public boolean isBusy(){
        return drive.isBusy();
    }

    public void turn(double radians){
        drive.turnAsync(radians);
    }

    public List<Double> getWheelVelocities() {
        return drive.getWheelVelocities();
    }

    public void stop() {
        drive(0, 0);
    }

    public Pose2d getPoseVelocity() {
        return drive.getPoseVelocity();
    }

    public Localizer getLocalizer() {
        return drive.getLocalizer();
    }




    }

