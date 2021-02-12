class Konj
{
	int x;
	int y;
	String poteze;
	
	Konj(int x, int y, String poteze)
	{
		this.x = x;
		this.y = y;
		this.poteze = poteze + " -> [" + x + "," + y + "]";
	}
}

	
public class NajkrajsaPot {
	
	public static void main(String[] args) {
		
		char[][] mapa = {
				{'B',' ',' ',' ','B',' ',' ','B'},
				{' ','B',' ',' ',' ','B','B',' '},
				{' ','B','B',' ',' ',' ',' ',' '},
				{' ','B',' ',' ','B','B','B',' '},
				{'B','B','B','B','B',' ','B','B'},
				{'B',' ',' ','B',' ','C',' ',' '},
				{' ','B','B','B',' ','B','B','B'},
				{' ','B',' ',' ','B','B',' ',' '}};
		
		int zacetni_x = 0;
		int zacetni_y = 1;
		Queue queue = new Queue();
		
		System.out.println("Zacetna pozicija konja je [" + zacetni_x + "," + zacetni_y + "]");
		
		Konj k = new Konj(zacetni_x, zacetni_y, "");
		queue.enqueue(k);
		
		boolean obstajaPot = false;
		while (!queue.empty())
		{
			k = (Konj)queue.front();
			queue.dequeue();
			
			if (k.y < 0 || k.y >= mapa.length)
				continue;
			
			if (k.x < 0 || k.x >= mapa[k.y].length)
				continue;
			
			if (mapa[k.y][k.x] == 'B')
				continue;
			
			if (mapa[k.y][k.x] == '.')
				continue;
			
			if (mapa[k.y][k.x] == 'C')
			{
				System.out.println("Najkrajsa varna pot do ciljnega polja je " + k.poteze);
				obstajaPot = true;
				break;
			}
			
			mapa[k.y][k.x] = '.';
			
			queue.enqueue(new Konj(k.x + 1, k.y + 2, k.poteze));
			queue.enqueue(new Konj(k.x - 1, k.y + 2, k.poteze));
			queue.enqueue(new Konj(k.x - 2, k.y + 1, k.poteze));
			queue.enqueue(new Konj(k.x - 2, k.y - 1, k.poteze));
			queue.enqueue(new Konj(k.x - 1, k.y - 2, k.poteze));
			queue.enqueue(new Konj(k.x + 1, k.y - 2, k.poteze));
			queue.enqueue(new Konj(k.x + 2, k.y - 1, k.poteze));
			queue.enqueue(new Konj(k.x + 2, k.y + 1, k.poteze));
		}
		
		if (!obstajaPot)
			System.out.println("Varne poti do ciljnega polja ni!");
	}

}
