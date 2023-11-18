// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.commands.AutoIntake;
import frc.robot.commands.Autonomous;
import frc.robot.commands.SwerveDrive;
import frc.robot.commands.ZeroWheels;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SwerveModule;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private Constants c;

  private SwerveModule backRight;
  private SwerveModule backLeft;
  private SwerveModule frontRight;
  private SwerveModule frontLeft;

  private DriveTrain swerveDrive;

  private Intake intake;

  private XboxController xbC;
  private CommandJoystick operatorController;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    c = new Constants();
    xbC = new XboxController(Constants.XB_CONTROLLER_PORT);
    operatorController = new CommandJoystick(Constants.XB_CONTROLLER_PORT);

    backRight = new SwerveModule(c.BACK_RIGHT_ANGLE_ID, c.BACK_RIGHT_SPEED_ID, "br");
    backLeft = new SwerveModule(c.BACK_LEFT_ANGLE_ID, c.BACK_LEFT_SPEED_ID, "bl");
    frontRight = new SwerveModule(c.FRONT_RIGHT_ANGLE_ID, c.FRONT_RIGHT_SPEED_ID, "fr");
    frontLeft = new SwerveModule(c.FRONT_LEFT_ANGLE_ID, c.FRONT_LEFT_SPEED_ID, "fl");

    swerveDrive = new DriveTrain(backRight, backLeft, frontRight, frontLeft);
    swerveDrive.setDefaultCommand(new SwerveDrive(swerveDrive, xbC));

    intake = new Intake();

    SmartDashboard.putData("Zero Wheels", new ZeroWheels(swerveDrive));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    operatorController.button(1)
        .onTrue(intake.SetRollers(0.4))
        .onFalse(intake.SetRollers(0));

    operatorController.button(3)
        .onTrue(intake.SetRollers(-0.3))
        .onFalse(intake.SetRollers(0));

    operatorController.button(2)
        .onTrue(intake.SetRollers(0.9))
        .onFalse(intake.SetRollers(0));

    operatorController.button(5)
        .onTrue(intake.SetIntake(true));

    operatorController.button(6)
        .onTrue(intake.SetIntake(false));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new AutoIntake(intake)
        .withTimeout(1)
        .andThen(new Autonomous(swerveDrive, 0.3)
            .withTimeout(3.5))
        .andThen(new Autonomous(swerveDrive, 0));
  }
}
