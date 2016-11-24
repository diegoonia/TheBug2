package jugador;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.codehaus.jackson.map.ObjectMapper;

import comunicacion.ClientThread;
import comunicacion.User;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class SeleccionPersonaje extends JFrame {

	private static final long serialVersionUID = -2726214439028001680L;
	private JPanel contentPane;

	
	/*public static void main(String[] args) {
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
	
	/**
	 * Create the frame.
	 */
	public SeleccionPersonaje(final Socket socket,final User user) {
		setTitle("Seleccion de Personaje");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblRaza = new JLabel("Raza:");
		lblRaza.setFont(new Font("Arial", Font.PLAIN, 13));
		lblRaza.setBounds(130, 126, 76, 14);
		contentPane.add(lblRaza);
		
		JComboBox comboRaza = new JComboBox();
		comboRaza.setFont(new Font("Arial", Font.PLAIN, 12));
		comboRaza.setForeground(new Color(255, 255, 255));
		comboRaza.setBackground(new Color(148, 0, 211));
		comboRaza.setModel(new DefaultComboBoxModel(new String[] {"Humano", "Holograma", "Bot"}));
		comboRaza.setBounds(181, 123, 129, 20);
		contentPane.add(comboRaza);
		
		JLabel lblCasta = new JLabel("Casta:");
		lblCasta.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCasta.setBounds(130, 162, 61, 14);
		contentPane.add(lblCasta);
		
		JComboBox comboCasta = new JComboBox();
		comboCasta.setBackground(new Color(148, 0, 211));
		comboCasta.setForeground(new Color(255, 255, 255));
		comboCasta.setFont(new Font("Arial", Font.PLAIN, 12));
		comboCasta.setModel(new DefaultComboBoxModel(new String[] {"Programador", "Tester", "Soporte"}));
		comboCasta.setBounds(181, 159, 129, 20);
		contentPane.add(comboCasta);
		
		JLabel lblMapa = new JLabel("Mapa:");
		lblMapa.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMapa.setBounds(130, 198, 46, 14);
		contentPane.add(lblMapa);
		
		final JComboBox comboMapa = new JComboBox();
		comboMapa.setBackground(new Color(148, 0, 211));
		comboMapa.setForeground(new Color(0, 255, 255));
		comboMapa.setFont(new Font("Arial", Font.PLAIN, 12));
		comboMapa.setModel(new DefaultComboBoxModel(new String[] {"Fisica", "Enlace de Datos", "Red(SOON)","Transporte(SOON)","Sesion(SOON)","Presentacion(SOON)","Aplicacion(SOON))"}));
		comboMapa.setBounds(181, 192, 129, 20);
		contentPane.add(comboMapa);
		
		JButton btnMapa = new JButton("Jugar");
		btnMapa.setForeground(new Color(255, 255, 255));
		btnMapa.setBackground(new Color(148, 0, 211));
		btnMapa.setFont(new Font("Arial", Font.PLAIN, 11));
		btnMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ObjectMapper mapper = new ObjectMapper();
				User user2 = new User(user.getPass(),user.getNombre(),null,null,null,0);
				
				String mapaSeleccionado = (String) comboMapa.getSelectedItem();
				switch (mapaSeleccionado) {
				case "Fisica":
					user2.setMundoSelec(1);
					break;
				case "Enlace de Datos":
					user2.setMundoSelec(2);
				default:
					//Poner mensaje de error
					break;
				}
	            	
				user2.setAccion("entrarAMundo");
		        String jsonInString;

				try {
					//Le envio al servidor la informacion del mundo al que quiere entrar el usuario
		            
					jsonInString = mapper.writeValueAsString(user2);
					PrintWriter out = new PrintWriter(socket.getOutputStream()); //OBTENGO EL CANAL DE SALIDA DEL SOCKET HACIA EL SERVIDOR
					out.println(jsonInString);
					out.flush();
					ClientThread newClient = new ClientThread(socket,mapaSeleccionado);
		            Thread thread = new Thread(newClient);
		            thread.start();
		            System.out.println("Se ejecuto la acción: "+ user2.getAccion());
		            dispose();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
					
					

	            
				
			}
		});
		btnMapa.setBounds(97, 251, 121, 23);
		contentPane.add(btnMapa);
		
		JButton btnCerrar = new JButton("Salir"); //Cierro el socket cuando cierro ventana de Seleccion de Personaje
		btnCerrar.setForeground(new Color(255, 255, 255));
		btnCerrar.setBackground(new Color(148, 0, 211));
		btnCerrar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
								
	            try {
	            	 ObjectMapper mapper = new ObjectMapper();
	        		 String jsonInString;
	        		 User user2 = new User(null,null,"oprimioCerrar",null,null,0);
	        		 jsonInString = mapper.writeValueAsString(user2);
	        		 PrintWriter out = new PrintWriter(socket.getOutputStream()); //OBTENGO EL CANAL DE SALIDA DEL SOCKET HACIA EL SERVIDOR
	        		 out.println(jsonInString);
	        		 out.flush();
					socket.close();
					System.exit(0);
					//dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnCerrar.setBounds(246, 251, 115, 23);
		contentPane.add(btnCerrar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SeleccionPersonaje.class.getResource("/imagenes/Titulo_opt4.png")));
		lblNewLabel.setBounds(162, 22, 464, 60);
		contentPane.add(lblNewLabel);

		
		
	}

	@SuppressWarnings("unused")
	private void desconectar(Socket socket) throws IOException {
		 socket.close();
		 ObjectMapper mapper = new ObjectMapper();
		 String jsonInString;
		 User user2 = new User(null,null,"oprimioCerrar",null,null,0);
		 jsonInString = mapper.writeValueAsString(user2);
		 PrintWriter out = new PrintWriter(socket.getOutputStream()); //OBTENGO EL CANAL DE SALIDA DEL SOCKET HACIA EL SERVIDOR
		 out.println(jsonInString);
		 out.flush();
	     System.exit(0);
		
	}
}
