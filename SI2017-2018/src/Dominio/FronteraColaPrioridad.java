package Dominio;

import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Stack;



public class FronteraColaPrioridad {
	boolean optimizo=false;
	public static Hashtable<String, Integer> visited = new Hashtable<String, Integer>();
	static PriorityQueue<Nodo> cola = new PriorityQueue<Nodo>();

	public FronteraColaPrioridad(PriorityQueue<Nodo> cola,Hashtable <String,Integer>visited)

	{
		this.cola = cola;
		this.visited=visited;
	}
	public FronteraColaPrioridad(PriorityQueue<Nodo> cola)

	{
		this.cola = cola;
		
	}
	public void insertar(Nodo nodo) {

		cola.offer(nodo);
	}
	public void insertaLista(Stack<Nodo> LN,int est,boolean poda){
		while(!LN.isEmpty()){
			Nodo n = LN.pop();
			
			if(poda) {
				
				  if( checkVisited(n, ObtenerEst(est))) insertar(n);
			// If not optimization
			}
			else {
				insertar(n);
			}
			
			
	}//metodo añadido en la pract3
	}
	private String ObtenerEst(int est) {
		String estg=null;
		switch (est) {
		case 1:					
			estg="BFS"; 
			break;
		case 2:
			estg="DFS";
			break;
		case 3:
			estg="DLS";
			break;
		case 4:
			estg="IDS";
			break;
		case 5:
			estg="UCS";
			break;
		case 6:
			estg="A_STAR";
			break;
		}
		
		return estg;
	}

	public void crearFronteraColaPrioridad() {
		cola = new PriorityQueue<Nodo>();

	}

	public Nodo Elimina() {
		return cola.remove();
	}

	public boolean esVacia() {
		if (cola.size() == 0)
			return true;
		return false;

	}
	private static boolean checkVisited(Nodo node, String strategy) {
		String serial = node.serialize();
		if(!visited.containsKey(serial)) {
			visited.put(serial,ObtenerValor(node,strategy));
			return true;
			
		}else {
			if(visited.get(serial) >ObtenerValor(node,strategy) ) { /****/
				visited.remove(serial);
				visited.put(serial,ObtenerValor(node,strategy));
				return true;
				
			}else {/*No nothing*/  return false;}
		}
		
	}
	private static int ObtenerValor(Nodo node,String strategy) {
		if(strategy.equals("BFS") || strategy.equals("DFS") || strategy.equals("DLS") || strategy.equals("IDS"))
			return node.GetCosto();
		else
			return (int)node.getValor();
		
	}
	
}
