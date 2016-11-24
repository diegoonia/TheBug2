package personaje;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;

import alianza.Alianza;
import output.Sprite;

public abstract class Personaje extends Observable implements Atacable, IamMovil {
	protected String nombre;
	protected int salud=100;
	protected int energia=50;
	protected int fuerza=50;
	protected int ingenio=10;
	protected int defensa=5;
	protected int multavsalud=1;
	protected int modavsalud=0;
	protected float multavenergia=1;
	protected int modavenergia=0;
	protected float multavingenio=1;
	protected int modavingenio=0;
	protected float multavfuerza=1;
	protected int modavfuerza=0;
	protected float multavdefensa=1;
	protected int modavdefensa=0;
	protected Alianza alianzaAct;
	public Calendar limiteMinimoPermanenciaAlianza;
	protected boolean sexo; //= mucho
	protected int nivel = 1;
	protected int experiencia = 10;
	protected String SpritePath;
	protected Point pos;
	protected Point vel;
	protected Point acc;
	private int x, y;
	
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

	public final boolean atacar(Atacable atacado) {
		if (puedeAtacar() && atacado.estaVivo()) {
			atacado.serAtacado(this.obtenerPuntosDeFuerza());
			energia -= this.obtenerPuntosDeFuerza(); // Te cansas despues de atacar
			if(!atacado.estaVivo())
				despuesDeAtacar(atacado.darExperiencia());
			return true;
		}
		return false;
	}

	// Despues de atacar me fijo si subio de nivel con la experiencia obtenida
	// si paso de nivel, la experiencia se me resetea a 0, y me tiene que sumar
	// el resto de experiencia que sobro.
	// sino solamente me suma la experiencia obtenida.
	
	protected void despuesDeAtacar(int experienciaObtenida) {
		if( this.experiencia + experienciaObtenida >= this.getTopeExperienciaNivel()){
			this.experiencia = this.experiencia + experienciaObtenida - getTopeExperienciaNivel();
			subirDeNivel();
		}
		else{
			this.experiencia += experienciaObtenida;
		}
	}
	
	protected void subirDeNivel(){
		if(this.nivel < 32){
			this.nivel++;
		}
	}
	
	public void revivir() {
		this.serCurado();
		this.serEnergizado();
	}
	
	public abstract boolean puedeAtacar();
	
	public boolean estaVivo() {
		return this.salud > 0;
	}
	
	@Override
	public void serAtacado(int daño) {
		if ( ( daño - (this.obtenerPuntosDeDefensa()) /10)  > 0)
			this.salud -= (daño - (this.obtenerPuntosDeDefensa()) /10 );
	}


	public void serCurado() {
		this.salud = this.getmaxsalud(); //por default pongo el maximo
	}
	
	public void serCurado(int cant) {
		this.salud += cant;
	}

	public void serEnergizado() {
		this.energia = this.getmaxenergia(); // por default pongo el maximo
	}
	public void serEnergizado(int cant) {
		this.energia +=cant;
	}
	public String getNombre(){
		return this.nombre;
	}
	public int getSalud() {
		return this.salud;
	}
	
	public int getEnergia(){
		return this.energia;
	}
	
	
	public void romperAlianzaAct(){
		this.alianzaAct.obtenerIntegrantes().remove(this);
		this.alianzaAct = null;
	}
	
	
	public void setAlianzaAct(Alianza alianzaAct) {
		this.alianzaAct = alianzaAct;
	}

	public Alianza obtenerAlianzaAct() {
		return alianzaAct;
	}
	

	
	public boolean crearAlianza(Personaje p) {
		/*
		 * Si this no tiene alianza, la creo y me agrego
		 */
		if (this.alianzaAct == null) {
			Alianza alianza = new Alianza();
			this.alianzaAct = alianza;
			alianza.añadirPerAAlianza(this);
		}
		
		/*
		 * Si p no tiene alianza, lo agrego a mi alianza
		 * */
		
		if (p.alianzaAct == null) {
			
			p.alianzaAct = this.alianzaAct;
			alianzaAct.añadirPerAAlianza(p);
			
			return true;
		} else
			// EL PERSONAJE YA ESTA EN UNA ALIANZA, NO SE PUEDE VOLVER A ALIAR
		return false;
	}
		
	public Calendar getLimiteMinimoPermanenciaAlianza() {
		return limiteMinimoPermanenciaAlianza;
	}

	public void setLimiteMinimoPermanenciaAlianza(Calendar limiteMinimoPermanenciaAlianza) {
		this.limiteMinimoPermanenciaAlianza = limiteMinimoPermanenciaAlianza;
	}
	
	
	
	//Las Siguientes funciones calculan el maximo de los attributos, no los valores actuales. 
	///////////////////////
	public int getmaxsalud(){
		return (int)(100 * this.nivel);
	}
	///////////////////////
	public int getmaxenergia(){
		return (int)(100 * this.nivel + 10);
	}
	/////////////////////////
	public int obtenerPuntosDeFuerza() {
		return (int)(this.fuerza * this.multavfuerza + this.modavfuerza); // Modificadores + valor inherente
	}
	/////////////////////////////
	public int obtenerPuntosDeSalud() {
		return (int)(getmaxsalud() * this.multavsalud + this.modavsalud); // Modificadores + valor inherente
	}
	///////////////////////
	public int obtenerPuntosDeEnergia() {
		return (int)(getmaxenergia() * this.multavenergia + this.modavenergia); // Modificadores + valor inherente
	}
	///////////////
	public int obtenerPuntosDeIngenio() {
		return (int)(this.ingenio * this.multavingenio + this.modavingenio); // Modificadores + valor inherente
	}
	///////////////
	public int obtenerPuntosDeDefensa(){
		return (int)(this.defensa * this.multavdefensa + this.modavdefensa); // Modificadores + valor inherente
	}

	@Override
	public int darExperiencia() {
		return  getExperiencia(); //Es igual al anterior em el caso default, y es compatible con otras formulas de experiencia.
	}

	public int getTopeExperienciaNivel() {
		return this.nivel * 100;
	}


	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getNivel() {
		return nivel;
	}
	
	public String getSpritePath(){
		return this.SpritePath;
	}
	public Sprite getSprite(){
		return new Sprite(SpritePath);
	}
	public void setSpritePath(String path){
		this.SpritePath=path;
	}
	
	public Point getPos() {return this.pos;}
	public Point getVel() {return this.vel;}
	public Point getAcc() {return this.acc;}
	public Point getNextStep(){return this.vel;} // Why? For the glory of Satan of course!
	public void mover(){
		this.pos=this.vel;
		this.vel.setLocation(vel.getX()+acc.getX(), vel.getY()+acc.getY());
		}
	public Point procMovimiento(){ mover(); return this.pos;}
	private void actualizar(){
		if (this.hasChanged())
			this.notifyObservers();
		this.clearChanged();
		
		
	}
	public void step(){
		mover();
//		actuar()
		actualizar();
	}
	
}