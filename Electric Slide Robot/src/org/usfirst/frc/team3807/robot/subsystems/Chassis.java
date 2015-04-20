package org.usfirst.frc.team3807.robot.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//RobotDrive robotDrive;
	Jaguar frontLeft, frontRight, backLeft, backRight;
	Joystick joystick;
	
	
	public Chassis(int frontL, int frontR, int backL, int backR)
	{
		if(frontL != -1 && frontR != -1 && backL != -1 && backR != -1)
		{
			//robotDrive = new RobotDrive(frontL, backL, frontR, backR);
			frontLeft = new Jaguar(frontL);
			frontRight = new Jaguar(frontR);
			backLeft = new Jaguar(backL);
			backRight = new Jaguar(backR);
		}
	}
	
	public void DriveStraight(double speed)
	{
		frontLeft.set(speed);
		frontRight.set(speed);
		backLeft.set(speed);
		backRight.set(speed);
	}
	
	//negative left; positive right
	public void DriveStrafe(double speed)
	{
		frontLeft.set(speed);
		frontRight.set(-speed);
		backLeft.set(-speed);
		backRight.set(speed);	
	}
	
	public void DiagonalRight(double speed)
	{
		frontLeft.set(speed);
		backRight.set(speed);
	}
	
	public void DiagonalLeft(double speed)
	{
		frontRight.set(speed);
		backLeft.set(speed);
	}
	
	public void CenterTurn(double speed)
	{
		frontLeft.set(speed);
		frontRight.set(-speed);
		backLeft.set(speed);
		backRight.set(-speed);
	}
	
	public void Drive(double move, double turn)
	{
		
	}
	
	public void DriveWithJoystick(int j)
	{
		joystick = new Joystick(j);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

