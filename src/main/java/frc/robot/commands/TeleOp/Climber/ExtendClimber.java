package frc.robot.commands.TeleOp.Climber;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class ExtendClimber extends Command {
  Climber climber;
  DoubleSupplier leftTrigger;
  DoubleSupplier rightTrigger;

  public ExtendClimber(Climber climber, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
    this.climber = climber;
    this.leftTrigger = leftTrigger;
    this.rightTrigger = rightTrigger;


    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    climber.leftPower(leftTrigger.getAsDouble());
    climber.rightPower(rightTrigger.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    climber.leftPower(0);
    climber.rightPower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
