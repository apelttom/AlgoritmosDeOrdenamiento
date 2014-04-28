package pe.edu.pucp.algorithms.sorting;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class InitialLauncher extends JFrame implements ActionListener {

	private JPanel MainContainer;
	private JTextField txtElements;
	private JButton btnContinuar;
	private JMenuItem New, Exit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialLauncher frame = new InitialLauncher();
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
	public InitialLauncher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 394);
		MainContainer = new JPanel();
		MainContainer.setBackground(Color.LIGHT_GRAY);
		MainContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainContainer);
		MainContainer.setLayout(null);
		
		createMenuBar();

		JLabel lblElements = new JLabel("Elementos en el Arreglo");
		lblElements.setBounds(43, 164, 246, 48);
		MainContainer.add(lblElements);
		
		txtElements = new JTextField();
		txtElements.setBounds(234, 181, 236, 48);
		MainContainer.add(txtElements);
		txtElements.setColumns(10);
		
		JLabel tittle = new JLabel("Bienvenido a Sorting Algoritms");
		tittle.setBounds(83, 36, 402, 63);
		MainContainer.add(tittle);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(285, 277, 165, 48);
		btnContinuar.addActionListener(this);
		MainContainer.add(btnContinuar);
	}
	
	private void createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 590, 25);
		MainContainer.add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		New = new JMenuItem("Nuevo");
		mnArchivo.add(New);
		
		Exit = new JMenuItem("Salir");
		mnArchivo.add(Exit);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnContinuar){
			System.out.println("next frame");
		}
		
	}
}
