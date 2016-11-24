package jugador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.MapaAlianza;
import output.Layer;
import output.Output;

public class MapaEnlaceDatos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -751031079532977799L;
	private JPanel contentPane;
	Point coord;
	MapaAlianza al1;
	Output out;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapaEnlaceDatos frame = new MapaEnlaceDatos();
					frame.setVisible(true);
					while (true)
						frame.step();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MapaEnlaceDatos() {
		setTitle("Mapa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		al1 = new MapaAlianza("Humanos");
		al1.addMiembro("ElTerrible");
		
		
		
		contentPane = new JPanel();
		out = new Output("src/main/resources/imagenes/mapa1.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(out);
		contentPane.repaint();
		out.addLayer(al1);
		
		contentPane.addMouseListener(new MouseAdapter() {
			public void actionPerformed(MouseEvent arg0) {
				coord = arg0.getPoint();
			}
		});
		}
	
	public void step(){
		out.repaint();
		al1.procesar();
	}
	
	
	
	

}
