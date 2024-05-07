package frc.robot.commands.TeleOp.Intake.Index;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeOutCommand extends Command {
  Intake intake;

  public IntakeOutCommand(Intake intake) {
    this.intake = intake;

    addRequirements(intake);
  }
  
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intake.setIntakePower(-.65);
  }

  @Override
  public void end(boolean interrupted) {
    intake.setIntakePower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}

