// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Add your docs here. */
public class OI {
    XboxController driver = new XboxController (Constants.OI.controllerDriver);


/** 
@param deadband
@param input
@return
*/

private double deadbandCompensation(double input, double deadband) {

    double slope = 1 / (1-deadband); // m = rise/run
    double offset = 1 - slope; // b = y - mx
    if (input < 0.0) {
        return Math.abs(input) > deadband? (-1 * (slope * Math.abs(input) + offset)) : 0.0;
    } else if (input > 0.0) {
        return Math.abs(input) > deadband? (slope * Math.abs(input) + offset): 0.0;
    } else {
        return 0.0;
    }
}

public double getDriveTurn() {
    double d = getLeftJoyStickX(driver);
    SmartDashboard.putNumber("Turn", d);
    return d;
}

public double getDriveThrottle() {
    double d = getRightTrigger(driver) - getLeftTrigger(driver);
    SmartDashboard.putNumber("throttle", d);
    return d;

}

public double getLeftJoyStickX(XboxController cont) {
    return deadbandCompensation(cont.getLeftX(), Constants.OI.controllerDeadbandJoystick);
    
}

public double getRightTrigger(XboxController cont) {
    return deadbandCompensation(cont.getRightTriggerAxis(), Constants.OI.controllerDeadbandTrigger);
}

public double getLeftTrigger(XboxController cont) {
    return deadbandCompensation(cont.getLeftTriggerAxis(), Constants.OI.controllerDeadbandTrigger);
}
}



