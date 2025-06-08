package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;


public class HomeGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeGUI frame = new HomeGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Timer timeLog = new Timer();
		
		  TimerTask task = new TimerTask() {
	            @Override
	            public void run() {
	                JLabel lblLog = new JLabel();
	                
	                try {
	             
	                	ProcessBuilder pbLoggerC = new ProcessBuilder("/Users/gabriele/Documents/LogDiSistmaC"); //processBuilder che lancera l'app in modalità silenziosa
		                
	                	pbLoggerC.redirectErrorStream(true);
		                
		                Process process = pbLoggerC.start();
	                	try(
		                BufferedReader readerLogger = new BufferedReader(
		                		new InputStreamReader(process.getInputStream()))) {
		                	
	                		String Ritorno;
                			
                			while((Ritorno = readerLogger.readLine()) != null) {
                				 System.out.println("C dice: " + Ritorno);
                				lblLog.setText(Ritorno);
                				
                				//aggiungo al panel la label con ciò che ritornerà dal logger in C
                				
                			}
                
	                		
		                }catch(Exception ex) {
		                	
		                	 System.out.println("Errore" + ex.getMessage());
		                }
		                			
		                			
	                }catch(Exception e) {
	                	
	                    System.out.println("Errore" + e.getMessage());
		                
	                }
	           
	            }
	        };
	        
	        long delayInMilliseconds = 0; // Delay iniziale prima della prima esecuzione (0 per iniziare subito)
	        long periodInMilliseconds = 5 * 1000; // Intervallo tra le esecuzioni (N secondi * 1000 millisecondi)
	        
	        timeLog.schedule(task, delayInMilliseconds, periodInMilliseconds);
	}
	
	
	
	

	/**
	 * Create the frame.
	 */
	public HomeGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(179, 57, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("Monitoraggio Log di sistema");
		lblTitle.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		lblTitle.setForeground(new Color(237, 243, 255));
		
		JScrollPane scrollPaneLog = new JScrollPane();

		
		//impostazione del layout:
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(0)
							.addComponent(scrollPaneLog, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneLog, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	
	
}
