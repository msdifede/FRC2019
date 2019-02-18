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

public class RotateAngleCommand extends Command {

  private double Kp;

  private final double TOLERANCE = 2;
  private double angle;
  private double startAngle;
  private double finishAngle;

  public RotateAngleCommand( double angle ) {
    requires(Robot.driveTrain);
    this.angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Kp= 0.03; //.003
    startAngle = Robot.driveTrain.getAngle();
    finishAngle = startAngle + angle;
  }

  @Override
  protected void execute() {
    
    double headingerror = Kp*getError();

    if( headingerror > .5 ){
      headingerror = .5;
    }
  
    if( headingerror < -.5 ){
      headingerror = -.5;
    }

    double leftcommand =  -headingerror;
    double rightcommand = headingerror;

    Robot.driveTrain.TeleOpDrive(-leftcommand, -rightcommand);

    SmartDashboard.putNumber("LeftCommand", leftcommand);
    SmartDashboard.putNumber("RightCommand", rightcommand);
  }

  //returns heading error between -180 and 180
  private double getError(){
    double err = Robot.driveTrain.getAngle() - finishAngle;
    if( err > 180 ){
      err = ( err - 180 );
    }else if ( err < -180){
      err = - (err + 180);
    }else {

    }
    return err;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
     if( Math.abs(getError()) < TOLERANCE ) {
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
