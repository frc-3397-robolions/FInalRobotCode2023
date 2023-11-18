// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.hal.PowerDistributionStickyFaults;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private PWMVictorSPX rollers;
  Solenoid solenoid;
  private Compressor comp;
  private PowerDistribution pdp;
  private boolean intakeOut = false;

  public Intake() {
    rollers = new PWMVictorSPX(Constants.INTAKE_PWM_PORT);
    solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    comp = new Compressor(PneumaticsModuleType.CTREPCM);
    pdp = new PowerDistribution();
    SmartDashboard.putString("PDP Faults", pdp.getFaults().toString());
    pdp.clearStickyFaults();
  }

  public void setRollersPower(double power) {
    rollers.set(power);
  }

  public void setIntake(boolean out) {
    intakeOut = out;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Pressure", comp.getPressure());
    SmartDashboard.putBoolean("Release", comp.getPressureSwitchValue());
    solenoid.set(intakeOut);
  }

  public CommandBase SetRollers(double power) {
    return runOnce(() -> setRollersPower(power));
  }

  public CommandBase SetIntake(boolean out) {
    return runOnce(() -> setIntake(out));
  }

  public CommandBase autoReverse() {
    return run(() -> setRollersPower(-0.3));
  }
}
