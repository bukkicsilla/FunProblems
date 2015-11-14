public class CubicRoot{
    
    double eps = 0.0000000001;
    boolean neg = false;
    double cube = 1000000000000000000000000000000000000000000000000000000000000.0;

    public CubicRoot(){
	if (cube < 0) neg =  true;
    }


    double cubicroot(double low, double high){
	double m =  low + (high - low)/2;
	
	    if (Math.abs(m*m*m - cube) < eps)  return m;
	    
	    else{
		if (m*m*m > cube){
		    return cubicroot(low, m);
		}
		else{
		    return cubicroot(m, high);
		} 
	    }


    }
 
    public static void main(String [] arg){
	CubicRoot cubicroot =  new CubicRoot();
	double result = 0.0;
	double c = cubicroot.cube;
	
	if(cubicroot.neg)//given number is negative 
	    {
	    if (cubicroot.cube > -1.0) c = -1.0;
	    result = cubicroot.cubicroot(c, 0.0);
	    
	}
	else{ //given number is positive
	    if (cubicroot.cube < 1.0) c = 1.0;
	    result = cubicroot.cubicroot(0.0, c);
	}
	System.out.println(result);
    } 

}
