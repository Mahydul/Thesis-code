
package aprioriz;

/**
 *
 * @author Mahydul Islam
 */


import java.util.*;
import java.sql.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
 
class Tuple {
	Set<Integer> itemset;
	int support;
	
	Tuple() {
		itemset = new HashSet<>();
		support = -1;
	}
	
	Tuple(Set<Integer> s) {
		itemset = s;
		support = -1;
	}
	
	Tuple(Set<Integer> s, int i) {
		itemset = s;
		support = i;
	}
}

class Apriori {
	static Set<Tuple> c;
	static Set<Tuple> l;
         static int d[][]=new int[200][200];
           // static int d[][];
	static int min_support;
	
	public static void main(String args[]) throws Exception {
            
             long lStartTime = System.nanoTime();
             
		getatabase();
		c = new HashSet<>();
		l = new HashSet<>();
		Scanner scan = new Scanner(System.in);
		int i, j, m, n;
		//System.out.println("Enter the minimum support (as an integer value):");
		//min_support = scan.nextInt();
                
        min_support = 20;
		Set<Integer> candidate_set = new HashSet<>();
		for(i=0 ; i < d.length ; i++) {
			System.out.println("Transaction Number: " + (i+1) + ":");
			for(j=0 ; j < d[i].length ; j++) {
                            
               
			//voltage apply
                                if(d[i][j]!=0){
                                    	System.out.print("Item number " + (j+1) + " = ");
				System.out.println(d[i][j]);
				candidate_set.add(d[i][j]);} 
//finish voltage apply
			}
		}
		
		Iterator<Integer> iterator = candidate_set.iterator();
		while(iterator.hasNext()) {
			Set<Integer> s = new HashSet<>();
			s.add(iterator.next());
			Tuple t = new Tuple(s, count(s));
			c.add(t);
		}
		
		prune();
		generateFrequentItemsets();
                
                  long lEndTime = System.nanoTime();
                  
                    long output = lEndTime - lStartTime;
                    
                      System.out.println("Elapsed time in milliseconds: " + output / 1000000);
	}
	
	static int count(Set<Integer> s) {
		int i, j, k;
		int support = 0;
		int count;
		boolean containsElement;
		for(i=0 ; i < d.length ; i++) {
			count = 0;
			Iterator<Integer> iterator = s.iterator();
			while(iterator.hasNext()) {
				int element = iterator.next();
				containsElement = false;
				for(k=0 ; k < d[i].length ; k++) {
					if(element == d[i][k]) {
						containsElement = true;
						count++;
						break;
					}
				}
				if(!containsElement) {
					break;
				}
			}
			if(count == s.size()) {
				support++;
			}
		}
		return support;
	}
	
	static void prune() {
		l.clear();
		Iterator<Tuple> iterator = c.iterator();
		while(iterator.hasNext()) {
			Tuple t = iterator.next();
			if(t.support >= min_support) {
				l.add(t);
			}
		}
		System.out.println("-+- L -+-");
		for(Tuple t : l) {
			System.out.println(t.itemset + " : " + t.support);
		}
	}
	
	static void generateFrequentItemsets() {
		boolean toBeContinued = true;
		int element = 0;
		int size = 1;
		Set<Set> candidate_set = new HashSet<>();
		while(toBeContinued) {
			candidate_set.clear();
			c.clear();
			Iterator<Tuple> iterator = l.iterator();
			while(iterator.hasNext()) {
				Tuple t1 = iterator.next();
				Set<Integer> temp = t1.itemset;
				Iterator<Tuple> it2 = l.iterator();
				while(it2.hasNext()) {
					Tuple t2 = it2.next();
					Iterator<Integer> it3 = t2.itemset.iterator();
					while(it3.hasNext()) {
						try {
							element = it3.next();
						} catch(ConcurrentModificationException e) {
							// Sometimes this Exception gets thrown, so simply break in that case.
							break;
						}
						temp.add(element);
						if(temp.size() != size) {
							Integer[] int_arr = temp.toArray(new Integer[0]);
							Set<Integer> temp2 = new HashSet<>();
							for(Integer x : int_arr) {
								temp2.add(x);
							}
							candidate_set.add(temp2);
							temp.remove(element);
						}
					}
				}
			}
			Iterator<Set> candidate_set_iterator = candidate_set.iterator();
			while(candidate_set_iterator.hasNext()) {
				Set s = candidate_set_iterator.next();
				// These lines cause warnings, as the candidate_set Set stores a raw set.
				c.add(new Tuple(s, count(s)));
			}
			prune();
			if(l.size() <= 1) {
				toBeContinued = false;
			}
			size++;
		}
		System.out.println("\n=+= FINAL LIST =+=");
		for(Tuple t : l) {
			System.out.println(t.itemset + " : " + t.support);
		}
	}
	
	static void getatabase() throws IOException {
                          
            
             int[][] matrix = new int[1000][1000];
    		int x, y;
      try
            {
    		BufferedReader in = new BufferedReader(new FileReader("out_new1.txt"));	//reading files in specified directory
    			String line;
                        x=0;
    			while ((line = in.readLine()) != null)	//file reading
    			{
    				String[] values = line.split(" ");
                                y=0;
    	        	for (String str : values)
    	        	{
    	        		int matrix_value = Integer.parseInt(str);
                              //  System.out.print(matrix_value+" ");
    	        		matrix[x][y]=matrix_value;
                              //  d[x][y]=matrix_value;
    	        		y=y+1;
    	        	}
    	        	//System.out.println();
                        x=x+1;
    			}   
            	in.close();
            }catch( IOException ioException ) {}
            
            for(int i=0;i<30;i++){
              for(int j=0;j<25;j++){
               d[i][j] = matrix[i][j];
              }
            }
            
	}//loop break
}