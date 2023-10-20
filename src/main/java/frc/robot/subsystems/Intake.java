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
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private PWMVictorSPX rollers;
  Solenoid move;
  private Compressor comp;
  private PowerDistribution pdp;
  public Intake() {
    rollers = new PWMVictorSPX(Constants.INTAKE_PWM_PORT);
    move = new Solenoid(PneumaticsModuleType.CTREPCM,0);
    comp = new Compressor(PneumaticsModuleType.CTREPCM);
    pdp = new PowerDistribution();
    SmartDashboard.putString("PDP Faults", pdp.getFaults().toString());
    pdp.clearStickyFaults();
  }
  public void setRollersButtonBox(Joystick box){
    if(box.getRawButton(1))
      rollers.set(0.4);
    else if(box.getRawButton(3))
      rollers.set(-0.3);
    else if(box.getRawButton(2))
      rollers.set(0.9);
    else
      rollers.set(0);

    if(box.getRawButton(0))
      move.set(false);
    if(box.getRawButton(5))
      move.set(true);
  }

  public void setRollersPower(double power){
    rollers.set(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Pressure", comp.getPressure());
    SmartDashboard.putBoolean("Release", comp.getPressureSwitchValue());
  }

  public Command intakeWithButtonBox(Joystick box){
    return run(() -> setRollersButtonBox(box));
  }

  public Command autoReverse(){
    return run(() -> setRollersPower(-0.3));
  }
}
