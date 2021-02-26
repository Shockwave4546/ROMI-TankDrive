// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class Forward extends CommandBase {
  double distance;
  DriveTrain driveTrain;
  boolean f;

  /** Creates a new Forward. */
  public Forward(double inches) {
    distance = Math.abs(inches);
    if (inches > 0) {
      f = true;
    } else {
      f = false;
    }
    driveTrain = RobotContainer.driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // zero the encoder 
    driveTrain.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // start driving forward
    // driveTrain.drive(0.85, 0);
    if (f) {
      driveTrain.driveForward(0.85);
    } else {
      driveTrain.driveBackward(0.85);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // encoder_distant >= distance
    return Math.abs(driveTrain.getDistance()) >= distance;
  }
}
