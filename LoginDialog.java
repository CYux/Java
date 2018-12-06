package bh.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import databaseOfMovies.MovieFrame;
import databaseOfMovies.UserFrame;
import user.Manager;

public class LoginDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private Manager user = new Manager();  
	
	private class ExitAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	

	class LoginAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(username.getText().equals(user.getUsername())&&password.getText().equals(user.getPassword()))
			{ 
				LoginDialog.this.setVisible(false);
		    	MovieFrame mf = new MovieFrame();
		        mf.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Please corret your password.");
			}
				
		}
	}
	class ClientAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LoginDialog.this.setVisible(false);
	    	UserFrame uf = new UserFrame();
	        uf.setVisible(true);
		}
		
	}
	
	public LoginDialog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Login");	
		
		JLabel lblNewLabel = new JLabel("Username");

		lblNewLabel.setForeground(Color.gray);
		lblNewLabel.setFont(new Font("Default",Font.BOLD, 15));		
		lblNewLabel.setBounds(200, 161, 79, 43);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(350, 159, 170, 43);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel label = new JLabel("Password");
		label.setBounds(200, 227, 79, 40);
		label.setForeground(Color.gray);
		label.setFont(new Font("Default",Font.BOLD, 15));
		contentPane.add(label);
		
		password = new JPasswordField();
		password.setBounds(350, 225, 170, 43);
		contentPane.add(password);
		
		JButton denglu = new JButton("Login");
		ActionListener listener = new LoginAction( );
		denglu.addActionListener(listener);
		denglu.setBounds(200, 343, 118, 46);
		contentPane.add(denglu);
		
		JButton tuichu = new JButton("Exit");
		tuichu.setBounds(350, 343, 118, 46);
		contentPane.add(tuichu);
		tuichu.addActionListener(new ExitAction());
		
		JButton yonghu = new JButton("Client");
		yonghu.setBounds(500, 343, 118, 46);
		contentPane.add(yonghu);
		yonghu.addActionListener(new ClientAction());

		
		JLabel back1 = new JLabel();
		back1.setIcon(new ImageIcon(getClass().getResource("/picture/"+"login.jpg")));
		back1.setBounds(0, 0, 757, 426);
		contentPane.add(back1);
		this.setVisible(true);
		this.setResizable(false);
		
	}
    public static void main(String[] args) {
    		LoginDialog ld=new LoginDialog();
    }
}