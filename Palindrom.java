
public class Palindrom {

	public static boolean jePalindrom(String word) {
		if (word.length() <= 1)
			return true;
		else if (word.charAt(0) != word.charAt(word.length()-1))
			return false;
		else 
			return jePalindrom(word.substring(1, word.length()-1));
	}
	
	public static boolean jePalindrom_DrugaResitev(String word, int index) {
		if (index >= word.length()/2)
			return true;
		else if (word.charAt(index) != word.charAt(word.length()-index-1))
			return false;
		else 
			return jePalindrom_DrugaResitev(word, index+1);
	}
	
	public static void main(String[] args) {
		String[] words = {"rotator", "radar", "r", "ab", "pericarezeracirep", "rotator1", "rotato", "abccba"};

		for (int i = 0; i < words.length; i++)
		{
			System.out.print("Beseda " + words[i]);
				
			if (jePalindrom(words[i]))
				System.out.println(" je palindrom");
			else
				System.out.println(" ni palindrom");
			
			//if (jePalindrom_DrugaResitev(words[i], 0))
			//	System.out.println(" je palindrom");
			//else
			//	System.out.println(" ni palindrom");
		}
	}

}
