
class Oseba implements Comparable<Oseba>
{
	String ime;
	String priimek;
	
	Oseba(String ime, String priimek)
	{
		this.ime = ime;
		this.priimek = priimek;
	}
	
	public int compareTo(Oseba o)
	{
		int r = priimek.compareTo(o.priimek);
		
		if (r == 0)
			return ime.compareTo(o.ime);
		else
			return r;
	}
	
	public boolean equals(Oseba o)
	{
		return compareTo(o) == 0;
	}
	
	public String toString()
	{
		return priimek + ", " + ime;
	}
	
	public int hashCode()
	{
		//
		// najpreprostejsa implementacija
		//
		/*
		int code = 0;
		if (priimek.length() > 0)
			code = priimek.charAt(0);
		return code;
		*/
		
		//
		// Funkcija hashCode() razreda String vrne s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1], 
		// pri cemer s[i] predstavlja i-ti znak v stringu, n je dolzina stringa
		//
		/*
		int code = 0;
		int h = 1;
		for (int i = priimek.length() - 1; i >= 0 ; i--)
		{
			code += priimek.charAt(i)*h;
			h *= 31;
		}
		return code;
		*/
			
		return (ime + priimek).hashCode();
	}
}