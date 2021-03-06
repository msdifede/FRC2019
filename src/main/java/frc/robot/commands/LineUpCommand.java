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

public class LineUpCommand extends Command {

  double Kp;
  double base;
  double Kp2;
  int preView;

  public LineUpCommand() {
    requires(Robot.driveTrain);
    requires(Robot.limelight);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  //  preView = Robot.limelight.getView();
    Robot.limelight.setView(0);
    Kp= 0.03;
    base = 0.3;
    Kp2 = 0.10;
  }

  @Override
  protected void execute() {
    

    double headingerror = Kp*Robot.limelight.getX();
    double leftcommand = 0, rightcommand = 0;
    double forwarderror = Kp2*(8 - Robot.limelight.getArea());

    leftcommand = forwarderror + headingerror;
    rightcommand = forwarderror - headingerror;

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
    //Robot.limelight.setView(1);
  }
}
