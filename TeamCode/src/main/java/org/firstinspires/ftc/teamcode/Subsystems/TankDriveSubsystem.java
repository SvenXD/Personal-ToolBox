package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.Localizer;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Util;

import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.util.CerboSignal;
import org.firstinspires.ftc.teamcode.util.CheesyDriveHelper;

import java.util.List;


public class TankDriveSubsystem extends SubsystemBase {


    private final SampleTankDrive drive;
    private final CheesyDriveHelper cheese;

    public TankDriveSubsystem(SampleTankDrive drive, CheesyDriveHelper cheese) {
        this.drive = drive;
        this.cheese = cheese;
    }
    public void setPIDFCoefficients(DcMotorEx.RunMode mode, PIDFCoefficients coefficients) {
        drive.setPIDFCoefficients(mode, coefficients);
    }

    public void setPoseEstimate(Pose2d pose) { drive.setPoseEstimate(pose); }

    public SampleTankDrive getDrive(){
        return drive;
    }

    public void update() { drive.update(); }

    public void updatePoseEstimate(){
        drive.updatePoseEstimate();
    }
    public void drive(double leftY, double rightX) {
        Pose2d poseEstimate = getPoseEstimate();

        drive.setWeightedDrivePower(
                new Pose2d(
                        -leftY,
                        0,
                        -rightX
                )
        );

    }

    public void openLoop(Gamepad joystick){
        drive.setOpenLoop( cheese.cheesyDrive(-joystick.left_stick_y,-joystick.right_stick_x,false,false));
    }

    public void setDrivePower(Pose2d drivePower) {
        drive.setDrivePower(drivePower);
    }

    public Pose2d getPoseEstimate() {
        return drive.getPoseEstimate();
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