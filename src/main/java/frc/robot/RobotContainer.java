// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.Forward;
import frc.robot.commands.Turn;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Define/Reference & Instantitate our DriveTrain class
  public static final DriveTrain driveTrain = new DriveTrain();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    SmartDashboard.putData("Fastest Robot", new Forward(23.5));
    SmartDashboard.putData("_1st Forward_", new Forward(13.2));
    SmartDashboard.putData("_2nd Forward_", new Forward(10.5));
    SmartDashboard.putData("_3nd Forward_", new Forward(6.5));
    SmartDashboard.putData("_1in Forward_", new Forward(1.0));
    SmartDashboard.putData("_0.5i Forward_", new Forward(0.5));
    SmartDashboard.putData("_1st Turn_", new Turn(265));
    SmartDashboard.putData("_2nd Turn_", new Turn(85));
    SmartDashboard.putData("_Half Turn_", new Turn(47.5));
//    SmartDashboard.putData("Forward 10", new Forward(10));
//    SmartDashboard.putData("Backward 10", new Forward(-10));
//    SmartDashboard.putData("Turn 90", new Turn(90));
//    SmartDashboard.putData("Turn 180", new Turn(180));
//    SmartDashboard.putData("Turn 270", new Turn(270));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return m_autoCommand;
    return new PrintCommand("message");
  }
}
