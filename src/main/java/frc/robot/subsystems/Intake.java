package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Intake {
    private CANSparkMax neo550ShooterFrontIntake;
    private CANSparkMax neo550ShooterRearIntake;
    private RelativeEncoder neo550ShooterFrontIntakeEncoder;   
    private SlewRateLimiter intakeFilter = new SlewRateLimiter(Constants.INTAKE_RAMP_UP_POWER);

    public Intake(CANSparkMax inNeo550ShooterFrontIntake,
                  CANSparkMax inNeo550ShooterRearIntake)
    {
        neo550ShooterFrontIntake = inNeo550ShooterFrontIntake;
        neo550ShooterRearIntake  = inNeo550ShooterRearIntake;
        neo550ShooterFrontIntakeEncoder = neo550ShooterFrontIntake.getEncoder();   

        neo550ShooterFrontIntake.restoreFactoryDefaults();
        neo550ShooterRearIntake.restoreFactoryDefaults();
    }

    /*
     * toggle the intakes on or off
     * 
     * objects used: neo550ShooterFrontIntake, 
     *               neo550ShooterRearIntake, 
     *               neo550ShooterFrontIntakeEncoder
     */
    public void toggle(boolean toggleOn)
    {
        System.out.println("intakeToggle");
        double speed = Constants.INTAKE_ON;

        if (toggleOn == false)
        {
            speed = Constants.INTAKE_OFF;
        }

        double fltSpeed = intakeFilter.calculate(speed);

        neo550ShooterFrontIntake.set(fltSpeed);
        neo550ShooterRearIntake.set(fltSpeed);

        SmartDashboard.putNumber("FrontIntake speed", 
                                 speed);
        SmartDashboard.putNumber("FrontIntake Velocity", 
                                 neo550ShooterFrontIntakeEncoder.getVelocity());
    }   

}
