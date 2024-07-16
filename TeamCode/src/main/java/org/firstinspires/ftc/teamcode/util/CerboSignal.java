package org.firstinspires.ftc.teamcode.util;

public class CerboSignal {
    private final double mLeftMotor;
    private final double mRightMotor;
    private final boolean mBrakeMode;

    public CerboSignal(double left, double right) {
        this(left, right, false);
    }

    public CerboSignal(double left, double right, boolean brakeMode) {
        mLeftMotor = left;
        mRightMotor = right;
        mBrakeMode = brakeMode;
    }

    public static CerboSignal fromControls(double throttle, double turn) {
        return new CerboSignal(throttle - turn, throttle + turn);
    }

    public static final CerboSignal NEUTRAL = new CerboSignal(0, 0);
    public static final CerboSignal BRAKE = new CerboSignal(0, 0, true);

    public double getLeft() {
        return mLeftMotor;
    }

    public double getRight() {
        return mRightMotor;
    }

    public boolean getBrakeMode() {
        return mBrakeMode;
    }

    @Override
    public String toString() {
        return "L: " + mLeftMotor + ", R: " + mRightMotor + (mBrakeMode ? ", BRAKE" : "");
    }
}