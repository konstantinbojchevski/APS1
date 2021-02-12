class SetElement
{
	Object element;
	SetElement next;

	SetElement()
	{
		element = null;
		next = null;
	}
}

public class Set 
{
	private SetElement first;
	
	public Set() 
	{
		makenull();
	}
	
	public void makenull()
	{
		first = new SetElement();
	}
	
	public SetElement first()
	{
		return first;
	}
	
	public SetElement next(SetElement pos)
	{
		return pos.next;
	}
	
	public void insert(Object obj) 
	{
		// nov element vstavimo samo, ce ga ni med obstojecimi elementi mnozice
		if (locate(obj) == null)
		{
			SetElement nov = new SetElement();
			nov.element = obj;
			nov.next = first.next;
			first.next = nov;
		}
	}
	
	public void delete(SetElement pos)
	{
		pos.next = pos.next.next;
	}
	
	public boolean overEnd(SetElement pos)
	{
		if (pos.next == null)
			return true;
		else
			return false;
	}
	
	public boolean empty()
	{
		return first.next == null;
	}
	
	public Object retrieve(SetElement pos)
	{
		return pos.next.element;
	}
	
	public SetElement locate(Object obj)
	{
		// sprehodimo se cez seznam elementov in preverimo enakost (uporabimo metodo equals)
		for (SetElement iter = first(); !overEnd(iter); iter = next(iter))
			if (obj.equals(retrieve(iter)))
				return iter;
		
		return null;
	}
	
	public void union(Set a)
	{
		// dodaj vse elemente iz mnozice a (metoda insert poskrbi, da ni podvajanja elementov)
		for (SetElement iter = a.first(); !a.overEnd(iter); iter = a.next(iter))
			insert(a.retrieve(iter));
	}
	
	public void intersection(Set a)
	{
		// odstranimo vse elemente, ki se ne nahajajo tudi v mnozici a
		Object el;
		SetElement iter = first();
		while (!overEnd(iter))
		{
			el = retrieve(iter);
			if (a.locate(el) == null)
				delete(iter);
			else
				iter = next(iter);
		}
	}
	
	public void print()
	{
		System.out.print("{");
		for (SetElement iter = first(); !overEnd(iter); iter = next(iter))
		{
			System.out.print(retrieve(iter));
			if (!overEnd(next(iter)))
				System.out.print(", ");
		}
		System.out.println("}");
	}
}
