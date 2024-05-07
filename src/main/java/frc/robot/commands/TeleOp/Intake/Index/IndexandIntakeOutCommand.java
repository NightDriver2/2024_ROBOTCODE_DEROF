package frc.robot.commands.TeleOp.Intake.Index;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IndexandIntakeOutCommand extends Command {
  Intake intake;
  
  public IndexandIntakeOutCommand(Intake intake) {
    this.intake = intake;

    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intake.indexPower(-.3);
    intake.setIntakePower(-.8);
  }

  @Override
  public void end(boolean interrupted) {
    intake.indexPower(0);
    intake.setIntakePower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
