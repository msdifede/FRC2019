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
import frc.robot.subsystems.Limelight;

public class LineUpCommand extends Command {

  double Kp;
  double base;

  public LineUpCommand() {
    requires(Robot.driveTrain);
    requires(Robot.limelight);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Kp= 0.03;
    base = /*-(1 - Math.abs(Robot.limelight.getX()*.06))*/.45;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    int insight = (int) Robot.limelight.getV();
    double headingerror = Kp*Robot.limelight.getX();
    double leftcommand = 0, rightcommand = 0;

    if( insight == 1){
      if(Robot.limelight.getX() < 0){
        leftcommand =  Math.log(Math.abs(Robot.limelight.getX()));//1/Math.abs(Robot.limelight.getX());
        rightcommand = 1 - Math.log(Math.abs(Robot.limelight.getX()));//1/Math.abs(Robot.limelight.getX());
      }
      if(Robot.limelight.getX() > 0 ){
        leftcommand = 1 - Math.log(Math.abs(Robot.limelight.getX()));//1/Math.abs(Robot.limelight.getX());
        rightcommand = Math.log(Math.abs(Robot.limelight.getX()));//1/Math.abs(Robot.limelight.getX());
      }
      /*if(Math.abs(Robot.limelight.getX()) == 1){
        leftcommand = .5;
        rightcommand = .5;
      }*/
  }
    Robot.driveTrain.TeleOpDrive(-leftcommand, -rightcommand);
    SmartDashboard.putNumber("LeftCommand", leftcommand);
    SmartDashboard.putNumber("RightCommand", rightcommand);
  }
//qnman

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // if(Robot.limelight.getX() == 0){
    //   return true;
    // }
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
