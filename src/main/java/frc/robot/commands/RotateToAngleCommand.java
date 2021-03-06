/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class RotateToAngleCommand extends Command {

  private double Kp;
  private final double TOLERANCE = 2;
  private double angle;

  public RotateToAngleCommand( double angle) {
    requires(Robot.driveTrain);
    this.angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Kp= 0.03; 
  }

  @Override
  protected void execute() {
    
    double headingerror = Kp*Robot.driveTrain.getError(angle);

    if( headingerror > .5 ){
      headingerror = .5;
    }
  
    if( headingerror < -.5 ){
      headingerror = -.5;
    }

    double leftcommand =  -headingerror;
    double rightcommand = headingerror;

    Robot.driveTrain.TeleOpDrive(headingerror, -rightcommand);

    SmartDashboard.putNumber("LeftCommand", leftcommand);
    SmartDashboard.putNumber("RightCommand", rightcommand);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
     if( Math.abs(Robot.driveTrain.getError(angle)) < TOLERANCE ) {
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
