import java.io.*;

public class Naloga3 {
	
	static class QueueElement
	{
		Object element;
		QueueElement next;

		QueueElement()
		{
			element = null;
			next = null;
		}
	}
	
	static class Queue
	{
		
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
			if(this.front == null)
				return null;
			return front.element;
		}

		public Object rear()
		{
			if(this.rear == null)
				return null;
			return rear.element;
		}
		
		public QueueElement first() {
			return this.front;
		}
		
		public QueueElement last() {
			return this.rear;
		}

		public void enqueue(Object obj)
		{
			QueueElement dodaj = new QueueElement();
			dodaj.element = obj;
			dodaj.next = null;
			if(rear == null && front == null) {
				rear = dodaj;
				front = dodaj;
			}
			else {
				rear.next = dodaj;
				rear = dodaj;
			}
		}
		
		public void enqueue_on_front(Object obj) {
			QueueElement dodaj = new QueueElement();
			dodaj.element = obj;
			dodaj.next = null;
			if(rear == null && front == null) {
				front = dodaj;
				rear = dodaj;
			}
			else {
				dodaj.next = front;
				front = dodaj;
			}
		}
		
		public void dequeue()
		{
			if(front == rear) {
				front = null;
				rear = null;
			}
			else front = front.next;
		}
		
		public void dequeue_from_rear() {
			QueueElement u = new QueueElement();
			u = front;
			if(front == rear) {
				front = null;
				rear = null;
			}
			else {
				while(u.next != rear) {
					u = u.next;
				}
				u.next = null;
				rear = u;
			}
		}
	}
	
	public static int najdi_I(int k, Object el, int n) {
		for(int i=0; i<n; i++) {
			if((i + (int) el) % n == k)
				return i;
		}
		return 0;
	}
	
	public static int najdi_k(int i, int p, int n) {
		for(int j=0; j<n; j++) {
			if((j + p + n) % n == i)
				return j;		
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {

		int n, v, k, p;
		String[] argumenti = new String[4];
    
		FileInputStream inputStream = new FileInputStream(args[0]);
		InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		
		String line = bufferedReader.readLine();
		argumenti = line.split(",");
		n = Integer.parseInt(argumenti[0]);
		v = Integer.parseInt(argumenti[1]);
		k = Integer.parseInt(argumenti[2]);
		p = Integer.parseInt(argumenti[3]);
		Queue[] tabela = new Queue[n];
		int counter = 0;
		while ((line = bufferedReader.readLine()) != null)
			{
				String[] str = line.split(",");
				tabela[counter] = new Queue();
				if(!line.equals(""))
					for(String a: str) {
						tabela[counter].enqueue_on_front((Integer.parseInt(a)));
					}
				counter++;
			}
		bufferedReader.close();
		
		//premestuvam
		Object premesti;
		for(int i=k; i>0; i--) {
			premesti = tabela[v].front();
			tabela[v].dequeue();
			int I = najdi_I(v, premesti, n);  //baram I
			tabela[I].enqueue(premesti);
			v = najdi_k(I, p, n); // v == k
		}
		
		//korak 1.
		Queue q = new Queue();
		Object stavi;
		int stevec = 0;
		while(stevec < n) {
			for(int i=0; i<n; i++) {
				stavi = tabela[i].rear();
				tabela[i].dequeue_from_rear();
				q.enqueue(stavi);
				if(stavi == null) stevec++;
			}
		}
	
		String[] bukvi = {"A", "B", "C", "Č", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "R", "S", "Š", "T", "U", "V", "Z", "Ž", 
			"a", "b", "c", "č", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "š", "t", "u", "v", "z", "ž", " "};
	
		PrintWriter printWriter = new PrintWriter(new File(args[1]), "UTF-8");
		QueueElement o = new QueueElement();
		o = q.first();
		while(o != null) {
			if(o.element != null) printWriter.print(bukvi[(int)o.element]);				
			o = o.next;
		}
		printWriter.print("\n");
		printWriter.close();	
	}
}