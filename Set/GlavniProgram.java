
public class GlavniProgram {

	// Funkcija prejme stavek ter izpise (brez ponavljanja)
	// - crke, ki nastopajo v stavku
	// - crke, ki nastopajo v vsaki besedi stavka
	public static void crke(String stavek)
	{
		// stavek spremenimo v male crke in ga razdelimo na besede
		String[] besede = stavek.toLowerCase().split(" ");
		
		// mnozica za hranjenje vseh crk v stavku 
		Set vse = new Set();
		
		// mnozico za hranjenje crk, ki nastopajo v vsaki besedi stavka
		Set povsod = new Set();
		
		for (int i = 0; i < besede.length; i++)
		{
			// mnozica crk v trenutni besedi
			Set beseda = new Set();
			for (int j = 0; j < besede[i].length(); j++)
			{
				beseda.insert(besede[i].charAt(j));
			}
			
			if (i==0)
				povsod.union(beseda); // zacnemo z vsemi crkami prve besede
			else
				povsod.intersection(beseda); // ohranimo samo skupne crke
			
			vse.union(beseda); // mnozici crk dodamo se crke iz opazovane besede
		}
		
		System.out.println("V stavku se pojavljajo crke: ");
		vse.print();
		System.out.println("V vsaki besedi se pojavljajo crke: ");
		povsod.print();
	}
	
	public static Set createPowerSet(Set s)
	{
		// rezultat je mnozica mnozic
		Set result = new Set();
		
		// prvi element v rezultatu je prazna mnozica
		result.insert(new Set());
		
		// za vsak element iz vhodne mnozice...
		for (SetElement i = s.first(); !s.overEnd(i); i = s.next(i))
		{
			// pripravimo pomozno mnozico podmnozic
			Set update = new Set();
			
			// za vsako mnozico iz rezultata...
			for (SetElement j = result.first(); !result.overEnd(j); j = result.next(j))
			{
				// ustvarimo novo mnozico z enakimi elementi,
				Set cur = new Set();
				cur.union((Set)result.retrieve(j));
				
				// vanjo dodamo trenutni element
				cur.insert(s.retrieve(i));
				
				// in jo dodamo v pomozno mnozico
				update.insert(cur);
			}
			
			// elemente pomozne mnozice dodamo v rezultat
			result.union(update);
		}
		
		// vrnemo rezultat
		return result;
	}
	
	public static void printPowerSet(Set p)
	{
		for (SetElement iter = p.first(); !p.overEnd(iter); iter = p.next(iter))
		{
			Set s = (Set)p.retrieve(iter);
			s.print();
		}
	}
	
	public static void main(String[] args) 
	{
		Set a = new Set();
		a.insert(1);
		a.print();
		
		a.delete(a.locate(1));
		a.print();
		
		a.insert(1);
		a.insert(2);
		a.insert(3);
		a.insert(2);
		a.insert(3);
		a.insert(4);
		a.print();
		
		crke("Abstraktni podatkovni tip");
		
		Set b = new Set();
		
		b.insert(10);
		b.insert(5);
		b.insert(3);
		
		System.out.print("Potencna mnozica mnozice ");
		b.print();
		
		Set p = createPowerSet(b);
		printPowerSet(p);
	}

}
