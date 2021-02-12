import java.io.*;

public class Naloga2 {
	
	static int pocetno_i;
	static int pocetno_j;
	static int najvecje = 0;
	
	public static void najdolgoOdKelija(String mat[][], int visited[][], int n, int m, int i, int j, String[] rez, int counter, String[] rezultat) {

			   
        if (j < m - 1 && (mat[i][j].equals(mat[i][j + 1])) && (visited[i][j + 1] == 0)){
			visited[i][j+1] = 1;
			rez[counter] = "DESNO";			
			najdolgoOdKelija(mat, visited, n, m, i, j+1, rez, counter+1, rezultat);
		}		

        if (j > 0 && (mat[i][j].equals(mat[i][j - 1])) && (visited[i][j - 1] == 0)){
        	visited[i][j-1] = 1;
        	rez[counter] = "LEVO";
			najdolgoOdKelija(mat, visited, n, m, i, j-1, rez, counter+1, rezultat);
		}	
  
        if (i > 0 && (mat[i][j].equals(mat[i - 1][j])) && (visited[i - 1][j] == 0)){
        	visited[i-1][j] = 1;
        	rez[counter] = "GOR";
			najdolgoOdKelija(mat, visited, n, m, i-1, j, rez, counter+1, rezultat);
		}

        if (i < n - 1 && (mat[i][j].equals(mat[i + 1][j])) && (visited[i + 1][j] == 0)){
        	visited[i+1][j] = 1;
        	rez[counter] = "DOL";			
			najdolgoOdKelija(mat, visited, n, m, i+1, j, rez, counter+1, rezultat);
		}
     	
  
        if(counter > najvecje) {
        	najvecje = counter;
        	for(int k=0; k<(n*m); k++) {
        		if(rez[k] == null) break;
        		rezultat[k] = rez[k];
        	}
        }
        
        if(counter > 0)
        for(int k=counter-1; k<(n*m); k++) {
    		if(rez[k] == null) break;
        	rez[k] = null;
        }
        
        visited[i][j] = 0;
		return;
    }
	
    public static String[] najdolgoOdSite(String mat[][], int n, int m) {
    	
		int counter = 0;
		String[] rez = new String [n*m];
		String[] rezultat = new String[n*m];
		
		int[][] visited = new int[n][m];
		for (int i = 0; i < n; i++) 
            for (int j = 0; j < m; j++)
                visited[i][j] = 0;

        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < m; j++) {
        		visited[i][j] = 1;
            	najdolgoOdKelija(mat, visited, n, m, i, j, rez, 0, rezultat);
            	
            	if(counter < najvecje) {
            		pocetno_i = i;
            		pocetno_j = j;
            		counter = najvecje;
            	}
        	}
        }
        
        return rezultat;
    }
 
    public static void main(String[] args) throws IOException {
        
    	int n, m;
    	String[] argumenti = new String[2];
    	
        FileInputStream inputStream = new FileInputStream(args[0]);
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
		
        String line = bufferedReader.readLine();
        argumenti = line.split(",");
        n = Integer.parseInt(argumenti[0]);
        m = Integer.parseInt(argumenti[1]);
        String[][] table = new String[n][m];
        int row = 0;
        while ((line = bufferedReader.readLine()) != null){
        	if(!(line.equals(""))) {
        		String[] str = line.split(",");
        		int column = 0;
        		for(String a: str) {
        			table[row][column] = a;
        			column++;
        		}
        		row++;
        	}
		}
        bufferedReader.close();
        
        String[] vrni = new String [n*m];
		vrni = najdolgoOdSite(table, n, m);
		
		PrintWriter printWriter = new PrintWriter(new File(args[1]), "UTF-8");
		printWriter.print(pocetno_i + "," + pocetno_j + "\n");
		for(int k=0; k<vrni.length; k++) {
			if(vrni[k] != null) {
				if(najvecje-1 == k) {
					printWriter.print(vrni[k]);
				}
				else{
					printWriter.print(vrni[k] + ",");
				}
			}
		}
		printWriter.print("\n");
		printWriter.close();
    }
}