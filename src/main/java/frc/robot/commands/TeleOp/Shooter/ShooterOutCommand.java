package frc.robot.commands.TeleOp.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ShooterOutCommand extends Command {
  Shooter shooter;
  Intake intake;

  public ShooterOutCommand(Shooter shooter, Intake intake) {
    this.shooter = shooter;
    this.intake = intake;

    addRequirements(shooter);
    addRequirements(intake);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    intake.indexPower(1);
    shooter.setShooterPower(-.4,-.4);
  }

  @Override
  public void end(boolean interrupted) {
    shooter.setShooterPower(0, 0);
    intake.indexPower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}

