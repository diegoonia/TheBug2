package comunicacion;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
public class AccionesAEnviar implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;
    private String nombre;
    private String accion;
	public AccionesAEnviar(int x, int y, String nombre, String accion) {
		this.x = x;
		this.y = y;
		this.nombre = nombre;
		this.accion = accion;
	}
	public AccionesAEnviar() {
		this.x = 0;
		this.y = 0;
		this.nombre = "";
		this.accion = "";
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
  
  
}
