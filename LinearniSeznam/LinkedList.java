
public class LinkedList 
{
	protected LinkedListElement first;
	protected LinkedListElement last;
	
	LinkedList()
	{
		makenull();
	}
	
	//Funkcija makenull inicializira seznam
	public void makenull()
	{
		//drzimo se implementacije iz ucbenika:
		//po dogovoru je na zacetku glava seznama (header)
		first = new LinkedListElement(null, null);
		last = null;
	}
	
	//Funkcija addLast doda nov element na konec seznama
	public void addLast(Object obj)
	{
		//najprej naredimo nov element
		LinkedListElement newEl = new LinkedListElement(obj, null);
		
		//ali je seznam prazen?
		// po dogovoru velja: ce je seznam prazen, potem kazalec "last" ne kaze nikamor
		if (last == null)
		{
			//ce seznam vsebuje samo en element, kazalca "first" in "last" kazeta na glavo seznama
			first.next = newEl;
			last = first;
		}
		else
		{
			last.next.next = newEl;
			last = last.next;
		}
	}
	
	//Funkcija write izpise elemente seznama
	public void write()
	{
		LinkedListElement el;
		
		//zacnemo pri elementu za glavo seznama
		el = first.next;
		while (el != null)
		{
			System.out.print(el.element + ", ");
			el = el.next;
		}
		
		System.out.println();
		
		/*
		//za kontrolo lahko izpisemo tudi vrednosti prvega in zadnjega elementa
		if (first.next != null)
			System.out.println("Prvi element: " + first.next.element);
		else
			System.out.println("Ni prvega elementa");
		
		if (last != null)
			System.out.println("Zadnji element: " + last.next.element);
		else
			System.out.println("Ni zadnjega elementa");
		*/
	}
	
	//Funkcija addFirst doda nov element na prvo mesto v seznamu (takoj za glavo seznama)
	void addFirst(Object obj)
	{
		//najprej naredimo nov element
		LinkedListElement newEl = new LinkedListElement(obj);
		
		//novi element postavimo za glavo seznama
		newEl.next = first.next;
		first.next = newEl;
		
		if (last == null)//preverimo, ali je to edini element v seznamu
			last = first;
		else if (last == first)//preverimo, ali je seznam vseboval en sam element
			last = newEl;
	}
	
	//Funkcija length() vrne dolzino seznama (pri tem ne uposteva glave seznama)
	int length()
	{
		int counter;
		LinkedListElement el;
		
		counter = 0;
		
		//zacnemo pri elementu za glavo seznama
		el = first.next;
		while (el != null)
		{
			counter++;
			el = el.next;
		}
		
		return counter;
	}
	
	//Rekurzivna funkcija za izracun dolzine seznama
	int lengthRek(LinkedListElement el)
	{
		if (el == null)
			return 0;
		else
			return lengthRek(el.next) + 1;
	}
	
	//Funkcija lengthRek() klice rekurzivno funkcijo za izracun dolzine seznama
	int lengthRek()
	{
		return lengthRek(first.next);
	}
	
	//Funkcija insertNth vstavi element na n-to mesto v seznamu
	//(prvi element seznama, ki se nahaja takoj za glavo seznama, je na indeksu 0)
	boolean insertNth(Object obj, int n)
	{
		LinkedListElement el;
		
		//zacnemo pri glavi seznama
		el = first;
		
		//premaknemo se n-krat
		for (int i = 0; i < n; i++)
		{
			el = el.next;
			if (el == null)
				return false;
		}
		
		LinkedListElement newEl = new LinkedListElement(obj);
		newEl.next = el.next;
		el.next = newEl;
			
		if (last == null) //ce smo dodali edini element
			last = first;
		else if (last == el) //ce smo dodali predzadnji element
			last = last.next;
		else if (last.next == el) //ce smo dodali zadnji element
			last = el;
		//v ostalih primerih se kazalec "last" ne spreminja
			
		return true;
	}
	
	//Funkcija deleteNth izbrise element na n-tem mestu v seznamu
	//(prvi element seznama, ki se nahaja takoj za glavo seznama, je na indeksu 0)
	boolean deleteNth(int n)
	{
		LinkedListElement el, prev_el;
		
		//zacnemo pri glavi seznama
		prev_el = null;
		el = first;
		
		//premaknemo se n-krat
		for (int i = 0; i < n; i++)
		{
			prev_el = el;
			el = el.next;
			if (el == null)
				return false;
		}
		
		if (el.next != null)
		{
			//preden izlocimo element preverimo, ali je potrebno popraviti kazalec "last"
			if (last == el.next) //ce brisemo predzadnji element
				last = el;
			else if (last == el) //ce brišemo zadnji element
				last = prev_el;
				
			el.next = el.next.next;
			
			return true;
		}
		else
			return false;
	}
	
	//Funkcija reverse obrne vrstni red elementov v seznamu (pri tem ignorira glavo seznama)
	void reverse()
	{
		LinkedListElement curEl;
		LinkedListElement tempEl;
		
		//preverimo, ali seznam vsebuje vsaj dva elementa
		if (first.next != null && first.next.next != null)
		{
			//zaceli bomo pri drugem elementu seznama
			curEl = first.next.next;
			
			//takoj lahko oznacimo, da se bo niz zakljuèil z elementom, ki je trenutno prvi
			//vemo tudi, da bo kazalec "last" kazal na element, ki je trenutno na drugem mestu
			first.next.next = null;
			last = curEl;
			
			//premikamo se proti koncu seznama
			while(curEl != null)
			{
				tempEl = curEl.next;
			
				//ustrezno prevezemo elemente
				curEl.next = first.next;
				first.next = curEl;
				
				curEl = tempEl;
			}
		}
	}
	
	//Rekurzivna funkcija, ki obrne vrstni red elementov v seznamu
	void reverseRek(LinkedListElement el)
	{
		if (el == null)
			return;
		
		if (el.next == null)
		{
			first.next = el;
			last = first;
		}
		else
		{
			reverseRek(el.next);
			el.next = null;
			last = last.next;
			last.next = el;
		}
	}
	
	//Funkcija reverseRek klice rekurzivno funkcijo, ki obrne vrstni red elementov v seznamu 
	void reverseRek()
	{
		reverseRek(first.next);
	}
	
	//Funkcija removeDuplicates odstrani ponavljajoce se elemente v seznamu
	void removeDuplicates()
	{
		LinkedListElement curEl;
		
		curEl = first.next;
		while(curEl != null)
		{
			LinkedListElement tmpEl;
			
			//preveri ali se element curEl.next nahaja v seznamu 
			
			tmpEl = curEl;
			while (tmpEl.next != null)
			{
				if (tmpEl.next.element.equals(curEl.element)) 
				{
					//element je ze v seznamu, izlocimo ga
					tmpEl.next = tmpEl.next.next;	
				}
				else 
				{
					//element ni kopija, pustimo ga v seznamu
					tmpEl = tmpEl.next;
				}
			}
			
			curEl = curEl.next;
		}
		
		//ne pozabimo na kazalec "last"
		last = null;
		curEl = first;
		while(curEl.next != null)
		{
			if (curEl.next.next == null)
			{
				last = curEl;
				break;
			}
			else
				curEl = curEl.next;
		}
	}
}
