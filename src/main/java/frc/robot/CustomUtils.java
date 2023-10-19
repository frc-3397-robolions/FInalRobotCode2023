package frc.robot;

public class CustomUtils {
    public static double applyDeadband(double x){
        if(x<=-Constants.controllerDeadband || x>=Constants.controllerDeadband)
          return x;
        return 0;
      }
}
