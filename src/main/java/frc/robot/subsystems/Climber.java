// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.ShooterConstants;

public class Climber extends SubsystemBase {
  CANSparkMax leftClimber = new CANSparkMax(ClimberConstants.leftClimber_ID, MotorType.kBrushless);
  CANSparkMax rightClimber = new CANSparkMax(ClimberConstants.rightClimber_ID, MotorType.kBrushless);

  RelativeEncoder leftClimberEncoder = leftClimber.getEncoder();
  RelativeEncoder rightClimbEncoder = rightClimber.getEncoder();

  SparkPIDController leftController = leftClimber.getPIDController();
  SparkPIDController rightController = rightClimber.getPIDController();

  public Climber() {
    leftClimber.restoreFactoryDefaults();
    rightClimber.restoreFactoryDefaults();

    leftClimber.setInverted(false);
    rightClimber.setInverted(true);

    leftClimber.setIdleMode(IdleMode.kBrake);
    rightClimber.setIdleMode(IdleMode.kBrake);

    leftClimber.setSmartCurrentLimit(40);
    rightClimber.setSmartCurrentLimit(40);

    leftController.setP(ShooterConstants.kP);
    leftController.setI(ShooterConstants.kI);
    leftController.setD(ShooterConstants.kD);
    
    rightController.setP(ShooterConstants.kP);
    rightController.setI(ShooterConstants.kI);
    rightController.setD(ShooterConstants.kD);
    
  }

  @Override
  public void periodic() {

  }

  public void leftPower(double leftPower) {
    leftClimber.set(leftPower);
  }

  public void rightPower(double rightPower) {
    rightClimber.set(rightPower);
  }

}
