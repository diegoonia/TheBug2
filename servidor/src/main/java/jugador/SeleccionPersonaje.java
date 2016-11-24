package jugador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeleccionPersonaje extends JFrame {
	private static final long serialVersionUID = -2726214439028001680L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionPersonaje frame = new SeleccionPersonaje();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SeleccionPersonaje() {
		setTitle("Seleccion de Personaje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRaza = new JLabel("Raza:");
		lblRaza.setBounds(10, 29, 76, 14);
		contentPane.add(lblRaza);
		
		JComboBox comboRaza = new JComboBox();
		comboRaza.setModel(new DefaultComboBoxModel(new String[] {"Humano", "Holograma", "Bot"}));
		comboRaza.setBounds(58, 26, 104, 20);
		contentPane.add(comboRaza);
		
		JLabel lblCasta = new JLabel("Casta:");
		lblCasta.setBounds(10, 77, 61, 14);
		contentPane.add(lblCasta);
		
		JComboBox comboCasta = new JComboBox();
		comboCasta.setModel(new DefaultComboBoxModel(new String[] {"Programador", "Tester", "Soporte"}));
		comboCasta.setBounds(58, 74, 104, 20);
		contentPane.add(comboCasta);
		
		JButton btnMapa = new JButton("Jugar");
		btnMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mapa mapa = new Mapa();
				mapa.setVisible(true);
				dispose();
			}
		});
		btnMapa.setBounds(143, 200, 151, 23);
		contentPane.add(btnMapa);
		
		JLabel lblMapa = new JLabel("Mapa:");
		lblMapa.setBounds(10, 125, 46, 14);
		contentPane.add(lblMapa);
		
		JComboBox comboMapa = new JComboBox();
		comboMapa.setBounds(58, 122, 104, 20);
		contentPane.add(comboMapa);
	}
}
