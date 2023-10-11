// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.CustomUtils;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class SwerveDrive extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrain m_subsystem;
  private XboxController controller;
 
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SwerveDrive(DriveTrain subsystem, XboxController controller) {
    m_subsystem = subsystem;
    this.controller=controller;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.drive(CustomUtils.applyDeadband(controller.getLeftX(),0.1),CustomUtils.applyDeadband(controller.getLeftY(),0.1), CustomUtils.applyDeadband(controller.getRightX(),0.1));
    SmartDashboard.putNumber("Left X", controller.getLeftX());
    SmartDashboard.putNumber("Left Y", controller.getLeftY());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
