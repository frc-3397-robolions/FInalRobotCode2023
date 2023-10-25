package frc.robot;

public class CustomUtils {
<<<<<<< HEAD
    public static double applyDeadband(double x){
        if(x<=-Constants.controllerDeadband || x>=Constants.controllerDeadband)
=======
    public static double applyDeadband(double x, double deadband){
        if(x<=deadband || x>=deadband)
>>>>>>> parent of 8beb984 (Making it work)
          return x;
        return 0;
      }
}
