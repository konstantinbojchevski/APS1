class GraphVertex
{
	Object value;
	GraphEdge firstEdge;
	GraphVertex nextVertex;
		
	public GraphVertex(Object val)
	{
		value = val;
		firstEdge = null;
		nextVertex = null;
	}
	
	public String toString()
	{
		return value.toString();
	}
}

class GraphEdge
{
	Comparable evalue;
	GraphVertex endVertex;
	GraphEdge nextEdge;
		
	public GraphEdge(Comparable eval, GraphVertex eVertex, GraphEdge nEdge)
	{
		evalue = eval;
		endVertex = eVertex;
		nextEdge = nEdge;
	}
}
	

public class DirectedGraph {

	protected GraphVertex fVertex;
	
	public void makenull()
	{
		fVertex = null;
	}
	
	public void insertVertex(GraphVertex v)
	{
		v.nextVertex = fVertex;
		fVertex = v;
	}
	
	public void insertEdge(GraphVertex v1, GraphVertex v2, Comparable eval)
	{
		GraphEdge newEdge = new GraphEdge(eval, v2, v1.firstEdge);
		v1.firstEdge = newEdge;
	}
	
	public GraphVertex firstVertex()
	{
		return fVertex;
	}
	
	public GraphVertex nextVertex(GraphVertex v)
	{
		return v.nextVertex;
	}
	
	public GraphEdge firstEdge(GraphVertex v)
	{
		return v.firstEdge;
	}
	
	public GraphEdge nextEdge(GraphVertex v, GraphEdge e)
	{
		return e.nextEdge;
	}
	
	public GraphVertex endPoint(GraphEdge e)
	{
		return e.endVertex;
	}
	
	public void print()
	{
		for (GraphVertex v = firstVertex(); v != null; v = nextVertex(v)) 
		{
			System.out.print(v + ": ");
			for (GraphEdge e = firstEdge(v); e != null; e = nextEdge(v, e))
				System.out.print(endPoint(e) + "(" + e.evalue + ")" + ", ");
			System.out.println();
		}
	}
}
