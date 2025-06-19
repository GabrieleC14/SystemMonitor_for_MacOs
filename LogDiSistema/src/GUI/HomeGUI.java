package GUI;

import java.awt.EventQueue;


import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import java.util.Timer;
import java.util.TimerTask;
import java.io.*;


public class HomeGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	  static JScrollPane scrollPaneLog = new JScrollPane(); 
	  private JPanel panelLog;
	/**
	 * Launch the application.
	 */
	  public static void main(String[] args) {
		    EventQueue.invokeLater(() -> {
		        try {
		            HomeGUI frame = new HomeGUI();
		            frame.setVisible(true);
		            
		            String os = System.getProperty("os.name");
		            
		            Timer timeLog = new Timer();
		            TimerTask task = new TimerTask() {
		                @Override
		                public void run() {
		                	
		                	if(os.contains("Mac OS")) {
		                		
		                		try {
		                			
		                			 ProcessBuilder pbLoggerC = new ProcessBuilder("/Users/gabriele/Documents/LogDiSistmaC/logger");  //solo per i test 
		                			 
		                			/*File jarFile = new File(HomeGUI.class
		                                    .getProtectionDomain()
		                                    .getCodeSource()
		                                    .getLocation()
		                                    .toURI());

		                            File baseDir = jarFile.getParentFile();
		                            
		                            System.out.println(baseDir);
		                            
		                            File loggerExecutable = new File(baseDir, "logger"); // O "logger.exe" su Windows
    
		                            ProcessBuilder pbLoggerC = new ProcessBuilder(loggerExecutable.getAbsolutePath());

		                            pbLoggerC.directory(baseDir);
		                            
			                        //ProcessBuilder pbLoggerC = new ProcessBuilder("");
			                        pbLoggerC.redirectErrorStream(true);
			                        */ 
		                			Process process = pbLoggerC.start();

			                        try (BufferedReader readerLogger = new BufferedReader(
			                                new InputStreamReader(process.getInputStream()))) {

			                            String line;
			                            while ((line = readerLogger.readLine()) != null) {
			                                String logLine = line;
			                                System.out.println("!!!");
			                                SwingUtilities.invokeLater(() -> {
			                                    JLabel lbl = new JLabel(logLine);
			                                    frame.panelLog.add(lbl);
			                                    frame.panelLog.revalidate();
			                                    frame.panelLog.repaint();
			                                });
			                            }

			                        }
			                    } catch (Exception e) {
			                        System.err.println("Errore: " + e.getMessage());
			                    }
		                		
		                	}else if(os.contains("Windows")) { //da fare su windows 
		                		
		                		try {
			                        ProcessBuilder pbLoggerC = new ProcessBuilder("/Users/gabriele/Documents/LogDiSistmaC/logger");
			                        //ProcessBuilder pbLoggerC = new ProcessBuilder("");
			                        pbLoggerC.redirectErrorStream(true);
			                        Process process = pbLoggerC.start();

			                        try (BufferedReader readerLogger = new BufferedReader(
			                                new InputStreamReader(process.getInputStream()))) {

			                            String line;
			                            while ((line = readerLogger.readLine()) != null) {
			                                String logLine = line;
			                                System.out.println("!!!");
			                                SwingUtilities.invokeLater(() -> {
			                                    JLabel lbl = new JLabel(logLine);
			                                    frame.panelLog.add(lbl);
			                                    frame.panelLog.revalidate();
			                                    frame.panelLog.repaint();
			                                });
			                            }

			                        }
			                    } catch (Exception e) {
			                        System.err.println("Errore: " + e.getMessage());
			                    }
		                		
		                	}
		                	
		                    
		                }
		            };

		            long delay = 0;
		            long period = 5 * 1000;
		            timeLog.schedule(task, delay, period);

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    });
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

		    panelLog = new JPanel();
		    panelLog.setLayout(new BoxLayout(panelLog, BoxLayout.Y_AXIS));

		    scrollPaneLog = new JScrollPane(panelLog);
		    scrollPaneLog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		    GroupLayout gl_contentPane = new GroupLayout(contentPane);
		    gl_contentPane.setHorizontalGroup(
		        gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
		            .addGroup(gl_contentPane.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    .addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
		                    .addComponent(scrollPaneLog, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
		                .addContainerGap())
		    );
		    gl_contentPane.setVerticalGroup(
		        gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
		            .addGroup(gl_contentPane.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(scrollPaneLog, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(17, Short.MAX_VALUE))
		    );
		    contentPane.setLayout(gl_contentPane);
		}

	
	
	
	
}
