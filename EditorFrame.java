import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class EditorFrame extends JFrame {


	private JMenu fileMenu;
	private JMenuItem open;
	private JMenuItem close;
	private JMenuItem save;
	private JMenuBar menuBar;
	private JFileChooser choose;
	private JTextArea text;
	private BufferedReader br;

	public EditorFrame(){

		super("Hinga Text Editor");

		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		
		open = new JMenuItem("Open");
		open.setMnemonic('o');
		fileMenu.add(open);
		openFile of = new openFile();
		open.addActionListener(of);
	
		save = new JMenuItem("Save");
		save.setMnemonic('S');
		fileMenu.add(save);
		SaveFile sf = new SaveFile();
		save.addActionListener(sf);

		close = new JMenuItem("Close");
		close.setMnemonic('c');
		fileMenu.add(close);
		close.addActionListener(
			
			new ActionListener(){

				public void actionPerformed(ActionEvent ae){
					
					System.exit(0);
			
				}
			}
		);

		JMenu aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic('a');

		JMenuItem about = new JMenu("My Story");
		about.setMnemonic('m');
		about.addActionListener(

			new ActionListener(){

				public void actionPerformed(ActionEvent ae){
					
					JOptionPane.showMessageDialog(null,"The program was developed by Hinga Labs");
				}
			}
		);
		aboutMenu.add(about);

		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
		add(menuBar, BorderLayout.NORTH);

		//The textarea where the text will be edited

		JPanel panel = new JPanel();
		text = new JTextArea();
		text.setLineWrap(true);
		

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(panel);
		add(panel);		
		panel.add(text);
		
	
		//text.add(scroll);
	}
	
	private class openFile implements ActionListener {

		public void actionPerformed(ActionEvent ae){
		
			JFileChooser choose = new JFileChooser();
			//PrintWriter printer = new PrintWriter(text);

			choose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES ); 
		
			int result = choose.showOpenDialog(null);

			if(result == JFileChooser.CANCEL_OPTION){	
					
				 System.exit(1);	
			}else{
		
				File name = choose.getSelectedFile();

				try {
					br = new BufferedReader(new FileReader(name));
					String line;
					
					while((line = br.readLine()) != null){
												
							text.append("\n" + line);
							text.setLineWrap(true);
							
						}			
					
				}catch(FileNotFoundException fe){
					System.err.println("File Not Found!");
					//br.close();
				}catch(IOException ie){
					System.err.println("Error" + ie);
				}		
				
			}
		}	

	}
		
	private class SaveFile implements ActionListener {

		public void actionPerformed(ActionEvent ae){

			JFileChooser save = new JFileChooser();
			save.setCurrentDirectory(new File("/home/virtual-ark/Java-How-to-Program-Files/myProjects"));
			int saver = save.showSaveDialog(null);

			try {

				if(saver == JFileChooser.APPROVE_OPTION){
				
					FileWriter fw = new FileWriter(save.getSelectedFile());
					//System.out.println(text.getText());
					fw.write(String.format(text.getText()));
					fw.close();
				}
			} catch (Exception ex){
				ex.printStackTrace();
			} 

		}

	}
	
}

