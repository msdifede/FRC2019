/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.LimelightCommand;

/**
 * 
 * Add your docs here.
 */
public class Limelight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  NetworkTable table;
  NetworkTableEntry tx, ty, tv, ta;
  double x, y, v, area;

  public Limelight(){
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    tv = table.getEntry("tv");
    ta = table.getEntry("ta");
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new LimelightCommand());
  }

  public void readValues(){
     x = tx.getDouble(0.0);
     y = ty.getDouble(0.0);
     v = tv.getDouble(0.0);
     area = ta.getDouble(0.0);
  }

  public void postToDashboard(){
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightX", v);
    SmartDashboard.putNumber("LimelightArea", area);
  }
  
  public double getX(){
    readValues();
    return x;
  }

  public double getY(){
    return y;
  }

  public double getV(){
    readValues();  
    return v;
  }
  public double getArea(){
    return area;
  }



}
