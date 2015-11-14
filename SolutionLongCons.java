import java.util.List;
import java.util.ArrayList;

public class SolutionLongCons{
    int [][] matrix;
    int [][] best;
    int n = 4;

    public SolutionLongCons(){
	matrix =  new int [n][n];
	//5,6,7,8,9,10
	/*matrix[0][0] =  16;
         matrix[0][1] = 15;
         matrix[0][2] = 2;
         matrix[0][3] = 1;
         
         matrix[1][0] =  3;
         matrix[1][1] = 14;
         matrix[1][2] = 13;
         matrix[1][3] = 4;
         
         matrix[2][0] =  12;
         matrix[2][1] = 1;
         matrix[2][2] = 5;
         matrix[2][3] = 6;

         matrix[3][0] =  10;
         matrix[3][1] = 9;
         matrix[3][2] = 8;
         matrix[3][3] = 7;*/

	 //1 2 3 4 5 6	
	/*matrix[0][0] =  1;
	matrix[0][1] = 6;
	matrix[0][2] = 7;
	matrix[0][3] = 2;
        
	matrix[1][0] =  2;
	matrix[1][1] = 3;
	matrix[1][2] = 3;
	matrix[1][3] = 7;
        
	matrix[2][0] =  6;
	matrix[2][1] = 4;
	matrix[2][2] = 2;
	matrix[2][3] = 1;
	
	matrix[3][0] =  5;
	matrix[3][1] = 4;
	matrix[3][2] = 3;
	matrix[3][3] = 1;*/

	//3 4 5 6 7 8 9 10 11 12
	matrix[0][0] = 1;
	matrix[0][1] = 10;
	matrix[0][2] = 11;
	matrix[0][3] = 12;
        
	matrix[1][0] =  8;
	matrix[1][1] = 9;
	matrix[1][2] = 13;
	matrix[1][3] = 14;
        
	matrix[2][0] =  7;
	matrix[2][1] = 6;
	matrix[2][2] = 3;
	matrix[2][3] = 16;
	
	matrix[3][0] =  2;
	matrix[3][1] = 5;
	matrix[3][2] = 4;
	matrix[3][3] = 15;
	
	//3 4 5 6 7 8
	/*matrix[0][0] = 1;
	matrix[0][1] = 2;
	matrix[0][2] = 5;
	matrix[0][3] = 8;
        
	matrix[1][0] =  8;
	matrix[1][1] = 3;
	matrix[1][2] = 4;
	matrix[1][3] = 9;
        
	matrix[2][0] =  7;
	matrix[2][1] = 6;
	matrix[2][2] = 3;
	matrix[2][3] = 10;
	
	matrix[3][0] =  1;
	matrix[3][1] = 5;
	matrix[3][2] = 4;
	matrix[3][3] = 11;*/

	 best = new int[n][n];
	 for (int i = 0; i < n; i++){
	     for (int j = 0; j < n; j++){
		 best[i][j] = 1;
	     }
	 }
    }

    public int  compute_best(int i, int j){

	if ((i > 0) && ( matrix[i-1][j] + 1 == matrix[i][j]))  best[i][j] = Math.max(best[i][j], 1 + compute_best(i-1, j));
	if ((j > 0) && (matrix[i][j-1] + 1 == matrix[i][j]))  best[i][j] = Math.max(best[i][j], 1 + compute_best(i, j-1));
	if ((i + 1 < n) && ( matrix[i+1][j] + 1 == matrix[i][j])) best[i][j] = Math.max(best[i][j], 1 + compute_best(i+1, j));
	if ((j + 1 < n) && ( matrix[i][j+1] + 1 == matrix[i][j])) best[i][j] = Math.max(best[i][j], 1 + compute_best(i, j+1));
	return best[i][j];
    }
    
 public static void main(String [] args){
    
			     

	 SolutionLongCons sol = new SolutionLongCons();
	 int longest = 1;
	 for (int i = 0; i < sol.n; i++){
	     for (int j = 0; j < sol.n; j++){
		 int best = sol.compute_best(i,j);
		 if (best > longest) longest = best;  
	     }
	 }
	 System.out.println(longest);
  }
} 
