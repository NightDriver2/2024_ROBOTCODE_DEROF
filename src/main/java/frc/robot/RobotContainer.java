package frc.robot;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Auto.JustShoot;
import frc.robot.commands.TeleOp.Climber.ExtendClimber;
import frc.robot.commands.TeleOp.Climber.ExtendLeftClimber;
import frc.robot.commands.TeleOp.Climber.ExtendRightClimber;
import frc.robot.commands.TeleOp.Climber.RetrayLeftClimber;
import frc.robot.commands.TeleOp.Climber.RetrayRightClimber;
import frc.robot.commands.TeleOp.DriveTrain.AbsoluteDriveAdv;
import frc.robot.commands.TeleOp.DriveTrain.driveAimAtTarget;
import frc.robot.commands.TeleOp.Intake.Index.IndexCommand;
import frc.robot.commands.TeleOp.Intake.Index.IndexOutCommand;
import frc.robot.commands.TeleOp.Intake.Index.IndexandIntakeOutCommand;
import frc.robot.commands.TeleOp.Intake.Index.IntakeAndIndexCommand;
import frc.robot.commands.TeleOp.Intake.Index.IntakeCommand;
import frc.robot.commands.TeleOp.Intake.Index.IntakeOutCommand;
import frc.robot.commands.TeleOp.Shooter.ShooterAMPCommand;
import frc.robot.commands.TeleOp.Shooter.ShooterOutCommand;
import frc.robot.commands.TeleOp.Shooter.ShooterSpeakerCommand;
import frc.robot.commands.TeleOp.Shooter.ShooterTrapCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;

import com.pathplanner.lib.commands.PathPlannerAuto;


public class RobotContainer
{
  private static final XboxController driverController = new XboxController(0);
  private static final XboxController subsystemController = new XboxController(1);

  private SendableChooser<Command> auto = new SendableChooser<>();

  private Climber climber = new Climber(); 
  private Shooter shooter = new Shooter();
  private Intake intake = new Intake();
  private SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve/neo"));

  PathPlannerAuto blueSupportAuto = new PathPlannerAuto("Pancho");
  PathPlannerAuto blueCorssLine = new PathPlannerAuto("Raulito");
  JustShoot justShoot = new JustShoot(shooter);
  
  public RobotContainer()
  {
  //AUTO//
    auto.addOption("Dispara Papu", justShoot);
    auto.addOption("Support Blue Auto", blueSupportAuto);
    auto.addOption("Cross Line Blue Auto", blueCorssLine);
    auto.addOption("Nothing", null);

    SmartDashboard.putData("Select Autonomous", auto);

    configureBindings();
    
  //Chassis Driver//
    AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(drivebase,
    () -> MathUtil.applyDeadband(driverController.getLeftY(),
    OperatorConstants.LEFT_Y_DEADBAND),
    () -> MathUtil.applyDeadband(driverController.getLeftX(),
    OperatorConstants.LEFT_X_DEADBAND),
    () -> MathUtil.applyDeadband(driverController.getRightX(),
    OperatorConstants.RIGHT_X_DEADBAND),
                                                                   driverController::getYButtonPressed,
                                                                   driverController::getAButtonPressed,
                                                                   driverController::getXButtonPressed,
                                                                   driverController::getBButtonPressed);

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the desired angle NOT angular rotation
    Command driveFieldOrientedDirectAngle = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(driverController.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverController.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> driverController.getRightX(),
        () -> driverController.getRightY());

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the angular velocity of the robot
    
    Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(driverController.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverController.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> driverController.getRawAxis(4));
        
    
    Command driveFieldOrientedDirectAngleSim = drivebase.simDriveCommand(
        () -> MathUtil.applyDeadband(driverController.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverController.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> driverController.getRawAxis(4));

    drivebase.setDefaultCommand(
      !RobotBase.isSimulation() ? driveFieldOrientedAnglularVelocity : driveFieldOrientedDirectAngleSim);


  //Subsystem Driver//
    Command ClimberExtend = new ExtendClimber(
      climber,
      ()-> MathUtil.applyDeadband(subsystemController.getLeftY(), 0.2),
      ()-> MathUtil.applyDeadband(subsystemController.getRightY(), 0.2));

    climber.setDefaultCommand(ClimberExtend);
  }

  public static void setRightRumbleDriver(double rumble){
    driverController.setRumble(RumbleType.kRightRumble, rumble);
  }

  private void configureBindings()
  {
  
//Control 1:
    new JoystickButton(driverController, 1).onTrue((new InstantCommand(drivebase::zeroGyro)));
    //new JoystickButton(driverController, 3).onTrue(new InstantCommand(drivebase::addFakeVisionReading));
    new JoystickButton(driverController, 4).whileTrue(new driveAimAtTarget(drivebase, ()->MathUtil.applyDeadband(-driverController.getLeftY(), 0.05), 
                                                                    ()->MathUtil.applyDeadband(-driverController.getLeftX(), 0.05))); 
    new JoystickButton(driverController,
                       2).whileTrue(
        Commands.deferredProxy(() -> drivebase.driveToPose(
                                   new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
                              ));
    new JoystickButton(driverController, 3).whileTrue(new RepeatCommand(new InstantCommand(drivebase::lock, drivebase)));


//Control 2:
    //new JoystickButton(subsystemController, 3).whileTrue(new IndexCommand(intake));
    new JoystickButton(subsystemController, 5).whileTrue(new IndexOutCommand(intake));
    new JoystickButton(subsystemController, 1).whileTrue(new IntakeAndIndexCommand(intake));
    //new JoystickButton(subsystemController, 5).whileTrue(new IndexandIntakeOutCommand(intake));

    new JoystickButton(subsystemController, 4).whileTrue(new ShooterAMPCommand(shooter, intake));
    new JoystickButton(subsystemController, 2).whileTrue(new ShooterSpeakerCommand(shooter, intake));
    new JoystickButton(subsystemController, 6).whileTrue(new ShooterTrapCommand(shooter, intake));
    new JoystickButton(subsystemController, 3).whileTrue(new ShooterOutCommand(shooter, intake));
    
    //new JoystickButton(subsystemController, 9).whileTrue(new RetrayLeftClimber(climber));
    //new JoystickButton(subsystemController, 10).whileTrue(new RetrayRightClimber(climber));
    
    /*new JoystickButton(subsystemController, 3).whileTrue(new IntakeCommand(intake));*/
    //new JoystickButton(subsystemController, 2).whileTrue(new IntakeOutCommand(intake));

    //new JoystickButton(subsystemController, 9).whileTrue(new ExtendLeftClimber(climber));
    //new JoystickButton(subsystemController, 10).whileTrue(new ExtendRightClimber(climber));

  }

  public Command getAutonomousCommand()
  {
    //return null;
    return auto.getSelected();
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
}
