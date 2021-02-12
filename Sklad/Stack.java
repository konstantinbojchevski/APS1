class StackElement
{
	Object element;
	StackElement next;

	StackElement()
	{
		element = null;
		next = null;
	}
}

class Stack
{
	//StackElement -> StackElement -> StackElement -> ... -> StackElement
	//     ^
	//     |
	//    top                                                   
	//
	// elemente dodajamo in brisemo vedno na zacetku seznama (kazalec top)
	
	
	private StackElement top;
	
	public Stack()
	{
		makenull();
	}
	
	public void makenull()
	{
		top = null;
	}
	
	public boolean empty()
	{
		return (top == null);
	}
	
	public Object top()
	{
		if (!empty())
			return top.element;
		else
			return null;
	}
	
	public void push(Object obj)
	{
		StackElement el = new StackElement();
		el.element = obj;
		el.next = top;
		
		top = el;
	}
	
	public void pop()
	{
		if (!empty())
		{
			top = top.next;
		}
	}
}
