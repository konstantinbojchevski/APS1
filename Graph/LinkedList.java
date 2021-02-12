class ListNode 
{
	Object element;
	ListNode next;
	
	public ListNode(Object obj, ListNode nextNode) 
	{
		element = obj;
		next = nextNode;
	}
}

public class LinkedList 
{
	private ListNode first;
	private int size;
	
	public LinkedList() 
	{
		makenull();
	}
	
	public void makenull() 
	{
		first = new ListNode(null, null);
		size = 0;
	}
	
	public ListNode first()
	{
		return first;
	}
	
	public ListNode next(ListNode pos)
	{
		return pos.next;
	}
	
	public Object retrieve(ListNode pos)
	{
		return pos.next.element;
	}
	
	public boolean overEnd(ListNode pos)
	{
		if (pos.next == null)
			return true;
		else
			return false;
	}
	
	public void add(Object element) 
	{
		first.next = new ListNode(element, first.next);
		size++;
	}
	
	public LinkedList makeCopy()
	{
		LinkedList newList = new LinkedList();
		
		for (ListNode curNode = first(); !overEnd(curNode); curNode = next(curNode))
		{
			newList.add(retrieve(curNode));
		}
		
		return newList;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void print()
	{
		for (ListNode curNode = first(); !overEnd(curNode); curNode = next(curNode))
		{
			System.out.print(retrieve(curNode) + ", ");
		}
		
		System.out.println();
	}
}
