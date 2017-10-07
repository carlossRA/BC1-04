import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class terreno {
	int filas,columnas;
	casilla cas [][] ;
	int xt=0;
	int yt=0;
	int k=0;
	int max=0;
	int c=0;
	int f=0;
	int [] MovimientosPosibles = new int[4];
	public terreno(casilla cas[][]) 
	{
		this.cas=cas;
	}	
	public void crearTerreno() 
	{
		for(int i=0;i<cas.length;i++) 
    		for(int j=0;j<cas.length;j++)
    		{	
    			cas[i][j]=new casilla(Integer.parseInt(JOptionPane.showInputDialog("Introduzca cantidad casilla "+i+" "+j)),i,j); 
    		}
	}
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
	 public void mostrarMovimientosPosibles(){
         
	        System.out.print("\nLos movimientos válidos son: \n\n");
	         
	        if(MovimientosPosibles[0] == 1) System.out.println("Izquierda");
	        if(MovimientosPosibles[1] == 1) System.out.println("Derecha");
	        if(MovimientosPosibles[2] == 1) System.out.println("Arriba");
	        if(MovimientosPosibles[3] == 1) System.out.println("Abajo");
	    }
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
	private int decidirAleatorio(int[] movimientosPosibles) {
    	Random rn=new Random();
    	int sel=rn.nextInt(4);
    	
    	while(movimientosPosibles[sel]==0)//Si el movimiento no es posible debe buscarse otro.
    		sel=rn.nextInt(4);
		return sel;
	}
	public void EscribirFichero()
	{
		
		FileWriter fichero = null;
		PrintWriter pw = null;
		        try
		        {
		            fichero = new FileWriter("terreno.txt");
		            pw = new PrintWriter(fichero);

		            for (int i=0;i<cas.length;i++) {
		               for(int j=0;j<cas[i].length;j++) {
		            	   pw.println(cas[i][j]+" ");
		               }
		               System.out.println();
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		           try {
		           // Nuevamente aprovechamos el finally para 
		           // asegurarnos que se cierra el fichero.
		           if (null != fichero)
		              fichero.close();
		           } catch (Exception e2) {
		              e2.printStackTrace();
		           }
		        }
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
