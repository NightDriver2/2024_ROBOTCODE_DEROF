package frc.robot.commands.TeleOp.Climber;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class ExtendRightClimber extends Command {
  Climber climber;

  public ExtendRightClimber(Climber climber) {
    this.climber = climber;

    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    climber.rightPower(.7);
  }

  @Override
  public void end(boolean interrupted) {
    climber.rightPower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
