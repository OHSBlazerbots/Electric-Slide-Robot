package org.usfirst.frc.team3807.robot;

import edu.wpi.first.wpilibj.Joystick;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    /**
     *
     */
	//driver joystick
	private static Joystick joystick = new Joystick(RobotMap.JOYSTICK_PORT);
	
	public OI() {
		
    }
	
	//returns the driver joystick
	public static Joystick getJoystick()
	{
		return joystick;
	}
}
