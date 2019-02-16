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
  int preView;

  public LineUpCommand() {
    requires(Robot.driveTrain);
    requires(Robot.limelight);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  //  preView = Robot.limelight.getView();
    
    Kp= 0.03;
    base = /*-(1 - Math.abs(Robot.limelight.getX()*.06))*/.3;
  }

  // Called repeatedly when this Command is scheduled to run
  // @Override
  // protected void execute() {
  //   int insight = (int) Robot.limelight.getV();
  //   double headingerror = Kp*Robot.limelight.getX();
  //   double leftcommand = 0, rightcommand = 0;

  //  if( insight == 1){
  //     if(Robot.limelight.getX() < 0){
  //       leftcommand =  Math.log(Math.abs(Robot.limelight.getX()));//1/Math.abs(Robot.limelight.getX());
  //       rightcommand = 1 - Math.log(Math.abs(Robot.limelight.getX()));//1/Math.abs(Robot.limelight.getX());
  //     }
  //     if(Robot.limelight.getX() > 0 ){
  //       leftcommand = 1 - Math.log(Math.abs(Robot.limelight.getX()));//1/Math.abs(Robot.limelight.getX());
  //       rightcommand = Math.log(Math.abs(Robot.limelight.getX()));//1/Math.abs(Robot.limelight.getX());
  //     }
  //     /*if(Math.abs(Robot.limelight.getX()) == 1){
  //       leftcommand = .5;
  //       rightcommand = .5;
  //     }*/
  // }
  //   Robot.driveTrain.TeleOpDrive(-leftcommand, -rightcommand);
  //   SmartDashboard.putNumber("LeftCommand", leftcommand);
  //   SmartDashboard.putNumber("RightCommand", rightcommand);
  // }

  @Override
  protected void execute() {
    Robot.limelight.setView(0);
    double kp2 = .10;

    double headingerror = Kp*Robot.limelight.getX();
    double leftcommand = 0, rightcommand = 0;
    double forwarderror = kp2*(8 - Robot.limelight.getArea());

    // base = base * kp2;
    // if( Robot.limelight.getArea() > 6){
    //   base = -base;
    // }


    leftcommand = forwarderror + headingerror;
    rightcommand = forwarderror - headingerror;

    
    //leftcommand = base + headingerror;
    //rightcommand = base - headingerror;
     
    Robot.driveTrain.TeleOpDrive(-leftcommand, -rightcommand);
    SmartDashboard.putNumber("LeftCommand", leftcommand);
    SmartDashboard.putNumber("RightCommand", rightcommand);
  }



  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
     if(Robot.limelight.seesTarget() ||  Robot.limelight.getArea() == 8){
       return false;
     }
     return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.TeleOpDrive(0, 0);
    Robot.limelight.setView(1);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.driveTrain.TeleOpDrive(0, 0);
    Robot.limelight.setView(1);
  }
}
