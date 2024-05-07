package frc.robot.commands.TeleOp.Intake.Index;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeAndIndexCommand extends Command {
  Intake intake;

  public IntakeAndIndexCommand(Intake intake) {
    this.intake = intake;

    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    /*if(intake.range() <= 300) {
      intake.setIntakePower(.85);
      intake.indexPower(.15);
    } else {
      intake.setIntakePower(0);
      intake.indexPower(0);
    }*/

    intake.setIntakePower(.85);
    intake.indexPower(.75);
    
    //intake.indexPower(.8);

  }
  @Override
  public void end(boolean interrupted) {
    intake.setIntakePower(0);
    intake.indexPower(0);
  }
}
