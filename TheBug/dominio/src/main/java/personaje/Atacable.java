package personaje;


import output.IhasSprite;

public interface Atacable extends IhasSprite{ //Todo objeto que se pueda atacar tiene que poder ser visualizado


	
		void serAtacado(int da�o);
		
		int darExperiencia();

		public boolean estaVivo();

		void revivir();

		
		
}
