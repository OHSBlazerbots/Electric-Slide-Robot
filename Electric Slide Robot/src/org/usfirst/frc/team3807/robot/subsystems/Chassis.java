package org.usfirst.frc.team3807.robot.subsystems;

import org.usfirst.frc.team3807.robot.commands.DriveWithJoystick;

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
	// RobotDrive robotDrive;
	Jaguar frontLeft, frontRight, backLeft, backRight;
	RobotDrive drive;

	public Chassis(int frontL, int frontR, int backL, int backR) {
		if (frontL != -1 && frontR != -1 && backL != -1 && backR != -1) {
			// robotDrive = new RobotDrive(frontL, backL, frontR, backR);
			frontLeft = new Jaguar(frontL);
			frontRight = new Jaguar(frontR);
			backLeft = new Jaguar(backL);
			backRight = new Jaguar(backR);
		}
		drive = new RobotDrive(frontLeft, frontRight, backLeft, backRight);
	}

	//calculates and returns the voltage for the front left motor
	public double getFrontLeftVoltage(double speed, double angle, double turn) {
		return speed * Math.sin(angle + (Math.PI / 4)) + turn;
	}

	//calculates and returns the voltage for the front right motor
	public double getFrontRightVoltage(double speed, double angle, double turn) {
		return speed * Math.cos(angle + (Math.PI / 4)) - turn;
	}

	//calculates and returns the voltage for the back left motor
	public double getBackLeftVoltage(double speed, double angle, double turn) {
		return speed * Math.cos(angle + (Math.PI / 4)) + turn;
	}

	//calculates and returns the voltage for the back right motor
	public double getBackRightVoltage(double speed, double angle, double turn) {
		return speed * Math.sin(angle + (Math.PI / 4)) - turn;
	}
	
	//implements previous methods to set speeds of all wheels
	//speed [-1,1]
	//angle [0, 2pi]
	//turn (changing direction) [-1,1]
	public void drive(double speed, double angle, double turn) {
		double[] values = {getFrontLeftVoltage(speed, angle, turn), getFrontRightVoltage(speed, angle, turn), getBackLeftVoltage(speed, angle, turn), getBackRightVoltage(speed, angle, turn)};
		double max = Math.abs(values[0]);
		for(double val:values)
		{
			if(Math.abs(val)>max)
				max = Math.abs(val);
		}
		
		frontLeft.set(values[0]/max *speed);
		frontRight.set(values[1]/max* speed);
		backLeft.set(values[2]/max*speed);
		backRight.set(values[3]/max*-speed);
	}

	//getting user input with joysticks
	public void driveWithJoystick(Joystick joystick) {
//		double speed = 0;
//		double angle = 0;
//		double turn = 0;
//		turn = joystick.getZ();
//		speed = Math.sqrt(Math.pow(joystick.getX(), 2)+ Math.pow(joystick.getY(),2));
//		angle = Math.atan(Math.abs(joystick.getY()/joystick.getX()));
//		this.drive(speed, angle, turn);
		//drive.mecanumDrive_Cartesian(joystick.getX(), joystick.getY(), joystick.getZ(), 0);
//		System.out.println("Front Left: " + frontLeft.get());
//		System.out.print(" Front Right: " + frontRight.get());
//		System.out.print(" Back Right: "+ backRight.get());
//		System.out.println(" Back Left: " + backLeft.get());
		double x = joystick.getX(); //changed to getX from getY
		double y = -joystick.getY(); //changed to getY from getX 
		double z = joystick.getZ();
		
		double one = x+y+z;
		double two = y-x-z;
		double three = y-x+z;
		double four = x+y-z;
		
		frontLeft.set(one / 3);
		frontRight.set(two / 3);
		backLeft.set(three / 3);
		backRight.set(-four/3);
		System.out.println("FL: " + one/3 + " FR: "+ two/3 + " BL: " + three/3 + " BR: " + -four/3);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoystick());
	}
//Example methods for driving with mechanum wheels
//They aren't used because they cannot easily be implemented with the joysticks
	public void DriveStraight(double speed) {
		frontLeft.set(speed);
		frontRight.set(speed);
		backLeft.set(speed);
		backRight.set(-speed);
	}

	// negative left; positive right
	public void DriveStrafe(double speed) {
		frontLeft.set(speed);
		frontRight.set(-speed);
		backLeft.set(-speed);
		backRight.set(-speed);
	}

	public void DiagonalRight(double speed) {
		frontLeft.set(speed);
		backRight.set(-speed);
	}

	public void DiagonalLeft(double speed) {
		frontRight.set(speed);
		backLeft.set(speed);
	}

	public void CenterTurn(double speed) {
		frontLeft.set(speed);
		frontRight.set(-speed);
		backLeft.set(speed);
		backRight.set(speed);
	}
	
	public void Halt()
	{
		frontLeft.set(0);
		frontRight.set(0);
		backLeft.set(0);
		backRight.set(0);
	}
}
