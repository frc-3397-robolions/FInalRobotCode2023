// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final static double SWERVE_MAX_VOLTS = 4.95;
    public final static double ROBOT_LENGTH = 32.375;
    public final static double ROBOT_WIDTH = 20.375;

    public static final int BACK_LEFT_ANGLE_ID = 8;
    public static final int BACK_LEFT_SPEED_ID = 7;
    public static final int BACK_RIGHT_ANGLE_ID = 6;
    public static final int BACK_RIGHT_SPEED_ID = 5;
    public static final int FRONT_LEFT_ANGLE_ID = 2;
    public static final int FRONT_LEFT_SPEED_ID = 1;
    public static final int FRONT_RIGHT_ANGLE_ID = 4;
    public static final int FRONT_RIGHT_SPEED_ID = 3;

    public final static int XB_CONTROLLER_PORT = 0;
    public static final int BOX_PORT = 1;
    public static final int ARM_MOTOR_1_ID = 3;
    public static final int ARM_MOTOR_2_ID = 4;
    public static final int INTAKE_PWM_PORT = 1;

    public static final double C = 10;
    public static final double controllerDeadband = 0.1;


}
