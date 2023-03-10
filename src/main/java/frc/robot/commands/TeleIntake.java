// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class TeleIntake extends CommandBase {
  /** Creates a new TeleIntake. */
  private Intake sub;
  private XboxController xbC;
  private Joystick pad;
  public TeleIntake(Intake sub, XboxController xbC, Joystick pad) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.sub=sub;
    this.xbC=xbC;
    this.pad=pad;
    addRequirements(sub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(pad.getRawButton(1))
      sub.setRollers(0.3);
    else if(pad.getRawButton(3))
      sub.setRollers(-0.3);
    else if(pad.getRawButton(2)){
      sub.setRollers(0.8);
    }
    else
      sub.setRollers(0);
    if(pad.getRawButton(6)){
      sub.intakeIn();
    }
    if(xbC.getRawButton(5)){
      sub.intakeOut();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sub.setRollers(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
