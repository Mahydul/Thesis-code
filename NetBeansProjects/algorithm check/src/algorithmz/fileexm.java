/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmz;

/**
 *
 * @author Mahydul Islam
 */
import java.io.*;
import java.io.*;
import java.util.*;

public class fileexm {
  public static void main(String[] args) throws IOException {

      int[][] matrix = new int[1000][1000];
    		int x, y;
      try
            {
    		BufferedReader in = new BufferedReader(new FileReader("data.txt"));	//reading files in specified directory
    			String line;
                        x=1;
    			while ((line = in.readLine()) != null)	//file reading
    			{
    				String[] values = line.split(" ");
                                y=1;
    	        	for (String str : values)
    	        	{
    	        		int matrix_value = Integer.parseInt(str);
                              //  System.out.print(matrix_value+" ");
    	        		matrix[x][y]=matrix_value;
    	        		y=y+1;
    	        	}
    	        	//System.out.println();
                        x=x+1;
    			}   
            	in.close();
            }catch( IOException ioException ) {}
         File outFile = new File ("out_new1.txt");
                 FileWriter fWriter = new FileWriter (outFile);
                   PrintWriter pWriter = new PrintWriter (fWriter);
                  for(int i=1;i<=30;i++){
                     // System.out.print(i+" ");
                        // pWriter.print(i+" ");
                         for(int j=1;j<=25;j++){
                          //System.out.print(matrix[i][j]+" ");
                            if(matrix[i][j]==1){
                              System.out.print(j+" ");  
    pWriter.print(j+" ");
   // pWriter.println ("This is another line.");     
           }
        }
     System.out.println();
     pWriter.println();
       }       
      pWriter.close();
      
  }
}
    
