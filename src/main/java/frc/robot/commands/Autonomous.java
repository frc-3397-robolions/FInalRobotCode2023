// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Autonomous extends CommandBase {
  /** Creates a new Autonomous. */
  DriveTrain dt;
  double speed;
  public Autonomous(DriveTrain dt, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.dt=dt;
    this.speed=speed;
    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    dt.drive(0, speed, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    dt.drive(0,0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
