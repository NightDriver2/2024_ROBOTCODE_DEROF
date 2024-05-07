
package frc.robot.commands.TeleOp.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ShooterSpeakerCommand extends Command {
  Shooter shooter;
  Intake intake;
  
  public ShooterSpeakerCommand(Shooter shooter, Intake intake) {
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
    shooter.setShooterPower(.75, 1);
    intake.indexPower(1);   
    
    /*double currentspeed = shooter.currentFrontShooterSpeed();

    shooter.frontSetPoint(2000);
    shooter.backSetPoint(2000);

    if(currentspeed >= 1980) {
      shooter.frontSetPoint(2000);
      shooter.backSetPoint(2000);
      intake.indexPower(1);
    }

    //1 TO 2 RELATION :)
    /*shooter.frontSetPoint(5600);
    shooter.backSetPoint(3600);

    if(currentspeed >= 5600) {
      shooter.frontSetPoint(5600);
      shooter.backSetPoint(3600);
      shooter.indexPower(1);
    } */

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

