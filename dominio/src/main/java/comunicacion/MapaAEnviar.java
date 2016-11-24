package comunicacion;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
public class MapaAEnviar implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String pass;
    private String nombre;
    private String accion;
    private  ArrayList<Socket> listaDeConexionesMundo1 = new ArrayList<>();
   
	private  ArrayList<Socket> listaDeConexionesMundo2 = new ArrayList<>();
    private int mundoSelect=0;

    public MapaAEnviar() {
        
    }

 
}
