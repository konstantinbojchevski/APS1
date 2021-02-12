
public class Sudoku {

	// Rekurzivna funkcija
    static boolean resi(int i, int j, int[][] polje) 
    {
    	// ce smo prisli do konca tabele, vrni pozitiven rezultat
    	if (i >= polje.length)
    		return true;
    	
    	// ce je vsebina celice polje[i][j] ze podana, rekurzivno poisci resitev za naslednjo celico
    	if (j >= polje[i].length)
    		return resi(i+1, 0, polje);
    	
    	if (polje[i][j] != 0)
    		return resi(i, j+1, polje);
    	
    	// za vse mozne vrednosti celice polje[i][j] preveri, ali je situacija dovoljena
    	// ce je, rekurzivno poisci resitev za naslednjo celico
    	for (int v = 1; v <= 9; v++)
    		if (veljavno(i, j, v, polje))
    		{
    			polje[i][j] = v;
    			if (resi(i,j+1,polje))
    				return true;
    		}
    	
    	// ce nobena vrednost ni dovoljena, razveljavi vsebino celice polje[i][j] in vrni negativen rezultat
    	polje[i][j] = 0;
        return false;
    }

    // Funkcija preveri, ali je dovoljeno postaviti vrednost "val" v polje[i][j]
    static boolean veljavno(int i, int j, int val, int[][] polje) 
    {
    	// preveri j-ti stolpec
        //   ce se vrednost "val" ze nahaja v j-tem stolpcu, potem je situacija neveljavna
       for (int k=0; k < polje.length; k++)
    	   if (polje[k][j] == val)
    		   return false;
       
    	// preveri i-to vrstico
        //   ce se vrednost "val" ze nahaja v i-ti vrstici, potem je situacija neveljavna
       for (int k=0; k < polje[i].length; k++)
    	   if (polje[i][k] == val)
    		   return false;
       
    	// preveri ustrezni kvadrat
        //   ce se vrednost "val" ze nahaja v kvadratu, potem je situacija neveljavna
        int vrstOd = (i/3)*3;
        int stolpOd = (j/3)*3;
        
        for (int m = 0; m < 3; m++)
        	for (int n = 0; n < 3; n++)
        		if (polje[vrstOd + m][stolpOd + n] == val)
         		   return false;
        
        // vse pogoje smo preverili, je situacija veljavna
        return true;
    }

    static void izpisiPolje(int[][] polje) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0)
                System.out.println(" -----------------------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                if (polje[i][j] == 0)
                	System.out.print(" ");
                else
                    System.out.print(polje[i][j]);

                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }

    public static void main(String[] args) {
        int[][] polje = {
        		{0,8,0,4,0,2,0,6,0},
        		{0,3,4,0,0,0,9,1,0},
        		{9,6,0,0,0,0,0,8,4},
        		{0,0,0,2,1,6,0,0,0},
        		{0,0,0,0,0,0,0,0,0},
        		{0,0,0,3,5,7,0,0,0},
        		{8,4,0,0,0,0,0,7,5},
        		{0,2,6,0,0,0,1,3,0},
        		{0,9,0,7,0,1,0,4,0}
        };
        
        izpisiPolje(polje);
        if (resi(0,0,polje))
            izpisiPolje(polje);
        else
            System.out.println("Ni resitve");
    }
}
