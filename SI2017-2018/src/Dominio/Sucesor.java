package Dominio;

public class Sucesor {

	private String accion;
	private Estado estado;
	private int costo;

	public Sucesor(String accion, Estado estado, int costo) {
		this.accion = accion;
		this.estado = estado;
		this.costo = costo;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getCosto() {
		return estado.getCosto();
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}
}