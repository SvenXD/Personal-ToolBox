package org.firstinspires.ftc.teamcode.Commands.Autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;


@Autonomous
        (name="Blue Auto", group="Linear Opmode")
@Disabled

public class AutoBlue extends LinearOpMode {


    private BNO055IMU       imu         = null;
    private double          robotHeading  = 0;
    private double          headingOffset = -150;
    private double          headingError  = 0;

    private double          desiredHeading = 0;

    private double          rightSetPoint = 0;

    private double          leftSetPoint = 0;

    private DcMotorEx rightDrive = null;private DcMotorEx leftDrive = null; private DcMotorEx motor3;

    ServoEx servoDerecho,servoIzquierdo;

    private double  targetHeading = 0;
    private double  driveSpeed    = 0;
    private double  turnSpeed     = 0;
    private double  leftSpeed     = 0;
    private double  rightSpeed    = 0;
    private int     leftTarget    = 0;
    private int     rightTarget   = 0;

    private boolean globalConstant = true;

    static final double     COUNTS_PER_MOTOR_REV    = 28 ;   // eg: GoBILDA 312 RPM Yellow Jacket
    static final double     DRIVE_GEAR_REDUCTION    = 15.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = 12;

    static final double     DRIVE_SPEED             = 0.9
            ;     // Max driving speed for better distance accuracy.
    static final double     TURN_SPEED              = 0.2;     // Max Turn speed to limit turn rate
    static final double     HEADING_THRESHOLD       = 1.0 ;

    static final double     P_TURN_GAIN            = 0.02;     // Larger is more responsive, but also less stable
    static final double     P_DRIVE_GAIN           = 0.03;


    @Override
    public void runOpMode() {

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        leftDrive = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightDrive = hardwareMap.get(DcMotorEx.class, "rightFront");
        motor3 = hardwareMap.get(DcMotorEx.class, "brazo");
        servoDerecho = new SimpleServo(hardwareMap, "servoDer",0,180);
        servoIzquierdo = new SimpleServo(hardwareMap, "servoIzq",0,180);

        servoDerecho.setInverted(true);

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        resetHeading();

        waitForStart();

        sleep(1000);
        driveUsingEncoder(1,10000);
        sleep(500);
        manualTurn(-1,90);
        sleep(500);
        driveUsingEncoder(0.5,1020);
        sleep(600);
        open();
        sleep(1000);


      /*  sleep(1000);
        setPosition(800,0.7);
        sleep(1000);
        driveUsingEncoder(0.9,620);
        sleep(400);                  //     ^
        manualTurn(-1,88);  //-1   |
        sleep(400);
        resetEncoder();
        sleep(100);
        useEncoder();
        sleep(300);
        driveUsingEncoder(0.5,350);
        sleep(  1000);
        driveUsingEncoder(0.5,800);
        sleep(1000);




        /*        waitForStart();
        driveStraight(DRIVE_SPEED,20,0);
        sleep(300);
        manualTurn(-1,-90);
        sleep(300);
        driveStraight(DRIVE_SPEED,20,-90);
        sleep(300);
        manualTurn(1,180);
        sleep(300);
        driveStraight(DRIVE_SPEED,30,180);
*/

    }
    public void setPosition(int pos, double power){
        motor3.setTargetPosition(pos);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setPower(power);
    }
    public void close(){
        servoDerecho.setPosition(0.25);
        servoIzquierdo.setPosition(0.25);
    }
    public void open(){
        servoDerecho.setPosition(0.55);
        servoIzquierdo.setPosition(0.55);
    }


    public void resetHeading() {
        // Save a new heading offset equal to the current raw heading.
        headingOffset = getRawHeading();
        robotHeading = 0;
    }
    public double getRawHeading() {
        Orientation angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return angles.firstAngle;
    }

    public double getgetRawHeading(boolean activate) {
        do {
            Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            return angles.firstAngle;
        }while(activate);
    }

    public void driveStraight(double maxDriveSpeed,
                              double distance,
                              double heading) {

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            int moveCounts = (int)(distance * COUNTS_PER_INCH);
            leftTarget = leftDrive.getCurrentPosition() + moveCounts;
            rightTarget = rightDrive.getCurrentPosition() + moveCounts;

            // Set Target FIRST, then turn on RUN_TO_POSITION
            leftDrive.setTargetPosition(leftTarget);
            rightDrive.setTargetPosition(rightTarget);

            leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set the required driving speed  (must be positive for RUN_TO_POSITION)
            // Start driving straight, and then enter the control loop
            maxDriveSpeed = Math.abs(maxDriveSpeed);
            moveRobot(maxDriveSpeed, 0);

            // keep looping while we are still active, and BOTH motors are running.
            while (opModeIsActive() &&
                    (leftDrive.isBusy() && rightDrive.isBusy())) {

                // Determine required steering to keep on heading
                turnSpeed = getSteeringCorrection(heading, P_DRIVE_GAIN);

                // if driving in reverse, the motor correction also needs to be reversed
                if (distance < 0)
                    turnSpeed *= -1.0;

                // Apply the turning correction to the current driving speed.
                moveRobot(driveSpeed, turnSpeed);

                // Display drive status for the driver.
                sendTelemetry(true);
            }

            // Stop all motion & Turn off RUN_TO_POSITION
            moveRobot(0, 0);
            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public double getSteeringCorrection(double desiredHeading, double proportionalGain) {
        targetHeading = desiredHeading;  // Save for telemetry

        // Get the robot heading by applying an offset to the IMU heading
        robotHeading = getRawHeading() - headingOffset;

        // Determine the heading current error
        headingError = targetHeading - robotHeading;

        // Normalize the error to be within +/- 180 degrees
        while (headingError > 180)  headingError -= 360;
        while (headingError <= -180) headingError += 360;

        // Multiply the error by the gain to determine the required steering correction/  Limit the result to +/- 1.0
        return Range.clip(headingError * proportionalGain, -1, 1);
    }

    private void sendTelemetry(boolean straight) {

        if (straight) {
            telemetry.addData("Motion", "Drive Straight");
            telemetry.addData("Target Pos L:R",  "%7d:%7d",      leftTarget,  rightTarget);
            telemetry.addData("Actual Pos L:R",  "%7d:%7d",      leftDrive.getCurrentPosition(),
                    rightDrive.getCurrentPosition());
        } else {
            telemetry.addData("Motion", "Turning");
        }

        telemetry.addData("Angle Target:Current", "%5.2f:%5.0f", targetHeading, robotHeading);
        telemetry.addData("Error:Steer",  "%5.1f:%5.1f", headingError, turnSpeed);
        telemetry.addData("Wheel Speeds L:R.", "%5.2f : %5.2f", leftSpeed, rightSpeed);

        telemetry.addData("Heading test",getRawHeading());
        telemetry.addData("Desired Heading test",desiredHeading);
        telemetry.addData("Right encoder pose",rightDrive.getCurrentPosition());
        telemetry.addData("Left encoder pose",leftDrive.getCurrentPosition());
        telemetry.addData("Desired encoder pose",rightSetPoint);
        telemetry.addData("Vel der", rightDrive.getVelocity());
        telemetry.addData("Vel izq", leftDrive.getVelocity());
        telemetry.update();
    }

    public void moveRobot(double drive, double turn) {
        driveSpeed = drive;     // save this value as a class member so it can be used by telemetry.
        turnSpeed  = turn;      // save this value as a class member so it can be used by telemetry.

        leftSpeed  = drive - turn;
        rightSpeed = drive + turn;

        // Scale speeds down if either one exceeds +/- 1.0;
        double max = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
        if (max > 1.0)
        {
            leftSpeed /= max;
            rightSpeed /= max;
        }

        leftDrive.setPower(leftSpeed);
        rightDrive.setPower(rightSpeed);
    }

    public void holdHeading(double maxTurnSpeed, double heading, double holdTime) {

        ElapsedTime holdTimer = new ElapsedTime();
        holdTimer.reset();

        // keep looping while we have time remaining.
        while (opModeIsActive() && (holdTimer.time() < holdTime)) {
            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(heading, P_TURN_GAIN);

            // Clip the speed to the maximum permitted value.
            turnSpeed = Range.clip(turnSpeed, -maxTurnSpeed, maxTurnSpeed);

            // Pivot in place by applying the turning correction
            moveRobot(0, turnSpeed);

            // Display drive status for the driver.
            sendTelemetry(false);
        }

        // Stop all motion;
        moveRobot(0, 0);
    }


    public void turnToHeading(double maxTurnSpeed, double heading) {

        // Run getSteeringCorrection() once to pre-calculate the current error
        getSteeringCorrection(heading, P_DRIVE_GAIN);

        // keep looping while we are still active, and not on heading.
        while (opModeIsActive() && (Math.abs(headingError) > HEADING_THRESHOLD)) {

            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(heading, P_TURN_GAIN);

            // Clip the speed to the maximum permitted value.
            turnSpeed = Range.clip(turnSpeed, -maxTurnSpeed, maxTurnSpeed);

            // Pivot in place by applying the turning correction
            moveRobot(0, turnSpeed);

            // Display drive status for the driver.
            sendTelemetry(false);
        }

        // Stop all motion;
        moveRobot(0,0);
    }

    public void manualTurn(double turnDirection, double heading){
        globalConstant = true;
        do {
            if (!isWithinThreshold(getgetRawHeading(true), heading, 29)) {
                leftDrive.setPower(turnDirection * -1);
                rightDrive.setPower(turnDirection);
                desiredHeading = heading;
            } else {
                leftDrive.setPower(0);
                rightDrive.setPower(0);
                globalConstant = false;
            }
        }while(globalConstant);
    }

    public boolean isWithinThreshold(double value, double target, double threshold){
        return Math.abs(value - target) <= threshold;
    }

    public void driveUsingEncoder(double power, double setPoint) {
        globalConstant = true;
        leftSetPoint = setPoint;
        rightSetPoint = setPoint;
        do {
            if (Math.abs(leftDrive.getCurrentPosition()) < setPoint && Math.abs(rightDrive.getCurrentPosition()) < setPoint) {
                leftDrive.setPower(0.7);
                rightDrive.setPower(0.6);
            } else {
                leftDrive.setPower(0);
                rightDrive.setPower(0);
                globalConstant = false;
            }
            sendTelemetry(true);
        }while (globalConstant) ;

    }

    public void resetEncoder(){
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void useEncoder(){
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }



}