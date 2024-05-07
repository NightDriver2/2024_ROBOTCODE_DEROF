package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  CANSparkMax frontShooter = new CANSparkMax(ShooterConstants.frontShoother_ID, MotorType.kBrushless);
  CANSparkMax backShooter = new CANSparkMax(ShooterConstants.backShoother_ID, MotorType.kBrushless);

  RelativeEncoder frontShooterEncoder = frontShooter.getEncoder();
  RelativeEncoder backShooterEncoder = backShooter.getEncoder();

  SparkPIDController frontController = frontShooter.getPIDController();
  SparkPIDController backController = backShooter.getPIDController();

  public Shooter() {
    frontShooter.restoreFactoryDefaults();
    backShooter.restoreFactoryDefaults();

    frontShooter.setInverted(false);
    backShooter.setInverted(true);

    frontShooter.setIdleMode(IdleMode.kCoast);
    backShooter.setIdleMode(IdleMode.kCoast);

    frontShooter.setSmartCurrentLimit(40);
    backShooter.setSmartCurrentLimit(40);

    frontController.setP(ShooterConstants.kP);
    frontController.setI(ShooterConstants.kI);
    frontController.setD(ShooterConstants.kD);
    //frontController.setFF(ShooterConstants.kFF);

    backController.setP(ShooterConstants.kP);
    backController.setI(ShooterConstants.kI);
    backController.setD(ShooterConstants.kD);
    //backController.setFF(ShooterConstants.kFF);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Front Shooter Speed", currentFrontShooterSpeed());
    SmartDashboard.putNumber("Back Shooter Speed", currentBackShooterSpeed());
  }

  public void setShooterPower(double frontPower, double backPower) {
    frontShooter.set(frontPower);
    backShooter.set(backPower);
  }
  
  public double currentFrontShooterSpeed() {
    return frontShooterEncoder.getVelocity();
  }
  
  public double currentBackShooterSpeed() {
    return backShooterEncoder.getVelocity();
  }
  
  public void frontSetPoint(double setPoint) {
    frontController.setReference(setPoint, ControlType.kVelocity);
  }
  
  public void backSetPoint(double setPoint) {
    backController.setReference(setPoint, ControlType.kVelocity);
  }

  public void setAutoShooter() {
    frontShooter.set(1);
    backShooter.set(1);
  }
  
  public void shootSpeakerAuto() {
    backSetPoint(3500);
    frontSetPoint(3500);
  }

  public void stopShooterAuto() {
    backSetPoint(0);
    frontSetPoint(0);
  }

  //InterpolatingDoubleTreeMap();
}