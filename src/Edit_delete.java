/*
 * The use of this class is to add
 * Functionality to the delete JMenuBar
 */

public class Edit_delete {
	String str = "";
	public Edit_delete(String str){
		this.str = str;
	}
	
	public void delete(){
		textFileEditor.textArea.setText(textFileEditor.textArea.getText().replaceAll(str, ""));
	}
}
