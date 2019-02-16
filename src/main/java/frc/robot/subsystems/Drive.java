/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem; 
import frc.robot.commands.DriveCommand;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drive extends Subsystem {

  private VictorSPX frontLeft;
  private VictorSPX frontRight;
  private VictorSPX rearLeft;
  private VictorSPX rearRight;


  public Drive( VictorSPX fl, VictorSPX fr, VictorSPX rl, VictorSPX rr ){
    super();
    frontLeft = fl;
    frontRight = fr;
    rearLeft = rl;
    rearRight = rr;


    rearRight.follow(frontRight);
    rearLeft.follow(frontLeft);

    frontLeft.setInverted(true); // pick CW versus CCW when motor controller is positive/green
    frontRight.setInverted(false); // pick CW versus CCW when motor controller is positive/green
   
    rearRight.setInverted(InvertType.FollowMaster);
    rearLeft.setInverted(InvertType.FollowMaster); // match whatever talon0 is
    //_victor0.setInverted(InvertType.OpposeMaster); // opposite whatever talon0 is
  }
  
  public void TeleOpDrive(double left, double right){
    frontLeft.set(ControlMode.PercentOutput, left);
    frontRight.set(ControlMode.PercentOutput, right);
   // rearLeft.set(ControlMode.PercentOutput, right);
   // rearRight.set(ControlMode.PercentOutput, -left);

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
  }
}
