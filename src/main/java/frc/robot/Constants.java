package frc.robot;
import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;


public final class Constants {
  
    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    
     public static final double DRIVETRAIN_TRACKWIDTH_METERS = Units.inchesToMeters(20.7109); // FIXME Measure and set trackwidth
     /**
      * The front-to-back distance between the drivetrain wheels.
      *
      * Should be measured from center to center.
      */
     public static final double DRIVETRAIN_WHEELBASE_METERS = Units.inchesToMeters(17.2109); // FIXME Measure and set wheelbase
 
 
     public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 7;//4; // FIXME Set front left module drive motor ID
     public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 6;//5; // FIXME Set front left module steer motor ID
     public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 3; // FIXME Set front left steer encoder ID
     public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(291); // FIXME Measure and set front left steer offset
 
     public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 8; // FIXME Set front right drive motor ID
     public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 9; // FIXME Set front right steer motor ID
     public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 2; // FIXME Set front right steer encoder ID
     public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(358); // FIXME Measure and set front right steer offset
 
     public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 2; // FIXME Set back left drive motor ID
     public static final int BACK_LEFT_MODULE_STEER_MOTOR = 3; // FIXME Set back left steer motor ID
     public static final int BACK_LEFT_MODULE_STEER_ENCODER = 0; // FIXME Set back left steer encoder ID
     public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(50.7); // FIXME Measure and set back left steer offset
 
     public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 5; // FIXME Set back right drive motor ID
     public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 4; // FIXME Set back right steer motor ID
     public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 1; // FIXME Set back right steer encoder ID
     public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(288); // FIXME Measure and set back right steer offset




  public static final double ROBOT_MASS = (104) * 0.453592; // 36lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag

  public static final class AutonConstants
  {
    public static final PIDConstants TRANSLATION_PID = new PIDConstants(5, 0, 0.75);
    public static final PIDConstants ANGLE_PID   = new PIDConstants(2.105, 0, 0.085);
  }

  public static final class Drivebase {
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static class OperatorConstants {
    public static final double LEFT_X_DEADBAND  = 0.1;
    public static final double LEFT_Y_DEADBAND  = 0.1;
    public static final double RIGHT_X_DEADBAND = 0.1;
    public static final double TURN_CONSTANT    = 6;
  }

  public static final class IntakeConstants {
    public static int intake_ID = 14;
    public static int indexMotor_ID = 11;
  }

  public static final class ShooterConstants {
    public static int backShoother_ID = 13;
    public static int frontShoother_ID = 12;

    public static final double kP = 0.012, //0.00025
    kI = 0,
    kD = 0.00015; //0.09
    //kFF = 0.00045;
    //kFF = 0.0025;

  }

  public static final class ClimberConstants {
    public static int leftClimber_ID = 15;
    public static int rightClimber_ID = 16;
    
    public static final double kP = 0,
    kI = 0,
    kD = 0;
  }

}
