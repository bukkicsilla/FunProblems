//Write a method that computes the cubic root of a float number.

public class CubicRootStatic{
    
    static double eps = 0.0000000001;

    private static boolean isNegative(double cube){
	return ( cube < 0)? true:false;
   
    }

    public static double cubicroot(double cube){
	double result = 0.0;
	double c = cube;


	if(isNegative(cube))//given number is negative 
	    {
		if (cube > -1.0) c = -1.0;
		result = cubicroot(cube, c, 0.0);
		
	    }
	else{ //given number is positive
	    if (cube < 1.0) c = 1.0;
	    result = cubicroot(cube, 0.0, c);
	}
	return result;
    }

    private static double cubicroot(double cube, double low, double high){
	

	double m =  low + (high - low)/2;
	
	if (Math.abs(m*m*m - cube) < eps)  return m;
	
	else{
	    if (m*m*m > cube){
		return cubicroot(cube, low, m);
	    }
	    else{
		return cubicroot(cube, m, high);
	    } 
	}
	
	
    }
 
    public static void main(String [] arg){
     
	int a = 20;
	double [] posbig = new double[a];
	for (int i = 0; i < a; i++){
	    posbig[i] = i*i*i;
	} 
	for (double d : posbig){
	    System.out.println(" number " + d + " cubicroot " + CubicRootStatic.cubicroot(d));
	    }

	double [] negbig = new double[a];
	for (int i = 0; i < a; i++){
	    negbig[i] = -i*i*i;
	} 
	for (double d : negbig){
	    System.out.println(" number " + d + " cubicroot " + CubicRootStatic.cubicroot(d));
	    }
	double [] possmall = new double[a];
	for (int i = 0; i < a; i++){
	    double j = i + 1.0;
	    possmall[i] = 1/(j*j*j);
	} 
	for (double d : possmall){
	    System.out.println(" number " + d + "\n cubicroot " + CubicRootStatic.cubicroot(d));
	    }

	double [] negsmall = new double[a];
	for (int i = 0; i < a; i++){
	    double j = i + 1.0;
	    negsmall[i] = -1/(j*j*j);
	} 
	for (double d : negsmall){
	    System.out.println(" number " + d + "\n cubicroot " + CubicRootStatic.cubicroot(d));
	    }

    } 

}
