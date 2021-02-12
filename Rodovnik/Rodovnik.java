import javax.management.OperationsException;

class Oseba
{
	String ime;
	
	Oseba oce;
	Oseba brat;
	Oseba sin;
	
	public Oseba(String ime, Oseba oce) 
	{
		this.ime = ime;
		this.oce = oce;
	}
}

public class Rodovnik 
{
	Oseba koren;
	
	public Rodovnik(String ime) 
	{
		koren = new Oseba(ime, null);
	}

	//Pomozna metoda: vrne kazalec na vozlisce, ki vsebuje osebo s podanim imenom in
	//se nahaja v poddrevesu s korenom 'v'
	private Oseba poisci(String ime, Oseba v)
	{
		if (v.ime.equals(ime))
			return v;
		else
		{
			Oseba sin = v.sin;
			Oseba r;

			while (sin != null)
			{
				r = poisci(ime, sin);

				if (r != null)
					return r;
				else
					sin = sin.brat;
			}

			return null;
		}
	}

	//Metoda doda sina podanemu ocetu
	public boolean dodajSina(String oce, String sin) 
	{
		Oseba v = poisci(oce, koren);
		if(v != null){
			Oseba s = new Oseba(sin, v);
			s.brat = v.sin;
			v.sin = s;
			return true;
		}
		return false;
	}

	private void izpis(int zamik, Oseba v)
	{
		for(int i = 0; i < zamik; i++)
			System.out.print(" ");

		System.out.println(v.ime);
		Oseba sin = v.sin;

		while (sin != null)
		{
			izpis(zamik+1, sin);
			sin = sin.brat;
		}
	}

	//Metoda za izpis druzinskega drevesa
	public void izpis()
	{
		izpis(0, koren);
	}

	//Pomozna metoda: presteje vozlisca v poddrevesu s korenom v vozliscu 'v'
	private int prestejVozlisca(Oseba v)
	{
		if (v == null)
			return 0;

		//takoj pristejemo trenutno vozlisce
		int stevilo = 1;

		Oseba sin = v.sin;
		while (sin != null)
		{
			stevilo += prestejVozlisca(sin);
			sin = sin.brat;
		}

		return stevilo;
	}

	//Metoda presteje vozlisca v celotnem drevesu
	public int prestejVozlisca()
	{
		return prestejVozlisca(koren);
	}

	//Metoda izpise vse sinove oceta, ki ga dolocimo na podlagi podanega imena
	public void izpisiSinove(String ime)
	{
		Oseba v = poisci(ime, koren);
		if (v != null) {
			Oseba sin = v.sin;

			while (sin != null)
			{
				System.out.print(sin.ime + ", ");
				sin = sin.brat;
			}

			System.out.println();
		}
	}

	public void izpisiSinove_DrugaResitev(String ime)
	{
		izpisiPotomce(ime, 1);
	}

	//Pomozna metoda: izpise vse potomce podanega vozlisca 'v'
	private void izpisiVsePotomce(Oseba v)
	{
		Oseba sin = v.sin;

		while (sin != null)
		{
			System.out.print(sin.ime + ", ");
			izpisiVsePotomce(sin);

			sin = sin.brat;
		}
	}

	//Metoda izpise vse potomce osebe, ki je podana z imenom
	public void izpisiVsePotomce(String ime)
	{
		Oseba v = poisci(ime, koren);
		if (v != null)
		{
			izpisiVsePotomce(v);
			System.out.println();
		}
	}

	//Metoda izpise vse prednike osebe, ki je podana z imenom
	public void izpisiVsePrednike(String ime)
	{
		Oseba v = poisci(ime, koren);
		if (v != null)
		{
			Oseba prednik = v.oce;

			while(prednik != null)
			{
				System.out.print(prednik.ime + ", ");
				prednik = prednik.oce;
			}

			System.out.println();
		}
	}

	//Metoda izpise vse vnuke osebe, ki je podana z imenom
	public void izpisiVnuke(String ime)
	{
		izpisiPotomce(ime, 2);
	}

	//Metoda izpise vse pravnuke osebe, ki je podana z imenom
	public void izpisiPravnuke(String ime)
	{
		izpisiPotomce(ime, 3);
	}

	//Metoda izpise vse strice osebe, ki je podana z imenom
	public void izpisiStrice(String ime)
	{
		Oseba jaz = poisci(ime, koren);
		Oseba dedek = poisciPrednika(jaz, 2);

		if (jaz == null || dedek == null)
			return;

		Oseba s = dedek.sin;

		while(s != null)
		{
			if (s != jaz.oce)
				System.out.print(s.ime + ", ");

			s = s.brat;
		}

		System.out.println();
	}

	//Metoda izpise vse bratrance osebe, ki je podana z imenom
	public void izpisiBratrance(String ime)
	{
		Oseba jaz = poisci(ime, koren);
		Oseba dedek = poisciPrednika(jaz, 2);

		if (jaz == null || dedek == null)
			return;

		Oseba s = dedek.sin;

		while(s != null)
		{
			if (s != jaz.oce)
				izpisiPotomce(s, 1);

			s = s.brat;
		}

		System.out.println();
	}

	//Pomozna metoda: vrne kazalec na prednika vozlisca 'v' (v podanem kolenu)
	private Oseba poisciPrednika(Oseba v, int koleno)
	{
		if (v == null || koleno <= 0)
			return null;

		if (koleno == 1)
			return v.oce;
		else
			return poisciPrednika(v.oce, koleno - 1);
	}

	//Pomozna metoda: izpise vse potomce (v podanem kolenu) vozlisca 'v'
	private void izpisiPotomce(Oseba v, int koleno)
	{
		if (v == null || koleno <= 0)
			return;

		Oseba s = v.sin;

		while(s != null)
		{
			if (koleno == 1)
				System.out.print(s.ime + ", ");
			else
				izpisiPotomce(s, koleno - 1);

			s = s.brat;
		}
	}

	//Pomozna metoda: izpise vse potomce (v podanem kolenu) osebe, ki je podana z imenom
	private void izpisiPotomce(String ime, int koleno)
	{
		Oseba v = poisci(ime, koren);
		if (v != null)
		{
			izpisiPotomce(v, koleno);
			System.out.println();
		}
	}

}
