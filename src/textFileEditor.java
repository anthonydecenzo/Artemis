import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class textFileEditor {
	private File file;
	public final static String FRAME_TITLE = "Text Editor";
	
	// GUI Components
	static JTextArea textArea = new JTextArea();
	
	private JMenuItem menuItem_new;
	private JMenuItem menuItem_open;
	private JMenuItem menuItem_save;
	private JMenuItem menuItem_exit;
	private JMenuItem menuItem_font;
	private JMenuItem menuItem_delete;
	
	static JFrame frame;
	
	private JMenu menu_file;
	private JMenu menu_edit;
	
	private JMenuBar menuBar;
	
	private StatusBar statusBar;
	
	private int iLineCount;
	
	public static void main(String [] args){
		textFileEditor i = new textFileEditor();
		i.showGUI();
	}
	
	public void setFrameTitle(String s){
		frame.setTitle(s);
	}
	
	public void showGUI(){
		frame = new JFrame();
		menuBar = new JMenuBar();
		menu_file = new JMenu("File");
		menu_edit = new JMenu("Edit");
		
		// Add the status bar at the bottom
		statusBar = new StatusBar();
		
		// Menu item instantiations
		menuItem_new = new JMenuItem("New File..");
		menuItem_open = new JMenuItem("Open..");
		menuItem_save = new JMenuItem("Save..");
		menuItem_exit = new JMenuItem("Exit");
		menuItem_font = new JMenuItem("Change font");
		menuItem_delete = new JMenuItem("Replace");
		
		// Add the menu bar and menu items
		menuBar.add(menu_file);
		menuBar.add(menu_edit);
		
		menu_file.add(menuItem_new);
		menu_file.add(menuItem_open);
		menu_file.add(menuItem_save);
		menu_file.add(menuItem_exit);
		
		menu_edit.add(menuItem_font);
		menu_edit.add(menuItem_delete);
		
		// Add action listeners to the menu items
		menuItem_open.addActionListener(new openListener());
		menuItem_save.addActionListener(new saveListener());
		menuItem_exit.addActionListener(new exitListener());
		menuItem_font.addActionListener(new FontListener());
		menuItem_delete.addActionListener(new deleteListener());
		menuItem_new.addActionListener(new newFileListener());
		
		// Set the layout of the frame
		// Adding components to the frame 
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(textArea);
		frame.getContentPane().add(statusBar, java.awt.BorderLayout.SOUTH);
		frame.setJMenuBar(menuBar);
		frame.setTitle(FRAME_TITLE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set other properties
		textArea.setBounds(frame.getBounds());
		
		//getTextArea();
		
		iLineCount = textArea.getLineCount();
	}

	public JTextArea getTextArea(){
		return textArea;
	}
	
	public String getWindowName(){
		return frame.getTitle().toString();
	}
	
	class deleteListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
//			Edit_delete delete = new Edit_delete("hello");
//			delete.delete();
			
			GUI_Replace i = new GUI_Replace();
			i.show();
		}
	}
	
	class FontListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			FontModifier i = new FontModifier();
			i.changeFont();
		}
	}
	
	class newFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(!textArea.getText().isEmpty()){
				int reply = JOptionPane.showConfirmDialog(frame, "You haven't saved your current file, would you like to do so?", "Alert", JOptionPane.YES_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					SaveFile save_options = new SaveFile();
					try {
						save_options.save();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					textArea.setText(null);
				}
			}
		}
	}
	
	class openListener implements ActionListener{
		
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e){
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
			OpenFile opener;
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(menuItem_open);
			
			if(returnVal == chooser.APPROVE_OPTION){
				opener = new OpenFile(chooser.getSelectedFile());
//				for(int i = 1; i < count - 1; i++){
//					if(OpenFile.textData.get(i).charAt(0) == ' '){
//						String line = OpenFile.textData.get(i).substring(1).toString();
//						OpenFile.textData.set(i, line);
//						System.out.println(line);
//					}
//				}
				textArea.setText(opener.readFile());
				statusBar.setMessage("Line count: " + (textArea.getLineCount() - 1));
				file = chooser.getSelectedFile();
			}
		}
	}
	
	class saveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			SaveFile save_options = new SaveFile(file);
			try {
				save_options.save();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	class exitListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			int reply = JOptionPane.showConfirmDialog(frame, "Do you wish to exit?", "Exit", JOptionPane.YES_OPTION);
			if(reply == JOptionPane.YES_OPTION)
				System.exit(0);			
		}
	}
}
