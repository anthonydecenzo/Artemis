/*
 * No main use for this class, yet.
 * The intended use is to be able to 
 * Change the font of the text, but
 * JTextArea is consisted of plain-text
 * Looking at the RTF text format  
 */

import java.awt.Font;

public class FontModifier {
	public FontModifier(){
		
	}
	
	public void changeFont(){
		Font fontType = new Font("Dialog", Font.BOLD, 18);
		

		
		String text = textFileEditor.textArea.getSelectedText();
		
		textFileEditor.textArea.setFont(fontType);
	}
}
