// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
//import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.sensors.RomiGyro;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  PWMVictorSPX leftMotor, rightMotor;
  Encoder leftEncoder, rightEncoder;
  DifferentialDrive diffDrive;
  RomiGyro romiGyro;
  // DifferentialDriveOdometry odometry;
  // Field2d field2d;

  //private final static double leftTurnMod = 0.97;
  //private final static double leftDriveMod = 0.97;
  private final static double leftTurnMod = 1.0;
  private final static double leftDriveMod = 0.82;
  private final static double rightTurnMod = 0.95;
  private final static double rightDriveMod = 1.0;
  
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    // Instantitating the Left and Right Motor with the Motor Ports as defined in the Constants.java file.
    leftMotor = new PWMVictorSPX(Constants.LEFT_MOTOR_PORT);
    rightMotor = new PWMVictorSPX(Constants.RIGHT_MOTOR_PORT);

    // 
    leftEncoder = new Encoder(Constants.LEFT_ENCODER_A, Constants.LEFT_ENCODER_B);
    rightEncoder = new Encoder(Constants.RIGHT_ENCODER_A, Constants.RIGHT_ENCODER_B);
    //
    leftEncoder.setDistancePerPulse(Constants.INCHES_PER_PULSE);
    rightEncoder.setDistancePerPulse(Constants.INCHES_PER_PULSE);

    // Let's just use a simple differential drive train
    diffDrive = new DifferentialDrive(leftMotor, rightMotor);
    // specific for ROMI only
    diffDrive.setRightSideInverted(true);
    diffDrive.setSafetyEnabled(false);

    romiGyro = new RomiGyro();
    // odometry = new DifferentialDriveOdometry(romiGyro.getRotation2d());
    // SmartDashboard.putData("Field", field2d);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // odometry.update(romiGyro.getRotation2d(), leftEncoder.getDistance(), rightEncoder.getDistance());
    // field2d.setRobotPose(getPose());
    SmartDashboard.putString("2d", String.valueOf(romiGyro.getRotation2d().getDegrees()));
    SmartDashboard.putString("Z Angle", String.valueOf(romiGyro.getAngleZ()));
  }

  //public Pose2d getPose() {
    //return odometry.getPoseMeters();
  //}

  // Reset is to zero the 2 encoders
  public void reset() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public void drive(double speed, double direction) {
    diffDrive.arcadeDrive(speed, direction, false);
  }

  public void driveForward(double speed) {
    // for now, hardcode the rough modifier after figuring out what it should from encoders value 
    // diffDrive.tankDrive(leftDriveMod * speed, speed, false);
    diffDrive.tankDrive(leftDriveMod * speed, rightDriveMod * speed, false);
  }

  public void driveBackward(double speed) {
    diffDrive.tankDrive(-1 * leftDriveMod * speed, -1 * rightDriveMod * speed, false);
  }

  public void turn(boolean direction) {
    double turnSpeed, leftS, rightS;
    // Direction = true, turn Left, otherwise turn Right;
    turnSpeed = 0.10;
    if (direction) {
      leftS = leftTurnMod * turnSpeed;
      rightS = -1 * turnSpeed;
    } else {
      leftS = -1 * leftTurnMod * turnSpeed;
      rightS = turnSpeed;
    }
    diffDrive.tankDrive(leftS, rightS, false);
  }

  public void stop() {
    driveForward(0);
  }

  public double getLeftDistance() {
    return leftEncoder.getDistance();
  }

  public double getRightDistance() {
    return rightEncoder.getDistance();
  }

  public double getDistance() {
    return (leftEncoder.getDistance() + rightEncoder.getDistance())/2;
  }

  public double getABSDistance() {
    return (Math.abs(leftEncoder.getDistance()) + Math.abs(rightEncoder.getDistance()))/2;
  }

  public void zeroHeading() {
    romiGyro.reset();
  }

  public double getHeading() {
    return romiGyro.getRotation2d().getDegrees();
  }

  public double getZHeading() {
    return romiGyro.getAngleZ();
  }

  public double getTurnRate() {
    return romiGyro.getRate();
  }
}
