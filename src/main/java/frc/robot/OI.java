/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.LineUpCommand;
import frc.robot.commands.OuttakeCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private Joystick driver,
                   operator;
  private Button lineup,
                 intake,
                 outtake;

  public OI(){
    driver = new Joystick(RobotMap.DRIVER_PORT);
    operator = new Joystick(RobotMap.OPERATOR_PORT);
    lineup = new JoystickButton(driver, 1);

    lineup.whileHeld(new LineUpCommand());
    while(operator.getPOV() == 0){
      new IntakeCommand();
      System.out.println(operator.getPOV());
  }
    while(operator.getPOV() == 4){
      new OuttakeCommand();
      System.out.println(operator.getPOV());
    }
  }

  public double getDriverLeftJoystick(){
    return driver.getRawAxis(RobotMap.DRIVER_LEFT_Y);
  }

  public double getDriverRightJoystick(){
    return driver.getRawAxis(RobotMap.DRIVER_RIGHT_Y);
  }
}
