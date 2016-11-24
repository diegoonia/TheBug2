package comunicacion;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String pass;
    private String nombre;
    private String accion;
    private  ArrayList<Socket> listaDeConexionesMundo1 = new ArrayList<>();
   
	private  ArrayList<Socket> listaDeConexionesMundo2 = new ArrayList<>();
    private int mundoSelect=0;

    public User() {
        
    }

    public User(String pass, String nombre,String accion, ArrayList<Socket> listaDeConexionesMundo1,ArrayList<Socket> listaDeConexionesMundo2,int mundoSelect) {
        this.nombre = nombre;
        this.pass = pass;
        this.accion = accion;
        this.listaDeConexionesMundo1=listaDeConexionesMundo1;
        this.listaDeConexionesMundo2=listaDeConexionesMundo2;
        this.mundoSelect=mundoSelect;
        
        
    }
    public ArrayList<Socket> getListaDeConexionesMundo1() {
		return listaDeConexionesMundo1;
	}

	public void setListaDeConexionesMundo1(ArrayList<Socket> listaDeConexionesMundo1) {
		this.listaDeConexionesMundo1 = listaDeConexionesMundo1;
	}

	public ArrayList<Socket> getListaDeConexionesMundo2() {
		return listaDeConexionesMundo2;
	}

	public void setListaDeConexionesMundo2(ArrayList<Socket> listaDeConexionesMundo2) {
		this.listaDeConexionesMundo2 = listaDeConexionesMundo2;
	}

    public int getMundoSelec() {
        return this.mundoSelect;
    }
    public void setMundoSelec(int m) {
        this.mundoSelect = m;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAccion() {
        return this.accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
