package frc.robot.commands.TeleOp.DriveTrain;
import java.util.function.DoubleSupplier;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class driveAimAtTarget extends Command {
  private final SwerveSubsystem SwerveSub;
  private DoubleSupplier translationX;
  private DoubleSupplier translationY;
  private double heading;
  private double lastGoodHeading;

  /** Creates a new driveAimAtTarget. */
  public driveAimAtTarget(SwerveSubsystem s_SwerveSubsystem, DoubleSupplier translationX, DoubleSupplier translationY) {
    this.SwerveSub = s_SwerveSubsystem;
    this.translationX = translationX;
    this.translationY = translationY;
    lastGoodHeading = 0;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_SwerveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(DriverStation.getAlliance().isPresent()){
      if(DriverStation.getAlliance().get() == DriverStation.Alliance.Red){
        SwerveSub.setVisionTargetID(1);
      }else{
        SwerveSub.setVisionTargetID(4);
      }
    }else{
      SwerveSub.setVisionTargetID(1 | 4);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(SwerveSub.isValidVisionTarget()){
      //heading = Math.pow(-SwerveSub.getVisionAngle()/30,3);
      heading = SwerveSub.getVisionAngle()/60;
      RobotContainer.setRightRumbleDriver(0);
    }else{
      System.out.println("Warning: Swerve Aim: Lost Target!");
      RobotContainer.setRightRumbleDriver(1);
      heading = 0;
    }
    lastGoodHeading = heading;

    //System.out.println("Target is: " + heading);
    //SwerveSub.driveCommand(translationX, translationY, heading);


    SwerveSub.getSwerve().drive(new Translation2d(Math.pow(translationX.getAsDouble(), 5) * SwerveSub.getSwerve().getMaximumVelocity(),
    Math.pow(translationY.getAsDouble(), 5) * SwerveSub.getSwerve().getMaximumVelocity()),
    heading * SwerveSub.getSwerve().getMaximumAngularVelocity(),true,false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.setRightRumbleDriver(0);
    //System.out.println("Warning: Swerve Aim: Target Lost!");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return !SwerveSub.isValidVisionTarget();
    return false;
  }
}