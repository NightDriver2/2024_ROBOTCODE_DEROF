package frc.robot.commands.TeleOp.Climber;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class ExtendLeftClimber extends Command {
  Climber climber;

  public ExtendLeftClimber(Climber climber) {
    this.climber = climber;

    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    climber.leftPower(.7);
  }

  @Override
  public void end(boolean interrupted) {
    climber.leftPower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
