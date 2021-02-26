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
    // ROMI's Left and Right predefined Motor Ports
    public static final int LEFT_MOTOR_PORT = 0;
    public static final int RIGHT_MOTOR_PORT = 1;

    // Encoder's A and B channels for Left and Right Encoders
    public static final int LEFT_ENCODER_A = 4;
    public static final int LEFT_ENCODER_B = 5;
    //
    public static final int RIGHT_ENCODER_A = 6;
    public static final int RIGHT_ENCODER_B = 7;


    // Additional Parameters - helpers with the math things
    // ROMI specific values
    public static final double WHEEL_DIA = 2.76;
    public static final double PULSES_PER_RESOLUTION = 119.76 * 12;
    // -> inches per pulse is
    public static final double INCHES_PER_PULSE = Math.PI * WHEEL_DIA / PULSES_PER_RESOLUTION;
    // inches of travel for the wheel to travel when the ROMI makes 1 resolution (on its center)
    public static final double WHEEL_TRACK = 5.2;
    public static final double INCHES_PER_TURN_DEGREE = Math.PI * WHEEL_DIA / 360;
    


}
