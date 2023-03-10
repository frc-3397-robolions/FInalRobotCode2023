// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm extends SubsystemBase {
  private PWMVictorSPX motor1;
  private PWMVictorSPX motor2;
  private PWMVictorSPX motor3;
  private CANSparkMax grabberMotor;
  private PIDController firstJointPID;
  private PIDController secondJointPID;
  private Encoder firstJointEncoder;
  private Encoder secondJointEncoder;
  private RelativeEncoder grabberEncoder;
  private AnalogInput testMeasDogAnalog;
  private DigitalInput limitSwitch;
  private AnalogInput ultrasonic;
  private AnalogInput ultrasonicPW;
  /** Creates a new Arm. */
  public Arm(Joystick pad) {
    motor1 = new PWMVictorSPX(Constants.ARM_MOTOR_1_ID);
    motor2 = new PWMVictorSPX(Constants.ARM_MOTOR_2_ID);
    motor3 = new PWMVictorSPX(5);
    grabberMotor = new CANSparkMax(9, MotorType.kBrushless);

    firstJointPID = new PIDController(0.01, 0.01, 0.001);
    firstJointEncoder = new Encoder(5, 4);
    firstJointPID.setTolerance(5, 5);
    firstJointEncoder.setDistancePerPulse(0.09465144224);
    firstJointPID.setSetpoint(0);   

    secondJointPID = new PIDController(0.01, 0.01, 0.001);
    secondJointEncoder = new Encoder(6, 7);
    secondJointPID.setTolerance(5, 5);
    secondJointPID.setSetpoint(0);
    //score high = -92.5 1000
    //socre mid = -50 530
    //out back = h>C=50 L>72 H>1325 L>19
    
    /*
     * AXISENSE-1 SERIES * VOLTAGE TILT SENSOR
     * Yellow-Black: Ground
     * Brown-Red: Analog Signal - Rio Analog In
     * White-Red: Voltage (8V+) - 12V VRM
     */
    testMeasDogAnalog = new AnalogInput(0);
    limitSwitch = new DigitalInput(0);
    ultrasonic = new AnalogInput(1);
    ultrasonicPW = new AnalogInput(3);
    firstJointPID.reset();
    
  }

  public void setBottomAngle(double angleDeg){
    firstJointPID.setSetpoint(angleDeg);
  }
  public void setTopAngle(double angleDeg){
    secondJointPID.setSetpoint(angleDeg);
  }
  public void zeroBottomEncoder(){
    firstJointEncoder.reset();
  }
  public boolean getBottomDone(){
    return firstJointPID.atSetpoint();
  }
  public boolean getTopDone(){
    return secondJointPID.atSetpoint();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    var bottompower = MathUtil.clamp(
      firstJointPID.calculate(firstJointEncoder.getDistance()),
      -0.5,
      0.5);
    var topPower = MathUtil.clamp(secondJointPID.calculate(secondJointEncoder.getDistance()),
    -0.5,
    0.5);
    motor1.set(bottompower);
    motor2.set(bottompower);
    motor3.set(topPower);
    // double dog1Offset = SmartDashboard.getNumber("DOG1 value", 2043);
    // double dog1Divisor = SmartDashboard.getNumber("DOG1 divisor", 9.0);
    SmartDashboard.putNumber("Top Power", topPower);
    SmartDashboard.putNumber("Power", firstJointPID.calculate(firstJointEncoder.getDistance()));
    SmartDashboard.putNumber("Encoder Position", firstJointEncoder.getDistance());
    SmartDashboard.putNumber("Seoncd Joint Pos", secondJointEncoder.getDistance());
    //SmartDashboard.putNumber("Grabber Encoder Pos", grabberEncoder.getPosition());
    // SmartDashboard.putNumber("DOG1 Angle", Math.round((testMeasDogAnalog.getValue()-dog1Offset)/dog1Divisor));
    // SmartDashboard.putBoolean("limit Switch", !limitSwitch.get());
    // SmartDashboard.putNumber("ultrasonic",ultrasonic.getValue()-55);
    // SmartDashboard.putNumber("ultrasonic Voltage",ultrasonic.getVoltage());
    // SmartDashboard.putNumber("ultrasonicPW Value",ultrasonicPW.getValue());
    // SmartDashboard.putNumber("ultrasonicPW Voltage",ultrasonicPW.getVoltage());
    
    // double inNum = SmartDashboard.getNumber("input number", 0);
    // SmartDashboard.putNumber("output number",inNum);
    // SmartDashboard.putNumber("DOG1 raw", testMeasDogAnalog.getValue());
    //Flat=2050 upside down = 3665 90 degrees = 2042
  }
}
