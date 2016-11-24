package jugador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.codehaus.jackson.map.ObjectMapper;

import comunicacion.ClientThread;
import comunicacion.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1453514283810873060L;
	private JPanel contentPane;
	private JTextField campoUsu;
	private JPasswordField campoContra;
	private JTextField campoMail;
	Socket socket;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Registro() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 484, 371);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("Nombre Usuario:");
		lblNombreUsuario.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNombreUsuario.setBounds(28, 101, 128, 14);
		panel.add(lblNombreUsuario);
		
		campoUsu = new JTextField();
		campoUsu.setBounds(116, 98, 170, 20);
		panel.add(campoUsu);
		campoUsu.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Arial", Font.PLAIN, 11));
		lblContrasea.setBounds(28, 141, 97, 14);
		panel.add(lblContrasea);
		
		campoContra = new JPasswordField();
		campoContra.setBounds(116, 138, 170, 20);
		panel.add(campoContra);
		
		JLabel lblDireccinEmail = new JLabel("Direcci\u00F3n e-mail:");
		lblDireccinEmail.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDireccinEmail.setBounds(28, 180, 97, 14);
		panel.add(lblDireccinEmail);
		
		campoMail = new JTextField();
		campoMail.setBounds(116, 177, 170, 20);
		panel.add(campoMail);
		campoMail.setColumns(10);
		
		JButton btnReg = new JButton("Registrar");
		btnReg.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReg.setBackground(new Color(148, 0, 211));
		btnReg.setForeground(new Color(255, 255, 255));
				btnReg.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent arg0) {
				
				String password = new String(campoContra.getPassword());
				
				try {
					final int PORT = 4445;
					String server = "127.0.0.1";
		            socket = new Socket(server, PORT);
		            System.out.println("Te conectaste a: " + server);
					
			        ObjectMapper mapper = new ObjectMapper();
					@SuppressWarnings("unused")
					Scanner sc = new Scanner(System.in);
					//Scanner input = new Scanner(socket.getInputStream());

		            User user = new User(password, campoUsu.getText(),"registrar",null,null,0);
		            String jsonInString = mapper.writeValueAsString(user);
		            PrintWriter out = new PrintWriter(socket.getOutputStream()); //OBTENGO EL CANAL DE SALIDA DEL SOCKET HACIA EL SERVIDOR
		            out.println(jsonInString); 
		            out.flush();
		            
		           // ClientThread newClient = new ClientThread(socket);
		           // Thread thread = new Thread(newClient);
		            //System.out.println("Creando thread");
		           // thread.start();

		            //Leo la informacion que vuelve del servidor
		            Scanner input;
		            do{ 
		            	input = new Scanner(socket.getInputStream());
		            	
		            }while (input.hasNext()==false);
		            
					String in = input.nextLine();
					user = mapper.readValue(in, User.class);

					
		            if(user.getAccion().compareTo("abrirSeleccionMundo")==0){
		            	System.out.println("Aca tengo que abrir la seleccion de mundos");
						SeleccionPersonaje sp = new SeleccionPersonaje(socket,user);
						sp.setVisible(true);
						dispose();
						
					}else{	
						
						JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña no validos");
						//socket.close();
						dispose();
						try {
							Login frame = new Login();
							frame.setVisible(true);
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnReg.setBounds(153, 246, 89, 23);
		panel.add(btnReg);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBackground(new Color(148, 0, 211));
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				dispose();
			}
		});
		btnCancelar.setBounds(267, 246, 89, 23);
		panel.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/Titulo_opt4.png")));
		lblNewLabel.setBounds(153, 11, 464, 58);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/HumanoProgQuieto_4.png")));
		lblNewLabel_2.setBounds(385, 101, 61, 95);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/HumanoTesterQuieto_1.png")));
		lblNewLabel_1.setBounds(303, 101, 89, 95);
		panel.add(lblNewLabel_1);
	}
}
