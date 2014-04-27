package pe.edu.pucp.algorithms.sorting;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InitialLauncher extends JFrame {

	private JPanel MainContainer;
	private JTextField txtElements;

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
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 590, 25);
		MainContainer.add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem New = new JMenuItem("Nuevo");
		mnArchivo.add(New);
		
		JMenuItem Exit = new JMenuItem("Salir");
		mnArchivo.add(Exit);
		
		JLabel lblElements = new JLabel("Elementos en el Arreglo");
		lblElements.setBounds(43, 164, 246, 48);
		MainContainer.add(lblElements);
		
		txtElements = new JTextField();
		txtElements.setBounds(232, 212, 236, 48);
		MainContainer.add(txtElements);
		txtElements.setColumns(10);
		
		JLabel tittle = new JLabel("Bienvenido a Sorting Algoritms");
		tittle.setBounds(83, 36, 402, 63);
		MainContainer.add(tittle);
	}
}
