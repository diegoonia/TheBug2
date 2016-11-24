package jugador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;



public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6087367122783786127L;
	private JPanel contentPane;
	private JTextField campo_usuario;
	private JPasswordField campo_contra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usuario = new JLabel("Usuario:");
		usuario.setBounds(55, 71, 54, 14);
		contentPane.add(usuario);
		
		JLabel contrasenia = new JLabel("Contrase\u00F1a:");
		contrasenia.setBounds(55, 130, 138, 14);
		contentPane.add(contrasenia);
		
		campo_usuario = new JTextField();
		campo_usuario.setBounds(144, 68, 130, 20);
		contentPane.add(campo_usuario);
		campo_usuario.setColumns(10);
		
		campo_contra = new JPasswordField();
		campo_contra.setBounds(144, 127, 130, 20);
		contentPane.add(campo_contra);
		
		JButton botonlogin = new JButton("Entrar");
		botonlogin.addMouseListener(new MouseAdapter() {
		});
		botonlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuario="admin";
				String contrasenia="1234";
				String password = new String(campo_contra.getPassword()); //para obtener el valor cuando ingrese password
				
				if(campo_usuario.getText().equals(usuario) && password.equalsIgnoreCase(contrasenia)){
					SeleccionPersonaje sp = new SeleccionPersonaje();
					sp.setVisible(true);
					dispose();
				}else{ //cuando ingreso mal el usuario o la contraseña aparece msj de eror
					
					
					JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña no validos");
				}
				
				
			}

	
		});
		botonlogin.setBounds(185, 200, 89, 23);
		contentPane.add(botonlogin);
	}
}
