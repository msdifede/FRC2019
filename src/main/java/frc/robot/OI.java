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
import frc.robot.commands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private Joystick driver, operator;
  private Button switchView, switchStream, openFlower, closeFlower, foldFlower, frontUp, frontDown, rearUp,
  rearDown, lineup, intakebutton, drivestraight, lineupcg;

  public OI(){
    driver = new Joystick(RobotMap.DRIVER_PORT);
  //  operator = new Joystick(RobotMap.OPERATOR_PORT);
   
  //  setButtons();
  //  setCommands();
 

  //   System.out.println(operator.getY());
  //   while(operator.getY() < 0){
  //     System.out.println("loop1: " + operator.getRawAxis(1));
  //     new IntakeCommand();
  // }
  //   while(operator.getRawAxis(1) > 0) {
  //     System.out.println("loop2: " + operator.getRawAxis(1));
  //     new OuttakeCommand();
  //   }
  }

  public double getDriverLeftJoystick(){
    return driver.getRawAxis(RobotMap.DRIVER_LEFT_Y);
  }

  public double getDriverRightJoystick(){
    return driver.getRawAxis(RobotMap.DRIVER_RIGHT_Y);
  }

  public void setButtons(){
    lineup = new JoystickButton(driver, 1);

    switchView = new JoystickButton(operator, 4);
    switchStream = new JoystickButton(operator, 2);
    foldFlower = new JoystickButton(operator, 5);
    openFlower = new JoystickButton(operator, 3);
    closeFlower = new JoystickButton(operator, 1);
    frontUp = new JoystickButton(operator, 7);
    frontDown = new JoystickButton(operator, 8);
    rearDown  = new JoystickButton(operator, 10);
    rearUp = new JoystickButton(operator, 9);
    drivestraight = new JoystickButton(driver, 2);
    intakebutton = new JoystickButton(operator, 6);
    lineupcg = new JoystickButton(driver, 4);
  }

  public void setCommands(){
    intakebutton.whileHeld( new IntakeCommand());
    foldFlower.whileHeld(new OuttakeCommand());
    lineup.whileHeld(new LineUpCommand());
    drivestraight.whileHeld(new DriveStraightCommand(.5));
    openFlower.whenPressed(new ArrowOutNewCommand());
    openFlower.whenReleased(new ArrowInCommand());
    closeFlower.whenPressed(new ArrowInCommand());
    //foldFlower.toggleWhenPressed(new DeanKamenSupportCommand());
    frontUp.whenPressed(new FrontRetractCommand());
    frontDown.whenPressed(new FrontExtendCommand());
    rearUp.whenPressed(new RearRetractCommand());
    rearDown.whenPressed(new RearExtendCommand());
    lineupcg.whenPressed(new LineUpStraightCommandGroup());

    switchView.whenPressed(new SwitchViewCommand());
    switchStream.whenPressed(new SwitchSideViewCommand());
  }

}
