package personaje;

import java.awt.Point;

public interface IamMovil {
	
	public Point getPos();
	public Point getVel();
	public Point getAcc();
	public Point getNextStep();
	public void mover();
	public Point procMovimiento();

}
