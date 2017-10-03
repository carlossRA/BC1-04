import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

/*-- Clase terreno --
 * 
 * En esta clase definimos el objeto terreno y sus atributos.
 * 
 *  */

public class terreno {
	int filas,columnas;
	casilla cas [][] ;
	int xt=0;
	int yt=0;
	int k=0;
	
	/* -- Variables --
	 * 
	 * c = representa la columna
	 * f = representa la fila
	 * max= representa la cantidad maxima que puede haber en una casilla
	 * movimientosPosibles = es un array de movimientos posibles que podemos hacer en una determinada posicion del tablero.
	 * 
	 * */
	
	int max=0;
	int c=0;
	int f=0;
	int [] MovimientosPosibles = new int[4];
	public terreno(casilla cas[][]) 
	{
		this.cas=cas;
	}
	
	/*-- Metodo crearTerreno --
	 * 
	 * nos permite crear un terreno con unas dimensiones especificas.
	 *  
	 *  */
	
	public void crearTerreno() 
	{
		for(int i=0;i<cas.length;i++) 
    		for(int j=0;j<cas.length;j++)
    		{	
    			cas[i][j]=new casilla(Integer.parseInt(JOptionPane.showInputDialog("Introduzca cantidad casilla "+i+" "+j)),i,j); 
    		}
	}
	
	/*-- Metodo leerTerreno --
	 * 
	 * nos permite leer un fichero con los datos especificos de un terreno para después crearlo.
	 *  
	 *  */
	
	public void leerTerreno() throws FileNotFoundException
	{
		File file=new File("terreno.txt");
		Scanner datos=new Scanner(file);
			xt=datos.nextInt();
			yt=datos.nextInt();
			k=datos.nextInt();
			max=datos.nextInt();
			c=datos.nextInt();
			f=datos.nextInt();
			
				for(int i=0;i<cas.length&&datos.hasNext();i++) 
		    		for(int j=0;j<cas.length;j++)
		    		{	
		    			cas[i][j]=new casilla(datos.nextInt(),i-1,j);
		    		}
				
		datos.close();
	}
	
	public void obtenerNuevoTerreno()
	{
		
	}
	
	/*-- Metodo imprimirTerreno --
	 * 
	 * Imprimimos por consola la cantidad que hay en cada casilla de un terreno ya creado.
	 *  
	 *  */
	
	public void imprimirTerreno() {
		System.out.println(xt+" "+yt+" "+k+" "+max+" "+c+" "+f);
		for(int i=0;i<cas.length;i++) {
    		for(int j=0;j<cas.length;j++)
    		{	
    			System.out.print(cas[i][j].getCantidad()+" ");
    		}
    		System.out.println();
		}
	}
	
	/*-- Metodo MovimientosValidos --
	 * 
	 * calculamos los movimientos que son validos y los que no en cada situación.
	 *  
	 *  */
	
	 public void MovimientosValidos() {
	     
		// Inicializamos array de flags de movimiento
	    	for(int i = 0; i < 4; i++){
	    		MovimientosPosibles[i] = 0;
	    	}
	  
	    			if((xt==0||xt==(cas.length-1))||(yt==0||yt==(cas[1].length-1))){
	    				if((xt==0)){
	    					if(yt==0){
	    						
	    						MovimientosPosibles[1] = 1;
	    						MovimientosPosibles[3] = 1;
	    					}
	    					else if(yt==(cas[1].length-1)){
	    						MovimientosPosibles[0] = 1;
	    						MovimientosPosibles[3] = 1;
	    						
	    					}else {
	    						MovimientosPosibles[0] = 1;
	    						MovimientosPosibles[1] = 1;
	    						MovimientosPosibles[3] = 1;
	    						
	    					}
	    				}
	    				else if(xt==(cas.length-1)){
	    					if(yt==0){
	    						MovimientosPosibles[1] = 1;
	    						MovimientosPosibles[2] = 1;
	    						
	    					}
	    					else if(yt==(cas[1].length-1)){
	    						MovimientosPosibles[0] = 1;
	    						MovimientosPosibles[2] = 1;
	    						
	    					}else {
	    						MovimientosPosibles[0] = 1;
	    						MovimientosPosibles[1] = 1;
	    						MovimientosPosibles[2] = 1;
	    						
	    					}
	    				}
	    				else{
	    					if(yt==0){
	    						MovimientosPosibles[1] = 1;
	    						MovimientosPosibles[2] = 1;
	    						MovimientosPosibles[3] = 1;
	    						
	    					}else if (yt==(cas[1].length-1)){
	    						MovimientosPosibles[0] = 1;
	    						MovimientosPosibles[2] = 1;
	    						MovimientosPosibles[3] = 1;
	    						
	    					}
	    				}
	    			}else {
	    				MovimientosPosibles[0] = 1;
						MovimientosPosibles[1] = 1;
						MovimientosPosibles[2] = 1;
						MovimientosPosibles[3] = 1;
	    				
	    			}
	    			
	    	
	    	
	    }
	 
	 /*-- Metodo mostrarMovimientosPosibles --
	  * 
	  * mostramos los movimientos que son posibles en ese estado.
	  *  
	  *  */
	
	 public void mostrarMovimientosPosibles(){
         
	        System.out.print("\nLos movimientos válidos son: \n\n");
	         
	        if(MovimientosPosibles[0] == 1) System.out.println("Izquierda");
	        if(MovimientosPosibles[1] == 1) System.out.println("Derecha");
	        if(MovimientosPosibles[2] == 1) System.out.println("Arriba");
	        if(MovimientosPosibles[3] == 1) System.out.println("Abajo");
	    }
	 
	 /*-- Metodo generarAcción --
	  * 
	  * generamos un movimiento de forma aleatoria y lo imprimimos por pantalla.
	  *  
	  *  */
	 
	public void generarAccion()
	{	casilla aux;
		MovimientosValidos();
		int el=decidirAleatorio(MovimientosPosibles);
		if(el==0) {
			yt=yt-1;
			System.out.println("Izquierda");
		}else if(el==1) {
			yt=yt+1;
			System.out.println("Derecha");
		}else if(el==2) {
			xt=xt-1;
			System.out.println("Arriba");
		}else {
			xt=xt+1;
			System.out.println("Abajo");
		}
	}
	
	/*-- Metodo decidirAleatorio --
	 * 
	 * Generamos un numero aleatorio en función de los movimientos posibles que tengamos.
	 *  
	 *  */
	
	private int decidirAleatorio(int[] movimientosPosibles) {
    	Random rn=new Random();
    	int sel=rn.nextInt(4);
    	
    	while(movimientosPosibles[sel]==0)//Si el movimiento no es posible debe buscarse otro.
    		sel=rn.nextInt(4);
		return sel;
	}

	public int getxt() {
		return xt;
	}
	public int getyt() {
		return yt;
	}
	public int k() {
		return k;
	}
	public int max() {
		return max;
	}
	public int c() {
		return c;
	}
	public int f() {
		return f;
	}
}
