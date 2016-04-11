
import java.io.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class OpenFile {
	
	private File file;
	
	private final String FRAME_TITLE = "Text Editor";
	
	static ArrayList<String> textData;
	private int count = 0;
	public OpenFile(File file){
		this.file = file;
	}
	
	public String readFile(){
		try {
			
			textFileEditor.textArea.setText("");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			textData = new ArrayList<String>();
				
			String lineData;
			
			textFileEditor.frame.setTitle(FRAME_TITLE + " - " + file.getName());
			
			while((lineData = reader.readLine()) != null){
				textData.add(lineData + "\n");
				count++;
				System.out.println(lineData);
			}
			
			String data = "";
			for(int i = 0; i < textData.size(); i++){
				data += textData.get(i).toString();
			}
			
			reader.close();
			
			//return textData.toString().replace(",", "").replace("[", "").replace("]", "");
			return data;
			
		} catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public int getNumOfLines(){
		return count;
	}
	
	public int getFileSize(){
		return textData.size();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public JTextArea setTextArea() {
		return textFileEditor.textArea;
	}
}
