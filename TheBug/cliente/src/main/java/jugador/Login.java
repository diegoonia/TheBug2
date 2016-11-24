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
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Login extends JFrame {
	private static final long serialVersionUID = -6087367122783786127L;
	
	private JPanel contentPane;
	private JTextField campo_usuario;
	private JPasswordField campo_contra;
	Socket socket;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setForeground(Color.WHITE);
		setBackground(Color.GREEN);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		
		JLabel usuario = new JLabel("Usuario:");
		usuario.setFont(new Font("Arial", Font.PLAIN, 13));
		usuario.setBounds(118, 148, 98, 14);
		contentPane.add(usuario);
		
		JLabel contrasenia = new JLabel("Contrase\u00F1a:");
		contrasenia.setFont(new Font("Arial", Font.PLAIN, 13));
		contrasenia.setBounds(118, 196, 138, 14);
		contentPane.add(contrasenia);
		
		campo_usuario = new JTextField();
		campo_usuario.setBounds(200, 146, 151, 20);
		contentPane.add(campo_usuario);
		campo_usuario.setColumns(10);
		
		campo_contra = new JPasswordField();
		campo_contra.setBounds(200, 194, 151, 20);
		contentPane.add(campo_contra);
		
		JButton botonlogin = new JButton("Entrar");
		botonlogin.setForeground(Color.WHITE);
		botonlogin.setBackground(new Color(148, 0, 211));
		botonlogin.setFont(new Font("Arial", Font.PLAIN, 11));
	
		botonlogin.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent arg0) {
				
				
				String password = new String(campo_contra.getPassword());

				
				try {
					final int PORT = 4445;
					String server = "127.0.0.1";
		            socket = new Socket(server, PORT);
		            System.out.println("Te conectaste a: " + server);
					
			        ObjectMapper mapper = new ObjectMapper();
					@SuppressWarnings("unused")
					Scanner sc = new Scanner(System.in);
					//Scanner input = new Scanner(socket.getInputStream());

		            User user = new User(password, campo_usuario.getText(),"login",null,null,0);
		            String jsonInString = mapper.writeValueAsString(user);
		            PrintWriter out = new PrintWriter(socket.getOutputStream()); //OBTENGO EL CANAL DE SALIDA DEL SOCKET HACIA EL SERVIDOR
		            out.println(jsonInString); 
		            out.flush();
		            

		           // ClientThread newClient = new ClientThread(socket);
		           // Thread thread = new Thread(newClient);
		           // System.out.println("Creando thread");
		           // thread.start();

		            
		            //Leo la informacion que vuelve del servidor
		            Scanner input;
		            do{ 
		            	input = new Scanner(socket.getInputStream());
		            	
		            }while (input.hasNext()==false);
		            
					String in = input.nextLine();
					user = mapper.readValue(in, User.class);
					System.out.println("La re accion: "+user.getAccion());
					
		            if(user.getAccion().compareTo("abrirSeleccionMundo")==0){
		            	System.out.println("Aca tengo que abrir la seleccion de mundos");
						SeleccionPersonaje sp = new SeleccionPersonaje(socket,user);
						sp.setLocationRelativeTo(null);
						sp.setVisible(true);
						dispose();
						
					}else{	
						
						JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña no validos");
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

	
		});
		botonlogin.setBounds(200, 244, 105, 20);
		contentPane.add(botonlogin);
		@SuppressWarnings("unused")
		JButton roundButton = new JButton("Redondo");
		
		JButton btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setBackground(new Color(148, 0, 211));
		btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registro reg = new Registro();
				reg.setVisible(true);
				reg.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnRegistrar.setBounds(41, 244, 124, 20);
		contentPane.add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Salir");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new Color(148, 0, 211));
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnCancelar.setBounds(332, 244, 105, 21);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/Titulo_opt2.png")));
		lblNewLabel.setBounds(129, 13, 484, 124);
		contentPane.add(lblNewLabel);

	}
}
