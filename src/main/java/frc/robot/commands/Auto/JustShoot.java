package frc.robot.commands.Auto;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Shooter;

public class JustShoot extends SequentialCommandGroup {
  Shooter shooter;

  public JustShoot(Shooter shooter) {
    this.shooter = shooter;

    addRequirements(shooter);

    addCommands(
      new ParallelDeadlineGroup(new WaitCommand(2), new SequentialCommandGroup(new ShootNote(shooter))),
      new ParallelDeadlineGroup(new WaitCommand(1), new SequentialCommandGroup(new StopShooter(shooter)))
    );
  }
}
