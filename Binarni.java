
public class Binarni {

	public static void izpisiBinarno(int n)
	{
		int celi = n / 2;
		int ostanek = n % 2;
		
		if (celi > 0)
		{
			izpisiBinarno(celi);
		}
		
		System.out.print(ostanek);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++)
		{
			System.out.print("Zapis stevila " + i + " v binarni obliki je: ");
			izpisiBinarno(i);
			System.out.println();
		}
	}

}
