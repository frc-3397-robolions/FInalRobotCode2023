// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class MoveSegment extends CommandBase {
  /** Creates a new MoveSegment. */
  private Arm arm;
  //true = bottom false=top
  private boolean seg;
  private double angle;
  public MoveSegment(Arm sub, boolean seg, double angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sub);
    arm=sub;
    this.seg=seg;
    this.angle=angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(seg)
      arm.setBottomAngle(angle);
    else
      arm.setTopAngle(angle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {    
    if(seg && arm.getBottomDone())
      return true;

    if(!seg && arm.getTopDone())
      return true;

    return false;
  }
}
