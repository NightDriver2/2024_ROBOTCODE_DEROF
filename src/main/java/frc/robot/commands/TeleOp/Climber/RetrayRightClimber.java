package frc.robot.commands.TeleOp.Climber;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class RetrayRightClimber extends Command {
  Climber climber;

  public RetrayRightClimber(Climber climber) {
    this.climber = climber;

    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    climber.rightPower(-.5);
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
