package alianza;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import logica.MapaAlianza;
import personaje.Personaje;

public class Batallon {

	protected int proxVict;	
	protected List<Personaje> luchadores = new LinkedList<Personaje>();
	protected List<Personaje> luchadoresDead = new LinkedList<Personaje>();
	
	
	/*
	 * Esto tendra que resolverlo el server
	 * */
	
	public Batallon ( Collection<Personaje> personaje){		
		this.proxVict = -1;
		luchadores= (List<Personaje>) personaje;
	}
	// CUANDO LLAME A BATALLON AL CONTRUSCTOR LE PASO HAY EQUIPO DE MAPA ALIANZA
		
	public final void limpiarBatallon() {
		for(Personaje integrante : luchadores){
			if(!integrante.estaVivo()){
				luchadoresDead.add(integrante);
				luchadores.remove(integrante);
			}
		}
	}
	

	public void atacar(Batallon otro) throws FileNotFoundException {
		Personaje victima = otro.obtenerProximaVictima();
		for (Personaje atacante : luchadores) {
			if(atacante.puedeAtacar()){
				atacante.atacar(victima);
			} else {
				atacante.serEnergizado();
				
			}
			
		}
	}
	
	public int ObtenerExperiencia(){
		int cont=0;
		for(Personaje p : luchadores)
			 cont += p.getExperiencia();
		return cont*10;
	}


	public Personaje obtenerProximaVictima() {
		limpiarBatallon();
		this.proxVict++;
		
		if(this.proxVict >= this.luchadores.size())
			this.proxVict = 0;
		
		if(luchadores.isEmpty()) {		
			System.out.println("El batallon no tiene integrantes");
		}
		return luchadores.get(this.proxVict);
	}
	
	
	public boolean comprobarSiHayAlguienVivo(){
		boolean bandera = false;
		for(int i = 0; i< this.luchadores.size();i++)
			if(luchadores.get(i).estaVivo())
				bandera = true;	
		return bandera;
		
	}
	
	
	public void repartirExperiencia(int experiencia){
		int puntos = experiencia / luchadores.size();
		
		for (int i = 0; i<luchadores.size();i++){
			if(luchadores.get(i).estaVivo())
				luchadores.get(i).setExperiencia(luchadores.get(i).getExperiencia()+puntos);
		}
	}
	
	public List<String> verGanador() {
        LinkedList<String> ganadores = new LinkedList<String>();
        
        for (int i = 0; i < luchadores.size();i++){
            ganadores.add(luchadores.get(i).getNombre());
        }
        return ganadores;
    }

	public List<Personaje> ObtenerListaPeleadores() {
		return luchadores;
	}

	public void setListaPeleadores(List<Personaje> listaPeleadores) {
		this.luchadores = listaPeleadores;
	}
	
	public boolean añadirPersonaje(Personaje personaje) {
		return luchadores.add(personaje);
	}
	
	
}