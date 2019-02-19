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
  private Button greenButton, redButton, pinkButton, purpleButton, eject, frontUp, frontDown, rearUp,
  rearDown, driverA, intakebutton, driverB, driverY, driverX, dehatch, r2rt, driverShoot, operatorJoystickUp,
  operatorJoystickDown;


  public OI(){
    driver = new Joystick(RobotMap.DRIVER_PORT);
    operator = new Joystick(RobotMap.OPERATOR_PORT);
   
    setButtons();
    setCommands();
 
  }

  public double getDriverLeftJoystick(){
    return driver.getRawAxis(RobotMap.DRIVER_LEFT_Y);
  }

  public double getDriverRightJoystick(){
    return driver.getRawAxis(RobotMap.DRIVER_RIGHT_Y);
  }

  public void setButtons(){
    
    operatorJoystickUp = new JoystickAnalogButton(operator, 1, .5); 
    operatorJoystickDown = new JoystickAnalogButton(operator, 1, -.5);

    eject = new JoystickButton(operator, 5);  //topleft gray
    dehatch = new JoystickAnalogButton(operator, 2 );
    pinkButton = new JoystickButton(operator, 3); 
    purpleButton = new JoystickButton(operator, 1);
    greenButton = new JoystickButton(operator, 4);  
    redButton = new JoystickButton(operator, 2); 
    //intakebutton = new JoystickButton(operator, 6); //top right gray
    r2rt = new JoystickAnalogButton(operator, 3 );

    frontUp = new JoystickButton(operator, 7); //top 1
    frontDown = new JoystickButton(operator, 8); //top 2
    rearUp = new JoystickButton(operator, 9);  //top 3
    rearDown  = new JoystickButton(operator, 10); //top 4

    driverA = new JoystickButton(driver, 1);
    driverB = new JoystickButton(driver, 2);
    driverX = new JoystickButton(driver, 3);
    driverY = new JoystickButton(driver, 4);

    driverShoot = new JoystickAnalogButton(driver, 3 );
  }

  public void setCommands(){
   
    eject.whileHeld(new PushHatchCommand());
    dehatch.whileHeld(new PushHatchCommand()); //new command

    pinkButton.whenPressed(new ArrowOutNewCommand());
    pinkButton.whenReleased(new ArrowInCommand());
    purpleButton.whenPressed(new ArrowInCommand());
    greenButton.whenPressed(new SwitchViewCommand());
    redButton.whenPressed(new SwitchSideViewCommand());
    //intakebutton.whenPressed( new IntakeBallCommand());

    frontUp.whenPressed(new FrontRetractCommand());
    frontDown.whenPressed(new FrontExtendCommand());
    rearUp.whenPressed(new RearRetractCommand());
    rearDown.whenPressed(new RearExtendCommand());

    driverA.whileHeld(new LineUpCommand());
    driverB.whileHeld(new DriveStraightCommand(.5));
    driverX.whenPressed(new DriveStraightDistanceCommand(40));
    driverY.whenPressed(new LineUpStraightCommandGroup());

    driverShoot.whenPressed( new CancelDriveCommand()); 
  }

}
