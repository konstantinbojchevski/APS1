class BSTNode {
	Comparable key;
	BSTNode left;
	BSTNode right;
	
	public BSTNode(Comparable k) {
		key = k;
		left = null;
		right = null;
	}
}

class BSTree {
	BSTNode root;
	
	public BSTree() {
		makenull();
	}
	
	// Funkcija naredi prazno drevo
	void makenull() {
		root = null;
	}
	
	// Rekurzivna funkcija za vstavljanje elementa v list drevesa
	private BSTNode insertRek(BSTNode node, Comparable k)
	{
		if (node == null)
			node = new BSTNode(k);
		else if (k.compareTo(node.key) < 0)
			node.left = insertRek(node.left, k);
		else if (k.compareTo(node.key) > 0)
			node.right = insertRek(node.right, k);
		else
			;//element je ze v drevesu, ne naredimo nicesar
		return node;
	}
	
	// Rekurzivna funkcija za vstavljanje elementa v list drevesa
	public void insertRek(Comparable k) {
		root = insertRek(root, k);
	}
	
	// Rekurzivna funkcija za izpis poddrevesa s podanim korenom
	private void write(BSTNode node) {
		if (node != null) {
			System.out.print("(");
			write(node.left);
			System.out.print(", " + node.key + ", ");
			write(node.right);
			System.out.print(")");
		}
		else
			System.out.print("null");
	}
	
	// Funkcija za izpis drevesa
	public void write() {
		write(root);
		System.out.println();
	}
	
	// Rekurzivna funkcija, ki preveri, ali se podani element nahaja v podanem poddrevesu
	private boolean memberRek(BSTNode node, Comparable k) {
		if (node == null)
			return false;
		else if (k.compareTo(node.key) == 0)
			return true;
		else if (k.compareTo(node.key) < 0)
			return memberRek(node.left, k);
		else
			return memberRek(node.right, k);
	}
	
	// Funkcija preveri, ali se podani element nahaja v drevesu
	public boolean memberRek(Comparable k) {
		return memberRek(root, k);
	}
	
	
	//Samostojno delo
	
	// Rekurzivna funkcija, ki poreze liste podanega poddrevesa
	protected BSTNode prune(BSTNode node)
	{
		if (node == null)
			return null;
		
		if (node.left == null && node.right == null)
			return null;
		
		node.left = prune(node.left);
		node.right = prune(node.right);
		return node;
	}

	// Funkcija, ki poreze liste drevesa
	public void prune()
	{
		root = prune(root);
	}
	
	// Rekurzivna funkcija, ki vrne visino podanega poddrevesa
	protected int height(BSTNode node) {
		if (node == null)
			return 0;
		else
			return Math.max(height(node.left), height(node.right)) + 1;
	}
	
	// Funkcija, ki vrne visino drevesa
	public int height() {
		return height(root);
	}
	
	// Rekurzivna funkcija, ki vrne stevilo elementov v podanem poddrevesu
	private int numOfElements(BSTNode node) {
		if (node == null)
			return 0;
		
		return numOfElements(node.left) + 1 + numOfElements(node.right);
	}
	
	// Funkcija, ki vrne stevilo elementov v drevesu
	public int numOfElements() {
		return numOfElements(root);
	}
	
	// Iterativna funkcija za vstavljanje elementa v list drevesa
	public void insertIter(Comparable k) {
		if (root == null) {
			root = new BSTNode(k);
			return;
		}
		
		BSTNode curNode = root;
		
		while(true) {
			if (k.compareTo(curNode.key) == 0) return;
		
			if (k.compareTo(curNode.key) < 0) {
				if (curNode.left == null) {
					curNode.left = new BSTNode(k);
					return;
				}
				else
					curNode = curNode.left;
			}
			else {
				if (curNode.right == null) {
					curNode.right = new BSTNode(k);
					return;
				}
				else
					curNode = curNode.right;
			}
		}
	}
	
	// Iterativna funkcija, ki preveri, ali se podani element nahaja v drevesu
	public boolean memberIter(Comparable k) {
		BSTNode curNode = root;
		
		while (curNode != null) {
			if (k.compareTo(curNode.key) == 0) return true;
			
			if (k.compareTo(curNode.key) < 0)
				curNode = curNode.left;
			else
				curNode = curNode.right;
		}
		
		return false;
	}
	
	// Rekurzivna funkcija, ki izpise elemente podanega poddrevesa v padajocem vrstnem redu
	private void descending(BSTNode node) {
		if (node != null) {
			descending(node.right);
			System.out.print(node.key + ", ");
			descending(node.left);
		}
	}
	
	// Funkcija, ki izpise elemente drevesa v padajocem vrstnem redu
	public void descending() {
		descending(root);
		System.out.println();
	}
	
	// Rekurzivna funkcija, ki preveri, ali je podano poddrevo uravnoveseno
	// (neoptimalna resitev)
	private boolean isBalanced(BSTNode node) {
		if (node == null)
			return true;
		else
			return isBalanced(node.left) && isBalanced(node.right) 
				&& (Math.abs(height(node.right) - height(node.left)) < 2);  
	}
	
	// Funkcija, ki preveri, ali je drevo uravnoveseno
	public boolean isBalanced() {
		return isBalanced(root);
	}
	
	// Bolj optimalna izvedba funkcije, ki preveri ali je 
	// podrevo s korenom "node" poravnano.
	//
	// Ideja je v tem, da "zdruzimo" funkciji za izracun visine drevesa in
	// preverjanje, ali je korensko vozlisce poddrevesa poravnano.
	//
	// Funkcija vraca visino poddrevesa v korenu "node", ki je pozitivna vrednost.
	// Ce se izkaze, da vozlisce ni poravnano, vrne negativno vrednost
	
	private int isBalancedOpt(BSTNode node)
	{
		if (node == null)
			return 0;
		
		int l = isBalancedOpt(node.left);
		if (l < 0)
			return -1;
		
		int r = isBalancedOpt(node.right);
		if (r < 0)
			return -1;
		
		if (Math.abs(l-r) > 1)
			return -1;
		
		return Math.max(l, r) + 1;
	}
	
	// Bolj optimalna izvedba funkcije
	public boolean isBalancedOpt() {
		return isBalancedOpt(root) >= 0;
	}
	
	
	
	
	
	
	
	// Funkcija, ki vrne kazalec na element drevesa s prvo manjso vrednostjo kljuca od podane vrednosti
	public BSTNode predecessor(Comparable k) {
		BSTNode resNode = null;
		BSTNode curNode = root;
		
		while(curNode != null) {
			if (k.compareTo(curNode.key) > 0) {
				resNode = curNode;
				curNode = curNode.right;
			}
			else
			if (k.compareTo(curNode.key) <= 0) {
				curNode = curNode.left;
			}	
		}
		
		return resNode;
	}
	
	// Funkcija, ki vrne kazalec na element drevesa s prvo vecjo vrednostjo kljuca od podane vrednosti
	public BSTNode successor(Comparable k) {
		BSTNode resNode = null;
		BSTNode curNode = root;
		
		while(curNode != null) {
			if (k.compareTo(curNode.key) < 0) {
				resNode = curNode;
				curNode = curNode.left;
			}
			else
			if (k.compareTo(curNode.key) >= 0) {
				curNode = curNode.right;
			}	
		}
		
		return resNode;
	}
}	

