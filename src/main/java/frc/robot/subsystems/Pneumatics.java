/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Pneumatics extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DoubleSolenoid piston;
  public boolean isOpen;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public Pneumatics(int a, int b) {
    piston = new DoubleSolenoid(a, b);
	}
  
  public void open() {
    piston.set(DoubleSolenoid.Value.kForward);
    isOpen = true;
	}
	
	public void close() {
    piston.set(DoubleSolenoid.Value.kReverse);
    isOpen = false;
	}
	
	public void off() {
		piston.set(DoubleSolenoid.Value.kOff);
  }
  
  public boolean getisOpen(){
    return isOpen;
  }





}





