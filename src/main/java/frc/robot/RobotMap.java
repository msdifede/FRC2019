/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  //Controller Ports
  public static int DRIVER_PORT = 0;
  public static int OPERATOR_PORT = 1;

  //Controller Values
  
  //VICTOR Chasis Motor Constants
  public static int VICTOR_FRONT_RIGHT = 6; //4
  public static int VICTOR_FRONT_LEFT = 5;
  public static int VICTOR_REAR_RIGHT = 4; //6
  public static int VICTOR_REAR_LEFT = 7;

  //VICTOR Wrist Constants
  public static double WRIST_SPEED = 0.4;
  public static int VICTOR_WRIST_LEFT = 6;
	public static int VICTOR_WRIST_RIGHT = 7;

  //VICTOR Intake Constants
  public static double INTAKE_SPEED = 1.0;
  public static int VICTOR_INTAKE =  3;
  
	//Controller Values
	public static int DRIVER_LEFT_X = 0;
	public static int DRIVER_LEFT_Y = 1;
	public static int DRIVER_RIGHT_X = 4;
  public static int DRIVER_RIGHT_Y = 5;
  
  	//Controller Values
	public static int OPERATOR_LEFT_X = 0;
	public static int OPERATOR_LEFT_Y = 1;
	public static int OPERATOR_RIGHT_X = 4;
  public static int OPERATOR_RIGHT_Y = 5; 
  
}
