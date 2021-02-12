class QueueElement
{
	Object element;
	QueueElement next;

	QueueElement()
	{
		element = null;
		next = null;
	}
}

class Queue
{
	//QueueElement -> QueueElement -> QueueElement -> ... -> QueueElement
	//    front                                                   rear
	//
	// nove elemente dodajamo na rear strani
	// elemente jemljemo s front strani
	
	private QueueElement front;
	private QueueElement rear;
	
	public Queue()
	{
		makenull();
	}
	
	public void makenull()
	{
		front = null;
		rear = null;
	}
	
	public boolean empty()
	{
		return (front == null);
	}
	
	public Object front()
	{
		if (!empty())
			return front.element;
		else
			return null;
	}
	
	public void enqueue(Object obj)
	{
		QueueElement el = new QueueElement();
		el.element = obj;
		el.next = null;
		
		if (empty())
		{
			front = el;
		}
		else
		{
			rear.next = el;
		}
		
		rear = el;
	}
	
	public void dequeue()
	{
		if (!empty())
		{
			front = front.next;
			
			if (front == null)
				rear = null;
		}
	}
}
