package Dominio;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Stack;

public class CreaFichero {

	List <Nodo> listaSolucion;
	
	public CreaFichero(List <Nodo> listaSolucion) {
		this.listaSolucion = listaSolucion;
	}
	
	public void CreacionFichero(int opcion,Terreno ter,long costeTemp,boolean poda) throws IOException{
	  // Generacion de salida en fichero de texto con la solucion
	 FileWriter fichero = null;
     PrintWriter pw = null;
   
   try
   {
  	 Stack<String> pilaSolucion = new Stack<String>();
  	
  	   String estrategia=calculaEstrategia(opcion);
       fichero = new FileWriter("Salida"+estrategia+"_"+ter.getFilas()+"x"+ter.getColumnas()+".txt");
       pw = new PrintWriter(fichero);
       pw.println("poda: "+poda);
       pw.println("Estrategia: "+estrategia+"\n ");
       pw.println("Terreno de "+ter.getFilas()+" filas y "+ter.getColumnas()+" columnas.");
       pw.println("Tiempo que tarda en ejecutarse el algoritmo: "+costeTemp+" ms.");
       pw.println("\n\nSolucion: ");
       Nodo aux = null;
      
       while(listaSolucion.size()>1)
       {     aux=listaSolucion.remove(0);
       
 			 pilaSolucion.add(aux.getEstado().getTerreno().getTer()+"-)Acción realizada en el estado anterior: "+aux.getAccion()+"\n-)costo hasta aquí:"+aux.GetCosto()+
 					 "\n-)Profundidad "+aux.getProfundidad()+"\n");
 			  
       }
       
 			 pw.println();
 			 
 		pw.println("Nodo raíz:\n "+ter.getTer()+"-)Coste cero\n"+"-)Profundidad:1\n");	
       while(!pilaSolucion.isEmpty()) {
           pw.println(pilaSolucion.pop());
        
       }
    
   } catch (Exception e) {
       e.printStackTrace();
       }
	fichero.close();
	}

	private String calculaEstrategia(int opcion) {
		String estrategia="";
		switch(opcion)
		{
		case 1:
			estrategia="Anchura";
		break;
		case 2:
			estrategia ="ProfundidadSimple";
		break;
		case 3:
			estrategia ="ProfundidadAcotada";
		break;
		case 4: 
			estrategia ="ProfundidadIterativa";
		break;
		case 5:
			estrategia="CostoUniforme";
		break;
		case 6:
			estrategia="A";
		}
		return estrategia;
		
	}	
}