// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.hal.PowerDistributionStickyFaults;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    pdp=new PowerDistribution();
    pdp.clearStickyFaults();
  }
  public void setRollers(double speed){
    rollers.set(speed);
  }
  public void intakeIn(){
    move.set(false);
  }
  public void intakeOut(){
    move.set(true);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Pressure", comp.getPressure());
    SmartDashboard.putBoolean("Release", comp.getPressureSwitchValue());
  }
}
