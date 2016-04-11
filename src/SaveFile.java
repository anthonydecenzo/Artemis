import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SaveFile {
	private File file;
	private ArrayList<String> textAreaData = new ArrayList<String>();
	public SaveFile(){
		this.file = null;
	}
	
	public SaveFile(File file){
		this.file = file;
	}
	
	public void save() throws IOException{
		System.out.println("saving file...");
		
		String saveFile;
		


		
		if(!textFileEditor.frame.getTitle().equals(textFileEditor.FRAME_TITLE)){
			saveFile = file.toString();
			
			for(String line : textFileEditor.textArea.getText().split("\\n")){
				textAreaData.add(line);
			}
			
			try{
				BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
				for(int i = 0; i < textAreaData.size(); i++){
					String data = (String) textAreaData.get(i);
					
					writer.write(data + System.getProperty("line.separator"));
				}
				
				writer.close();
				
			} catch(IOException e2){
				e2.printStackTrace();
			}
		} else {
			System.out.println("creating a new file");
			
			saveAs();
		}
	}
	
	public void saveAs(){
		JFileChooser saver = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		
		saver.addChoosableFileFilter(filter);
		
//		saver.setFileFilter("txt");
		
		int reply = saver.showSaveDialog(textFileEditor.frame);
		
		if(reply == JFileChooser.APPROVE_OPTION){
			File directory = saver.getSelectedFile();

			for(String line : textFileEditor.textArea.getText().split("\\n")){
				textAreaData.add(line);
			}
			
			try{
				BufferedWriter writer = new BufferedWriter(new FileWriter(directory.toString() + ".txt"));
				for(int i = 0; i < textAreaData.size(); i++){
					String data = (String) textAreaData.get(i);
					
					writer.write(data + System.getProperty("line.separator"));
				}
				
				writer.close();
				
			} catch(IOException e2){
				e2.printStackTrace();
			}
		}
	}
}
