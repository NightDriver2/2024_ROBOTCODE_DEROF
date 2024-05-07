package frc.robot.commands.TeleOp.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ShooterAMPCommand extends Command {
  Shooter shooter;
  Intake intake;
  
  public ShooterAMPCommand(Shooter shooter, Intake intake) {
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
    double currentspeed = shooter.currentBackShooterSpeed();

    shooter.frontSetPoint(650);
    shooter.backSetPoint(1400);    

    if(currentspeed >= 1380) {
      shooter.frontSetPoint(650);
      shooter.backSetPoint(1400);
      intake.indexPower(.65);
    } 

    //1 to 2 RELATION :)
    /*shooter.frontSetPoint(300);
    shooter.backSetPoint(500);    

    if(currentspeed >= 500) {
      shooter.frontSetPoint(300);
      shooter.backSetPoint(500);
      shooter.indexPower(.2);
    }*/

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

