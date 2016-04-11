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
