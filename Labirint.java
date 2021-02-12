
public class Labirint {

	//
	// Oznake:
	//
	// '#' zid
	// ' ' hodnik
	// 'C' cilj
	// '.' oznaka, da smo trenutno lokacijo vkljucili v pot
	//
	
	public static boolean najdiPot(char[][] labirint, int x, int y)
	{
		//preveri ali je y-koordinata veljavna
		if (y < 0 || y >= labirint.length)
			return false;
		
		//preveri ali je x-koordinata veljavna
		if (x < 0 || x >= labirint[y].length)
			return false;
		
		//ali smo prispeli do cilja?
		if (labirint[y][x] == 'C')
			return true;
		
		//ali je na trenutni lokaciji zid?
		if (labirint[y][x] == '#')
			return false;
		
		//ali smo v tej tocki ze bili?
		if (labirint[y][x] == '.')
			return false;
		
		//oznaci trenutni polozaj
		labirint[y][x] = '.';
		
		//odkomentirajte spodnjo vrstico za izpis trenutnega položaja
		//izpis(labirint);
		
		//preveri, ali pridemo do cilja, ce se premaknemo proti vzhodu
		if (najdiPot(labirint, x+1, y))
			return true;
		
		//preveri, ali pridemo do cilja, ce se premaknemo proti severu
		if (najdiPot(labirint, x, y-1))
			return true;
		
		//preveri, ali pridemo do cilja, ce se premaknemo proti zahodu
		if (najdiPot(labirint, x-1, y))
			return true;
		
		//preveri, ali pridemo do cilja, ce se premaknemo proti jugu
		if (najdiPot(labirint, x, y+1))
			return true;
		
		//ta polozaj ocitno ni pripeljal do cilja, odznacimo ga!
		labirint[y][x] = ' ';
		
		return false;
	}
	
	public static void izpis(char[][] labirint)
	{
		for (int i = 0; i < labirint.length; i++)
		{
			for (int j = 0;  j < labirint[i].length; j++)
				System.out.print(labirint[i][j]);
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		char[][] labirint = {
				{'#','#','#','#','#','#','#','#','#'},
				{'#',' ',' ',' ',' ',' ','#',' ','#'},
				{'#',' ','#','#','#',' ','#',' ','#'},
				{'#',' ','#','#','#',' ','#',' ','#'},
				{'#',' ',' ',' ','#','#','#',' ','#'},
				{'#',' ','#',' ','#',' ',' ',' ','#'},
				{'#',' ','#',' ',' ',' ','#',' ','#'},
				{'#',' ','#','#','#','#','#',' ','#'},
				{'#',' ',' ',' ','#',' ',' ','C','#'},
				{'#','#','#','#','#','#','#','#','#'}};

		System.out.println("Izgled labirinta:");
		izpis(labirint);

		System.out.println("\nNajdena pot skozi labirint:");
		if (najdiPot(labirint, 5, 3))
			izpis(labirint);
		else
			System.out.println("Ne najdem poti skozi labirint!");
	}
}
