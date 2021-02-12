
public class Znesek {

	public static boolean sestavi(int[] vrednosti, int index, int znesek)
	{
		// ali imamo zahtevani znesek?
		if (znesek == 0)
			return true;
		
		// ce je znesek negativen, ga ne more sestaviti
		if (znesek < 0)
			return false;
		
		// ali smo pregledali vse vrednosti?
		if (index >= vrednosti.length)
			return false;
		
		// problem poskusimo resiti tako, da uporabimo trenutni element. 
		// ce nam ne uspe, preskocimo trenutni element in nadaljujemo iskanje
		
		if (sestavi(vrednosti, index+1, znesek - vrednosti[index]))
		{
			System.out.print(vrednosti[index] + ", ");
			return true;
		}
		else
			return sestavi(vrednosti, index+1, znesek);
	}
	
	public static void main(String[] args) {
		int[] vrednosti = {7,8,5,1,3,9,2,5,2,3,5};
		int znesek = 10;
		
		System.out.print("Znesek " + znesek + " dobimo tako, da sestejemo elemente: ");
		
		if (!sestavi(vrednosti, 0, znesek))
			System.out.println("Zneska ni mogoce sestaviti s podanimi elementi");
	}

}
