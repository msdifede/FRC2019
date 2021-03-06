/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveStraightDistanceCommand extends Command {

private double distance;
private double angle;
private double speed;
private double kP;

  public DriveStraightDistanceCommand(double distance) {
    requires(Robot.driveTrain);
    this.distance = distance;
    this.speed = .5;
    this.kP = .01;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    angle = Robot.driveTrain.getAngle();
    Robot.driveTrain.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double err = kP * (Robot.driveTrain.getAngle() - angle);

    double left = speed + err;
    double right = speed - err;

    Robot.driveTrain.TeleOpDrive(left, right);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if( Robot.driveTrain.getRightDistance() >= distance || Robot.driveTrain.getLeftDistance() >= distance ){
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.TeleOpDrive(0, 0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.driveTrain.TeleOpDrive(0, 0);
  }
}
