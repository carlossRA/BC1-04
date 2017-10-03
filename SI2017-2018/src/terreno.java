import java.io.File;
import java.io.FileNotFoundException;
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
