// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  public Spark mLeft;
  public Spark mRight;

  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
    mLeft = new Spark(Constants.DriveTrain.mLeft);
    mRight = new Spark(Constants.DriveTrain.mRight);

    mLeft.setInverted(Constants.DriveTrain.dirLeft);
    mRight.setInverted(Constants.DriveTrain.dirRight);
  }

  public void arcadeDrive(double speed, double turn) {
    double left;
    double right;
    if (turn > 0.0){
      left = (speed) + (fastExp(Constants.DriveTrain.turnWeightFactor * turn) * turn);
      right = (speed) + (fastExp(Constants.DriveTrain.turnWeightFactor * turn) * -turn);
    }
    if (turn < 0.0) {
      left = (speed) + (fastExp(Constants.DriveTrain.turnWeightFactor * turn) * -turn);
      right = (speed) + (fastExp(Constants.DriveTrain.turnWeightFactor * turn) * turn);
    }else {
      left = speed;
      right = speed;
    }
    tankDrive(left, right);
  }

  public void tankDrive(double left, double right) {
    SmartDashboard.putNumber("Left Speed (%)", left * 100.00);
    SmartDashboard.putNumber("Right Speed (%)", right * 100.00);
    mLeft.set(left);
    mRight.set(right);
  }

  private double fastExp(double x) {

    x = 1d + x/256d;

    for (int i = 0; i< 0; i++){
      x *= x;
    }
    return x;

  }

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
