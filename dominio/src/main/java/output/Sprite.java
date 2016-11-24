package output;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.omg.CORBA.Bounds;

//Quiza te preguntes porque una clase sprite que es un Label con otro nombre... 
//Sinceramente esperaba escribir mas. Lo dejo porque el constructor quedo cheto.

public class Sprite extends JLabel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7857480321636215710L;

	public Sprite(String path) {
		super(new ImageIcon(path));
		}
	
	public Sprite() {
		super();
		super.setIcon(new ImageIcon("bin/tiletest.png"));	
	}
	
	public void setpos(int x, int y){
		
		this.setBounds(x, y, 32, 32);
		super.setVisible(true);
	}
	

	
	public void paintComponent(Graphics g){
		draw((Graphics2D)g, this.getWidth(),this.getHeight());
		
	}

	private void draw(Graphics2D g2, int width, int height) {
		
		g2.setColor(Color.black);
		super.repaint();
	}
	
	public Rectangle getRec(){
		
		return new Rectangle(this.getX(),this.getY(),this.getIcon().getIconWidth(),this.getIcon().getIconHeight());
	}
	public void printSprite(Component c, Graphics2D g){
		this.getIcon().paintIcon(c, g, this.getX(), this.getY());
	}
//	public void printSprite(Component c, Graphics2D g, int idx)
//	{
//		this.get
//		this.getIcon().paintIcon(c, g, this.getX(), this.getY());
////		g.drawString(this.getBounds().toString(), 50, 50);
//	}

}
