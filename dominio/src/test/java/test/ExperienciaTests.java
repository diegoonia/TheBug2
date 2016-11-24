package test;

import org.junit.Assert;
import org.junit.Test;

import npc.Resistencia;
import personaje.Humano;

public class ExperienciaTests {

 /*   Dado un Personaje y un NPC, cuando el Personaje 
 *   elimina al NPC entonces aumenta el nivel de experiencia del Personaje:
 */
	@Test
	public void aumentarExperienciaAlMatarNPC(){
		Humano diego = new Humano("diego");
		Resistencia npc = new Resistencia();
	
		for(int i=0; i<2; i++)
			diego.atacar(npc);
		

		// Lo ataco hasta matarlo y me tiene que dar 10 de experiencia
		Assert.assertEquals(10, diego.getExperiencia());
	}
	
/*
 *   * Dado un Personaje y un Personaje Enemigo, cuando el Personaje elimina al 
 *    Personaje Enemigo entonces aumenta el nivel de experiencia del Personaje.
 */
	@Test
	public void aumentarExperienciaAlMatarPersonajeEnemigo(){
		Humano diego = new Humano("diego");
		Humano matias = new Humano("matias");
		
		for(int i=0; i<10; i++)
			diego.atacar(matias);
		
		// Lo ataco hasta matarlo y me tiene que dar 10 de experiencia
		Assert.assertEquals(10, diego.getExperiencia());
	}
	
 /* Dado un Personaje y un Personaje Enemigo cuando el Personaje
 elimina al Jugador Enemigo entonces el Jugador Enemigo no pierde experiencia.
 */
	@Test
	public void noPierdeExpAlMorir(){
		Humano diego = new Humano("diego");
		Humano matias = new Humano("matias");
		
		for(int i=0; i<10; i++)
			diego.atacar(matias);
		
		Assert.assertEquals(10, matias.getExperiencia());
	}
		
/* Dado un Personaje, cuando obtenga una cierta cantidad de experiencia 
 * entonces subire de nivel.
 */
	@Test
	public void subirDeNivelAlPasarExp(){
		Humano diego = new Humano("diego");
		Humano matias = new Humano("matias");

		for (int j = 0; j < 10; j++) {  // lo mato 10 veces para obtener 100 de exp.
			for(int i=0; i<50; i++){ // le pego 50 ataques de 10 de daño, para matarlo
				diego.atacar(matias);
				diego.serEnergizado();
			}		
			matias.revivir(); //Lo revivo para matarlo de nuevo
		}
		Assert.assertEquals(2, diego.getNivel());
	}

/*  Dado un Nivel de experiencia, cuando el Nivel de experiencia 
 * sea mayor entonces requerirá mas cantidad de experiencia para subir de nivel.
 */
	@Test
	public void masNivelNecesitaMasExperiencia(){
		Humano diego = new Humano("diego");
		Humano matias = new Humano("matias");
		// Para pasar a nivel 2, necesita 100 de experiencia
		for (int j = 0; j < 10; j++) {  // lo mato 10 veces para obtener 100 de exp.
			for(int i=0; i<50; i++){ // le pego 50 ataques de 10 de daño, para matarlo
				diego.atacar(matias);
				diego.serEnergizado();
			}		
			matias.revivir(); //Lo revivo para matarlo de nuevo
		}
		Assert.assertEquals(2, diego.getNivel());
		// Para pasar a nivel 3, necesita 200 de experiencia
		for (int j = 0; j < 20; j++) {  // lo mato 20 veces para obtener 200 de exp.
			for(int i=0; i<50; i++){ // le pego 50 ataques de 10 de daño, para matarlo
				diego.atacar(matias);
				diego.serEnergizado();
			}		
			matias.revivir(); //Lo revivo para matarlo de nuevo
		}
		Assert.assertEquals(3, diego.getNivel());
	}
	
	/* Dado un Nivel de experiencia, cuando el Nivel de experiencia
	llegue al nivel máximo entonces este será nivel 32.  */
	@Test
	public void maximoNivel32(){
		Humano diego = new Humano("diego");
		Resistencia npc = new Resistencia();

		for (int j = 0; j < 9000; j++) { // Lo mato 9000 veces para conseguir 90000 exp
			for(int i=0; i<10; i++){
				diego.atacar(npc);
				diego.serEnergizado();
			}
			npc.setSalud(100); // No se porque no le funciona el revivir al npc, le meti setSalud
		}
		Assert.assertEquals(32, diego.getNivel());
	}
			
}
