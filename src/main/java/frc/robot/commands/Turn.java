// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class Turn extends CommandBase {
  int countme;
  double inches, turnangle;
  boolean direction;
  DriveTrain driveTrain;

  /** Creates a new Turn. */
  public Turn(double angle) {
    if (angle >= 180) {
      direction = true; // Turn Left
      turnangle = angle - 180;
    } else {
      direction = false; // Turn Right
      turnangle = angle;
    }
    driveTrain = RobotContainer.driveTrain;
    addRequirements(driveTrain);

    // inches is the distance the encoder 'travels' - which is based on the dia of the entire robot (WHEEL_TRACK)
    inches = Math.abs(angle) * Math.PI * Constants.WHEEL_TRACK / 360.0;

    SmartDashboard.putString("T2Go", String.valueOf(turnangle));
    SmartDashboard.putNumber("CountMe", countme);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.reset();
    driveTrain.zeroHeading();
    countme = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Tank drive turning operation: Left go 1 direction and Right go opposite direction
    driveTrain.turn(direction);
    countme++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return driveTrain.getABSDistance() >= inches;
    double h = driveTrain.getHeading();
    SmartDashboard.putString("GotZ", String.valueOf(h));
    SmartDashboard.putNumber("CountMe", countme);
    return Math.abs(h) >= Math.abs(turnangle);
  }
}
