package test;

import org.junit.Assert;
import org.junit.Test;

import npc.Capacitor;
import npc.Resistencia;
import personaje.Humano;

public class PersonajeTests {

 /* Dado un Personaje, cuando el Personaje
 *  no tiene mas energia entonces no puede atacar.
 */
	@Test
	public void noAtacarCuandoNoHayEnergia(){
		Humano diego = new Humano("diego");
		Humano matias = new Humano("matias");
		Resistencia npc = new Resistencia();
		
		for(int i=0; i<50; i++)
			diego.atacar(matias); // Gaste 500 de energia despues de atacar
		
		// No pudo atacar porque no tiene mas energia, atacar me devuelva false.
		Assert.assertEquals(false, diego.atacar(npc));
	}
	
	@Test
	public void queMueraNpc(){
		Humano diego = new Humano("diego");
		Capacitor npc = new Capacitor();
		
		for(int i=0; i<10; i++){
			diego.atacar(npc); // le pego a full
			System.out.println(npc.getSalud());
		}
			
		// Murio el capacitor
		Assert.assertEquals(false, npc.estaVivo());
	}
	
	@Test
	public void queBajeSaludEnemigoDespuesDeAtacar(){
		Humano diego = new Humano("diego");
		Humano matias = new Humano("matias");
		
		matias.atacar(diego);
		
		Assert.assertEquals(50,diego.getSalud());
		
	}
	
	@Test
	public void noPuedeAtacarEnemigoMuerto(){
		
		Humano diego = new Humano("diego");
		Humano matias = new Humano("matias");
		
		for(int i=0; i<10; i++)
			matias.atacar(diego);
		
		// No pudo atacar porque no el personaje enemigo esta muerto 
		Assert.assertEquals(false, matias.atacar(diego));
	}
	
	@Test
	public void queElPersonajeSeaCurado(){
		
		Humano bruno = new Humano("bruno");
		Humano ivan = new Humano("ivan");
		
		for(int i=0; i<5; i++)
			bruno.atacar(ivan);
		
		Assert.assertEquals(50 ,ivan.getSalud());
		ivan.serCurado();
		Assert.assertEquals(100 ,ivan.getSalud());
	}
	
	@Test
	public void queElPersonajeSeaEnergizado(){
		
		Humano bruno = new Humano("bruno");
		Humano ivan = new Humano("ivan");
		
		
			ivan.atacar(bruno);
		
		Assert.assertEquals(0 ,ivan.getEnergia());
		ivan.serEnergizado();
		Assert.assertEquals(110 ,ivan.getEnergia());
	}
	
//	@Test
//	public void  queElPersonajeEliminadoSeaRevivido(){
//		
//		Humano diego = new Humano("diego");
//		Humano matias = new Humano("matias");
//		
//		for(int i=0; i<30; i++){
//			matias.atacar(diego);
//			System.out.println(diego.getSalud());
//			}
//		
//		Assert.assertEquals(false,diego.estaVivo());
//	}
//	
	
}
