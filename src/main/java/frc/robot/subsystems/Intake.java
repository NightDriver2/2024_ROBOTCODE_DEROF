package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  CANSparkMax intakeMotor = new CANSparkMax(IntakeConstants.intake_ID, MotorType.kBrushless);

  CANSparkMax indexMotor = new CANSparkMax(IntakeConstants.indexMotor_ID, MotorType.kBrushless);

  SparkPIDController indexEncoder = indexMotor.getPIDController();

  Ultrasonic rangefinder = new Ultrasonic(1, 0);

  public Intake() {
    intakeMotor.restoreFactoryDefaults();
    
    intakeMotor.setInverted(false);
    
    intakeMotor.setIdleMode(IdleMode.kCoast);

    intakeMotor.setSmartCurrentLimit(35);

    indexMotor.restoreFactoryDefaults();

    indexMotor.setInverted(true);

    indexMotor.setIdleMode(IdleMode.kCoast);

    indexMotor.setSmartCurrentLimit(35);

    Ultrasonic.setAutomaticMode(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Distancia", range());
  }

  public void setIntakePower(double intakePower) {
    intakeMotor.set(intakePower);
  }
  
  public void indexPower(double indexPower) {
    indexMotor.set(indexPower);
  }

  public void indexSetpoint(double setPoint) {
    indexEncoder.setReference(setPoint, ControlType.kVelocity);
  }

  public double range(){
    double range = rangefinder.getRangeMM();
    return range;
  }
} 