import java.util.*;

class Person
{	
	public Person(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}

	String name;
	
	// stevilo izstopnih povezav
	int outdegree;
	
	// pomozna spremenljivka, ki jo potrebujemo v metodah "chain" in "wedding"
	int distance;
	
	// spodnja atributa potrebujemo v metodi "vip" 
	boolean covered;
	boolean selected;
}

public class GlavniProgram {

	// Pomozna funkcija, ki generira nakljucni graf poznanstev
	public static DirectedGraph createSocialNetwork(int numPersons, double friendshipProb)
	{
		Random generator = new Random();
		
		DirectedGraph diGraph = new DirectedGraph();
		GraphVertex[] vertices = new GraphVertex [numPersons];
		
		for (int i = 0; i < vertices.length; i++)
		{
			vertices[i] = new GraphVertex(new Person("Oseba" + (i+1)));
			diGraph.insertVertex(vertices[i]);
		}
		
		for (int i = 0; i < vertices.length-1; i++)
			for (int j = i+1; j < vertices.length; j++)
			{
				if (generator.nextDouble() < friendshipProb)
				{
					diGraph.insertEdge(vertices[i], vertices[j], "prijatelj");
					diGraph.insertEdge(vertices[j], vertices[i], "prijatelj");
				}
			}
				
		return diGraph;
	}
	
	// Pomozna funkcija, ki poisce doloceno osebo v grafu
	public static GraphVertex findPerson(DirectedGraph diGraph, String name)
	{
		for (GraphVertex curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
		{
			// preveri, ali je to oseba, ki jo iscemo
			Person curPerson = (Person)curVertex.value;
			if (curPerson.name.equals(name))
				return curVertex;
		}
		
		// osebe s tem imenom ni v grafu
		return null;
	}
	
	// Pomozna funkcija, ki inicializira atribute oseb v grafu
	public static void initPersons(DirectedGraph diGraph)
	{
		for (GraphVertex curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
		{
			Person curPerson = (Person)curVertex.value;
			curPerson.distance = 0;
			
			// spodnja atributa potrebujemo pri metodi "vip"
			curPerson.selected = false;
			curPerson.covered = false;
		}
	}
	
	// Pomozna funkcija, ki preveri ali v grafu obstaja usmerjena povezava od vertex1 do vertex2 
	public static boolean checkFriends(DirectedGraph diGraph, GraphVertex vertex1, GraphVertex vertex2)
	{	
		for (GraphEdge curEdge = diGraph.firstEdge(vertex1); curEdge != null; curEdge = diGraph.nextEdge(vertex1, curEdge))
		{
			if (diGraph.endPoint(curEdge) == vertex2)
				return true;
		}
		
		return false;
	}
	
	
	
	
	//
	// NALOGE
	//
	
	// Implementirajte metodo, ki za vsako osebo v grafu izracuna stevilo njenih prijateljev
	public static void numFriends(DirectedGraph diGraph)
	{	
		for (GraphVertex curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
		{
			int n = 0;
			for (GraphEdge curEdge = diGraph.firstEdge(curVertex); curEdge != null; curEdge = diGraph.nextEdge(curVertex, curEdge))
			{
				n++;
			}
			
			Person curPerson = (Person)curVertex.value;
			curPerson.outdegree = n;
			
			//System.out.println(curPerson + " ima " + n + " prijateljev");
		}
	}
	
	// Implementirajte metodo int chain(DirectedGraph diGraph, String nameA, String nameB), 
	// ki vrne dolzino najkrajse "verige" poznanstev od osebe nameA do osebe nameB. 
	// Ce se osebi poznata, je dolzina verige 1. 
	// Ce se ne poznata, imata pa skupnega prijatelja, je dolzina verige 2 in tako naprej. 
	// Ce med osebama nameA in nameB ni poti v grafu poznanstev, funkcija vrne -1.
	public static int chain(DirectedGraph diGraph, String nameA, String nameB)
	{
		GraphVertex curVertex;
		Person curPerson;
			
		// vsem vozliscem nastavimo zacetno razdaljo na 0 
		initPersons(diGraph);
		
		// poiscemo zacetno in koncno osebo
		GraphVertex vertexA = findPerson(diGraph, nameA);
		GraphVertex vertexB = findPerson(diGraph, nameB);
		
		// ce osebi obstajata v grafu, zacnemo z iskanjem najkrajse verige poznanstev
		if (vertexA != null && vertexB != null)
		{
			Queue q = new Queue();
			q.enqueue(vertexA);
			
			while (!q.empty())
			{
				curVertex = (GraphVertex)q.front();
				q.dequeue();
				
				curPerson = (Person)curVertex.value;
				
				if (curVertex == vertexB)
					return curPerson.distance;
				else
				{
					for (GraphEdge curEdge = diGraph.firstEdge(curVertex); curEdge != null; curEdge = diGraph.nextEdge(curVertex, curEdge))
					{
						GraphVertex nextVertex = diGraph.endPoint(curEdge);
						Person nextPerson = (Person)nextVertex.value;
						
						if (nextPerson.distance == 0)
						{
							nextPerson.distance = curPerson.distance + 1;
							q.enqueue(nextVertex);
						}
					}
				}	
			}
		}
		
		return -1;
	}
	
	// Implementirajte metodo void wedding(DirectedGraph diGraph, String nameA, String nameB, int radij), 
		// ki izpise seznam povabljencev na poroko med osebama nameA in nameB. Pri tem argument "radius" doloca krog potencialnih povabljencev. 
		// Ce je radius=1, bodo na poroko povabljeni samo neposredni prijatelji mladoporocencev. 
		// Ce je radius=2, bodo povabljeni tudi neposredni prijatelji neposrednih prijateljev mladoporocencev in tako naprej. 
		// Pri izracunu upostevajte, da bo vsaka oseba povabljena samo enkrat, kljub temu, da ustreza razlicnim kriterijem za povabilo.
		public static void wedding(DirectedGraph diGraph, String nameA, String nameB, int radius)
	{
		GraphVertex curVertex;
		Person curPerson;
			
		// vsem vozliscem nastavimo zacetno razdaljo na 0 
		initPersons(diGraph);
		
		// poiscemo zacetno in koncno osebo
		GraphVertex vertexA = findPerson(diGraph, nameA);
		GraphVertex vertexB = findPerson(diGraph, nameB);
		
		// ce osebi obstajata v grafu, zacnemo z iskanjem povabljencev
		if (vertexA != null && vertexB != null)
		{
			Queue q = new Queue();
			q.enqueue(vertexA);
			q.enqueue(vertexB);
			
			while (!q.empty())
			{
				curVertex = (GraphVertex)q.front();
				q.dequeue();
				
				curPerson = (Person)curVertex.value;
				
				// nadaljujemo s povabili, ce opazovana oseba ni predalec (glede na radij)
				if (curPerson.distance < radius)
				{
					for (GraphEdge curEdge = diGraph.firstEdge(curVertex); curEdge != null; curEdge = diGraph.nextEdge(curVertex, curEdge))
					{
						GraphVertex nextVertex = diGraph.endPoint(curEdge);
						Person nextPerson = (Person)nextVertex.value;
						int nextDistance = curPerson.distance + 1;
						
						// ce ne zelimo, da povabita sama sebe
						if (nextVertex == vertexA || nextVertex == vertexB)
							continue;
						
						// v vrsto dodamo osebo, ki ni ze povabljena oziroma, ce pridemo do nje po krajsi poti (ta moznost obstaja, ce najprej preiscemo prostor z enega konca nato pa z drugega)
						if (nextPerson.distance == 0 || nextPerson.distance > nextDistance)
						{
							nextPerson.distance = nextDistance;
							q.enqueue(nextVertex);
						}
					}	
				}
			}
		}
		
		// izpisimo povabljene osebe
		System.out.print( ((Person)(vertexA.value)).name + " in " + ((Person)(vertexB.value)).name + " bosta povabila naslednje osebe: ");
		for (curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
		{
			curPerson = (Person)curVertex.value;
			if (curPerson.distance > 0)
				System.out.print(curPerson.name + ", ");
		}
		
		System.out.println();
	}
	
		
	// Implementirajte metodo void clique(DirectedGraph diGraph), ki poisce (in izpise) najvecjo skupino ljudi, v kateri se vsi poznajo med seboj
	// (drugace povedano, vsak je prijatelj z vsakim).
	public static void clique(DirectedGraph diGraph)
	{
		LinkedList maxClique = new LinkedList();
		boolean unique = true;
		
		for (GraphVertex curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
		{
			LinkedList curClique = new LinkedList();
			curClique.add(curVertex);
			
			// Hevristicna resitev s pozresnim iskanjem
			
			for (GraphVertex nextVertex = diGraph.nextVertex(curVertex); nextVertex != null; nextVertex = diGraph.nextVertex(nextVertex))
			{
				boolean addNextVertex = true;
				for (ListNode curNode = curClique.first(); !curClique.overEnd(curNode); curNode = curClique.next(curNode))
				{
					if (!checkFriends(diGraph, nextVertex, (GraphVertex)curClique.retrieve(curNode)) || !checkFriends(diGraph, (GraphVertex)curClique.retrieve(curNode), nextVertex))
					{
						addNextVertex = false;
						break;
					}
				}
				
				if (addNextVertex)
					curClique.add(nextVertex);
			}
			
			if (curClique.getSize() == maxClique.getSize())
				unique = false;
			else if (curClique.getSize() > maxClique.getSize())
			{
				maxClique = curClique.makeCopy();
				unique = true;
			}
		}
		
		System.out.print("Najvecja skupina ljudi, v kateri se vsi poznajo med seboj, je sestavljena iz naslednjih oseb ");
		if (unique)
			System.out.print("(resitev je enolicna): ");
		else
			System.out.print("(resitev ni enolicna): ");
		
		maxClique.print();
		
	}
	
	// Funkcija, ki poisce "vip" mnozico z izcrpnim iskanjem vseh kombinacij
	public static void vipExhaustive(DirectedGraph diGraph)
	{
		boolean unique = true;
		int numSelected = 0;
		int minSelected = Integer.MAX_VALUE;
		LinkedList minVip = new LinkedList();
		
		// na zacetku nobeno vozlisce ni niti izbrano niti pokrito
		initPersons(diGraph);
		
		do
		{
			// spodnja koda generira naslednjo kombinacijo izbranih oseb v grafu
			for (GraphVertex curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
			{
				Person curPerson = (Person)curVertex.value;
					
				if (curPerson.selected)
				{
					curPerson.selected = false;
					numSelected--;
				}
				else
				{
					curPerson.selected = true;
					numSelected++;
					break;
				}
			}
			
			// sedaj imamo izbrano kombinacijo vozlisc
			// poglejmo katera vozlisca so pokrita
			
			
			// najprej oznacimo vsa vozlisca za nepokrita...
			for (GraphVertex curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
			{
				Person curPerson = (Person)curVertex.value;
				curPerson.covered = false;
			}
			
			
			// ... nato pokrijemo vozlisca, ki so bodisi izbrana bodisi neposredni prijatelji izbranih vozlisc
			for (GraphVertex curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
			{
				Person curPerson = (Person)curVertex.value;
				if (curPerson.selected)
				{
					curPerson.covered = true;
					
					for (GraphEdge curEdge = diGraph.firstEdge(curVertex); curEdge != null; curEdge = diGraph.nextEdge(curVertex, curEdge))
					{
						GraphVertex nextVertex = diGraph.endPoint(curEdge);
						Person nextPerson = (Person)nextVertex.value;
						nextPerson.covered = true;
					}
				}
			}
			
			// na koncu preverimo, ali so vsa vozlisca grafa pokrita
			boolean bValid = true;
			for (GraphVertex curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
			{
				Person curPerson = (Person)curVertex.value;
				if (!curPerson.covered)
				{
					bValid = false;
					break;
				}
			}
			
			if (bValid)
			{
				// imamo potencialno resitev. Preverimo, ali je res najmanjsa ...
				if (numSelected < minSelected)
				{
					minVip.makenull();
				
					for (GraphVertex curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
					{
						Person curPerson = (Person)curVertex.value;
						if (curPerson.selected)
							minVip.add(curVertex);
					}
					
					
					minSelected = numSelected;
					unique = true;
				}
				else if (numSelected == minSelected)
					unique = false;
			}
			
		} while (numSelected > 0);
		
		System.out.print("Najmanjsa skupina ljudi, ki pozna vse osebe v grafu vsebuje naslednje osebe ");
		if (unique)
			System.out.print("(resitev je enolicna): ");
		else
			System.out.print("(resitev ni enolicna): ");
		
		minVip.print();
		
	}
	
	// Funkcija, ki poisce "vip" mnozico z uporabo hevristike (pozresno dodaja osebe z najvec prijatelji)
	public static void vipHeuristic(DirectedGraph diGraph)
	{
		GraphVertex curVertex;
		Person curPerson;
		
		// na zacetku nobeno vozlisce ni izbrano niti pokrito
		initPersons(diGraph);
		
		// vsaki osebi nastavimo stevilo prijateljev
		numFriends(diGraph);
		
		while (true)
		{
			int maxFriends = -1;
			GraphVertex selectedVertex = null;
			
			// izberemo nepokrito osebo z najvec prijatelji...
			for (curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
			{
				curPerson = (Person)curVertex.value;
				if (!curPerson.covered && curPerson.outdegree > maxFriends)
				{
					selectedVertex = curVertex;
					maxFriends = curPerson.outdegree;
				}
			}
			
			// ... ce oseba obstaja
			if (selectedVertex != null)
			{
				// oznacimo, da je izbrana in pokrita
				curPerson = (Person)selectedVertex.value;
				curPerson.selected = true;
				curPerson.covered = true;
				
				// pokrijemo tudi vse njene neposredne prijatelje
				for (GraphEdge curEdge = diGraph.firstEdge(selectedVertex); curEdge != null; curEdge = diGraph.nextEdge(selectedVertex, curEdge))
				{
					GraphVertex nextVertex = diGraph.endPoint(curEdge);
					Person nextPerson = (Person)nextVertex.value;
					nextPerson.covered = true;
				}
			}
			else
			{
				// ... osebe ni, pokrili smo graf
				break;
			}
		};
		
		// izpisimo izbrane osebe
		System.out.println("V VIP mnozici se (na podlagi hevristicne funkcije) nahajajo naslednje osebe: ");
		for (curVertex = diGraph.firstVertex(); curVertex != null; curVertex = diGraph.nextVertex(curVertex))
		{
			curPerson = (Person)curVertex.value;
			if (curPerson.selected)
				System.out.print(curPerson.name + ", ");
		}
				
		System.out.println();
	}
	
	// Implementirajte metodo void vip(DirectedGraph diGraph), ki izbere (in izpise) minimalno podmnožico oseb, za katere velja, 
	// da (skupinsko gledano) poznajo vse osebe v grafu. 
	// Drugace povedano, za vsako osebo v grafu velja, da je bodisi element izbrane podmnozice bodisi je neposredni znanec neke osebe iz izbrane podmnozice.
	public static void vip(DirectedGraph diGraph)
	{
		// optimalna resitev, ki preveri vse kombinacije izbranih vozlisc
		vipExhaustive(diGraph);
		
		// priblizna resitev, ki ima nizjo casovno kompleksnost
		vipHeuristic(diGraph);
	}
	
	public static void main(String[] args) 
	{
		DirectedGraph diGraph = createSocialNetwork(20, 0.1);
		diGraph.print();
		
		numFriends(diGraph);
		
		System.out.println("Veriga poznanstev med Osebo1 in Osebo5 je dolga " + chain(diGraph, "Oseba1", "Oseba5"));
		wedding(diGraph, "Oseba2", "Oseba9", 2);
		
		clique(diGraph);
		
		vip(diGraph);
	}

}
