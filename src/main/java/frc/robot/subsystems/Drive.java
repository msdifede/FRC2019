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
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveCommand;


public class Drive extends Subsystem {

  private VictorSPX frontLeft;
  private VictorSPX frontRight;
  private VictorSPX rearLeft;
  private VictorSPX rearRight;
  private AHRS ahrs;
  private double Kp;
  private Encoder right;
  private Encoder left;


  public Drive( VictorSPX fl, VictorSPX fr, VictorSPX rl, VictorSPX rr ){
    super();
    frontLeft = fl;
    frontRight = fr;
    rearLeft = rl;
    rearRight = rr;
    Kp = 0.7;
    right = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
    left = new Encoder(0, 1, true, Encoder.EncodingType.k4X);


    right.setDistancePerPulse(.06264);
    left.setDistancePerPulse(.06264);

    rearRight.follow(frontRight);
    frontRight.follow(frontLeft);
    frontLeft.setInverted(true);
    frontRight.setInverted(false);
    rearRight.setInverted(InvertType.FollowMaster);
    rearLeft.setInverted(InvertType.FollowMaster); 

    try{
      ahrs = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex ){
      DriverStation.reportError("Error instantiating nvX " + ex.getMessage(), true);
    }
  }
  
  public void TeleOpDrive(double left, double right){
    frontLeft.set(ControlMode.PercentOutput, Kp*left);
    frontRight.set(ControlMode.PercentOutput, Kp*right);

    SmartDashboard.putNumber("Left Encoder: ", getLeftEncoder());
    SmartDashboard.putNumber("Right Encoder: ", getRightEncoder());
    SmartDashboard.putNumber("Left Distance: ", getLeftDistance());
    SmartDashboard.putNumber("Right Distance: ", getRightDistance());
  }

  public double getRightEncoder(){
    return right.get();
  }

  public double getLeftEncoder(){
    return left.get();
  }

  public double getLeftDistance(){
    return left.getDistance();
  }

  public double getRightDistance(){
    return right.getDistance();
  }

  public void resetEncoders(){
    right.reset();
    left.reset();
  }

  public void resetGyro(){
    ahrs.reset();
  }

  public double getAngle(){
    return ahrs.getAngle();
  }

  public double getError( double desiredAngle){
    double err = ahrs.getAngle() - desiredAngle;
    if( err > 180 ){
      err = ( err - 180 );
    }else if ( err < -180){
      err = - (err + 180);
    }else {

    }
    return err;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
  }

}
