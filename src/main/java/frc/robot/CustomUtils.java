package frc.robot;

public class CustomUtils {
    public static double applyDeadband(double x, double deadband){
        if(x<=deadband || x>=deadband)
          return x;
        return 0;
      }
}
