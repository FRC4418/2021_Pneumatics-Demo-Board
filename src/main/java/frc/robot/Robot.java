/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * This is a sample program showing the use of the solenoid classes during operator control. Three
 * buttons from a joystick will be used to control two solenoids: One button to control the position
 * of a single solenoid and the other two buttons to control a double solenoid. Single solenoids can
 * either be on or off, such that the air diverted through them goes through either one channel or
 * the other. Double solenoids have three states: Off, Forward, and Reverse. Forward and Reverse
 * divert the air through the two channels and correspond to the on and off of a single solenoid,
 * but a double solenoid can also be "off", where the solenoid will remain in its default power off
 * state. Additionally, double solenoids take up two channels on your PCM whereas single solenoids
 * only take a single channel.
 */
public class Robot extends TimedRobot {
	private final Joystick m_stick = new Joystick(0);

	// Compressor object on CAN ID 0
	private final Compressor compressor = new Compressor(0);

	// DoubleSolenoid corresponds to a double solenoid.
	private final DoubleSolenoid m_smallSolenoid =
		new DoubleSolenoid(4, 5);
			// DoubleSolenoid corresponds to a double solenoid.

	private final DoubleSolenoid m_mediumSolenoid =
		new DoubleSolenoid(0, 1);
			// DoubleSolenoid corresponds to a double solenoid.

	private final DoubleSolenoid m_largeSolenoid =
		new DoubleSolenoid(2, 3);

	// Button ID's for each solenoid
	private static final int SMALL_SOLENOID_FORWARD_BUTTON_ID = 5;
	private static final int SMALL_SOLENOID_REVERSE_BUTTON_ID = 6;
	
	private static final int MEDIUM_SOLENOID_BUTTON_ID = 4;
	
	private static final int LARGE_SOLENOID_FOWARD_BUTTON_ID = 1;
	private static final int LARGE_SOLENOID_REVERSE_BUTTON_ID = 2;


	@Override
	public void teleopPeriodic() {
		/*
		 * The output of GetRawButton is true/false depending on whether
		 * the button is pressed; Set takes a boolean for whether
		 * to use the default (false) channel or the other (true).
		 *
		 * In order to set the double solenoid, if just one button
		 * is pressed, set the solenoid to correspond to that button.
		 * If both are pressed, set the solenoid will be set to Forwards.
		 */
		compressor.setClosedLoopControl(true);
		if (m_stick.getRawButton(SMALL_SOLENOID_FORWARD_BUTTON_ID)) {
			m_smallSolenoid.set(DoubleSolenoid.Value.kForward);
		} else if (m_stick.getRawButton(SMALL_SOLENOID_REVERSE_BUTTON_ID)) {
			m_smallSolenoid.set(DoubleSolenoid.Value.kReverse);
		}

		if (m_stick.getRawButtonPressed(MEDIUM_SOLENOID_BUTTON_ID)) {
			m_mediumSolenoid.set(DoubleSolenoid.Value.kForward);
		} else if (m_stick.getRawButtonReleased(MEDIUM_SOLENOID_BUTTON_ID)){
			m_mediumSolenoid.set(DoubleSolenoid.Value.kReverse);
		}

		if (m_stick.getRawButton(LARGE_SOLENOID_FOWARD_BUTTON_ID)) {
			m_largeSolenoid.set(DoubleSolenoid.Value.kForward);
		} else if (m_stick.getRawButton(LARGE_SOLENOID_REVERSE_BUTTON_ID)) {
			m_largeSolenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}
}