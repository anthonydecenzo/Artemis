/*
 * This class uses the GridBagLayout with GridBagConstraints
 * To construct the GUI and has action listeners for the buttons functionality
 * Also includes a method that replaces the specified text with text of choice.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI_Replace {
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JTextField find = new JTextField("Find..");
	private JTextField replace = new JTextField("Replacement text");
	private JButton button = new JButton("test");
	
	public GUI_Replace(){
		button.addActionListener(new replaceListener());
	}
	
	public void show(){
		
		panel.setLayout(new GridBagLayout());
		frame.add(panel);

		
		
		find.setSize(50, 20);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 3;       //third row
		
		panel.add(button, c);
		
		c.gridx = 2;
		c.gridwidth = 2;
		c.gridy = 1;
		
		panel.add(find, c);
		
		c.gridx = 2;
		c.gridy = 2;
		panel.add(replace, c);
		frame.setTitle("Replace");
		frame.setSize(300, 150);
		frame.setVisible(true);
		
	}
	
	public void setTitle(String s){
		frame.setTitle(s);
	}
	
	public String getTextToReplace(){
		return find.getText();
	}
	
	public String getTextToReplaceWith(){
		return replace.getText();
	}
	
	public void replace(String textToReplace, String textToReplaceWith){
		String base = textFileEditor.textArea.getText();
		
		int start = base.indexOf(getTextToReplace());	
		
		if(start != -1){
			textFileEditor.textArea.select(start, start + getTextToReplace().length());
			
			textFileEditor.textArea.replaceSelection(getTextToReplaceWith());
		} else{
			JOptionPane.showMessageDialog(frame, "The text " + getTextToReplace() + " has not been found.");
		}
		
	}
	
	class replaceListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			replace(getTextToReplace(), getTextToReplaceWith());
		}
	}
}
