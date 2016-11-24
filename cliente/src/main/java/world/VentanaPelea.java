package world;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import world.entities.creatures.*;

public class VentanaPelea extends JFrame {
	private static final long serialVersionUID = 1L;

	public VentanaPelea() {
		getContentPane().setLayout(null);
	}

	protected static final world.entities.creatures.Player Player = null;
	private JPanel contentPane;

	public VentanaPelea(Player player, Player player2, Game game) { //recibo los dos jugadores que van a pelear
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JButton btnHuir = new JButton("Huir");
		btnHuir.setForeground(Color.WHITE);
		btnHuir.setBackground(Color.BLUE);
		btnHuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				game.setEstaPeleando(false);
			}
		});
		btnHuir.setBounds(333, 237, 101, 23);
		contentPane.add(btnHuir);
		
		JButton btnAtacar = new JButton("Atacar");
		btnAtacar.setForeground(Color.WHITE);
		btnAtacar.setBackground(Color.BLUE);
		btnAtacar.setBounds(211, 237, 94, 23);
		contentPane.add(btnAtacar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("res/textures/fondo_pelea.png"));
		lblNewLabel.setBounds(0, 0, 444, 271);
		contentPane.add(lblNewLabel);
		
	}
	
	public VentanaPelea(Player player, GameNpc enemigo, Game game) { //recibo los dos jugadores que van a pelear
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JButton btnHuir = new JButton("Huir");
		btnHuir.setForeground(Color.WHITE);
		btnHuir.setBackground(Color.BLUE);
		btnHuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				game.setEstaPeleando(false);
			}
		});
		btnHuir.setBounds(333, 237, 101, 23);
		contentPane.add(btnHuir);
		
		JButton btnAtacar = new JButton("Atacar");
		btnAtacar.setForeground(Color.WHITE);
		btnAtacar.setBackground(Color.BLUE);
		btnAtacar.setBounds(211, 237, 94, 23);
		contentPane.add(btnAtacar);
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player.getPersonaje().atacar( enemigo.getNpc() );
				if( !enemigo.getNpc().estaVivo() ){
					dispose();
				}
			}
		});
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("res/textures/fondo_pelea.png"));
		lblNewLabel.setBounds(0, 0, 444, 271);
		contentPane.add(lblNewLabel);
		
	}
}
