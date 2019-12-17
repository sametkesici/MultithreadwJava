package main.java;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class yazlab {

	public static JFrame frame;
	static AnaSunucu anaSunucu;
	Color customColor = new Color(10,10,255);
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	   
     
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					yazlab window = new yazlab();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public yazlab() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1272, 697);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	    UIManager.put("ProgressBar.selectionBackground", Color.decode("#000000"));
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setMaximum(10000);
		progressBar.setStringPainted(true);
		progressBar.setBounds(47, 73, 300, 30);
		frame.getContentPane().add(progressBar);
		progressBar.setBackground(Color.white);
		progressBar.setForeground(Color.decode("#B850FF"));

		
		JButton btnStart = new JButton(new ImageIcon("C:\\Users\\Samet\\Desktop\\2.png"));
		btnStart.setBorder(BorderFactory.createEmptyBorder());
		btnStart.setContentAreaFilled(false);
		btnStart.setBackground(Color.BLACK);

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anaSunucu = new AnaSunucu();
				anaSunucu.start();
				int grid = 255;
			
				Thread t1 = new Thread() {
					public void run() {
						
						while(true) {
							try {
								TimeUnit.MILLISECONDS.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
							progressBar.setValue(anaSunucu.AnaKapasite);
							for(int i = 0; i < anaSunucu.listeAd.size(); i++) {
								anaSunucu.barList.get(i).setValue(anaSunucu.listeAd.get(i).AltKapasite);
								frame.repaint();
							}
						}
					}
				};
				
				t1.start();
				
			}
		});
		btnStart.setBounds(150, 95, 90, 90);
		frame.getContentPane().add(btnStart);
		
		JLabel lblAnaSunucu = new JLabel("Alt Sunucu 1");
		lblAnaSunucu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAnaSunucu.setBounds(219, 202, 145, 47);
		//frame.getContentPane().add(lblAnaSunucu);
		
		JLabel label = new JLabel("Ana Sunucu :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(50, 13, 145, 60);
		frame.getContentPane().add(label);
		
		JLabel lblAltSunucu = new JLabel("Alt Sunucu 2");
		lblAltSunucu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAltSunucu.setBounds(219, 323, 145, 47);
		//frame.getContentPane().add(lblAltSunucu);
		
		JLabel lblYeniAlanSunucular = new JLabel("Alt Sunucular :");
		lblYeniAlanSunucular.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYeniAlanSunucular.setBounds(50, 170 , 203, 32);
		frame.getContentPane().add(lblYeniAlanSunucular);
		
		
		
		
	}
}
