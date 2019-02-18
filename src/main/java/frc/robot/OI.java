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
  private Button greenButton, redButton, pinkButton, purpleButton, foldFlower, frontUp, frontDown, rearUp,
  rearDown, driverA, intakebutton, driverB, driverY, driverX, l2lt, r2rt;


  public OI(){
    driver = new Joystick(RobotMap.DRIVER_PORT);
    operator = new Joystick(RobotMap.OPERATOR_PORT);
   
    setButtons();
    setCommands();
 

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
    

    l2lt = new JoystickAnalogButton(operator, 2, 0.5);
    r2rt = new JoystickAnalogButton(operator, 3, 0.5);

    greenButton = new JoystickButton(operator, 4);  //green button
    redButton = new JoystickButton(operator, 2); //red button
    foldFlower = new JoystickButton(operator, 5);  //topleft gray
    pinkButton = new JoystickButton(operator, 3); //pink button
    purpleButton = new JoystickButton(operator, 1); //purplebutton
    frontUp = new JoystickButton(operator, 7); //top 1
    frontDown = new JoystickButton(operator, 8); //top 2
    rearDown  = new JoystickButton(operator, 10); //top 4
    rearUp = new JoystickButton(operator, 9);  //top3
    intakebutton = new JoystickButton(operator, 6); //top right gray

    driverA = new JoystickButton(driver, 1);
    driverB = new JoystickButton(driver, 2);
    driverX = new JoystickButton( driver, 3);
    driverY = new JoystickButton(driver, 4);
    
  }

  public void setCommands(){
    intakebutton.whileHeld( new IntakeCommand());
    foldFlower.whileHeld(new OuttakeCommand());
    l2lt.whileHeld(new PushHatchCommand()); //new command
   
   
    driverA.whileHeld(new LineUpCommand());
    driverB.whileHeld(new DriveStraightCommand(.5));
    pinkButton.whenPressed(new ArrowOutNewCommand());
    pinkButton.whenReleased(new ArrowInCommand());
    purpleButton.whenPressed(new ArrowInCommand());
    //foldFlower.toggleWhenPressed(new DeanKamenSupportCommand());
    frontUp.whenPressed(new FrontRetractCommand());
    frontDown.whenPressed(new FrontExtendCommand());
    rearUp.whenPressed(new RearRetractCommand());
    rearDown.whenPressed(new RearExtendCommand());
    driverY.whenPressed(new LineUpStraightCommandGroup());

    greenButton.whenPressed(new SwitchViewCommand());
    redButton.whenPressed(new SwitchSideViewCommand());

    driverX.whenPressed(new RotateAngleCommand( 90));
  }

}
