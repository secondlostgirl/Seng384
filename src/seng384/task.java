
package seng384;
import java.io.*;

public class task {
	public static void main(String[]args) throws IOException {
	File file = new File("task.txt");
	if(!file.exists()){
		file.createNewFile();
	}
	String task1= "do your math homework";
	FileWriter fWriter= new FileWriter(file,false);
	BufferedWriter bWriter= new BufferedWriter(fWriter);
	bWriter.write(task1);
	bWriter.close();
	System.out.println("Dosya yolu: " + file.getAbsolutePath());

	
	
	
}
}
