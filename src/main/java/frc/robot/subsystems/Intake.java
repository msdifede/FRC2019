/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.IntakeCommand;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  VictorSPX intakemotor;

  public Intake(VictorSPX motor){
    intakemotor = motor;
  }

  public void Move(double speed){
    System.out.println("running at speed" + speed);
    intakemotor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void initDefaultCommand() {
   // setDefaultCommand(new IntakeCommand());
  }
}
