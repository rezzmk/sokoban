package pt.iscte.dcti.poo.sokoban;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.Scanner;

public class FileHelper {
	
	private FileHelper() {
		throw new IllegalStateException("FileHelper is a Utility class, should not be instantiated");
	}
	
	public static char[][] get2DArrayFromFile(String fileName) throws IOException {
		Path filePath = new File(fileName).toPath();
		        
		List<String> stringList = Files.readAllLines(filePath);
		
		String[] stringArray = stringList.toArray(new String[]{});
		
		char[][] result = new char[stringArray[0].length()][stringArray.length];
		
		for(int x = 0; x < stringArray.length; x++) {
			for(int y = 0; y < stringArray[0].length(); y++) {
				result[y][x] = stringArray[x].charAt(y);
			}
		}
		
		return result;
	}
	
	public static boolean fileExists(String fullPath) {
		File file = new File(fullPath);
		return file.exists();
	}
	
	public static void writeToFile(Path fullPath, String content) {
		try {
			File file = new File(fullPath.toString());
			if (!file.exists()) {
				if(!file.createNewFile()) {
					// Log this
				}
			}
	    	FileWriter fw = new FileWriter(file,true);
	    	BufferedWriter bw = new BufferedWriter(fw);
	    	bw.newLine();
	    	bw.write(content);
	    	bw.close();
		} catch(IOException ex) {
			
		}		
	}
	
	public static String getLineFromFile(Path fullPath, String filter) {
		try {
			return Files.lines(fullPath).filter(line -> line.contains(filter)).findFirst().orElse("");
		} catch (IOException e) {
			return "";
		}
	}
	
	public static String getFileSplit(Path fullPath, String filter , String separator) {
		String line = getLineFromFile(fullPath, filter);
		
		return !line.isEmpty() ? line.split(separator)[1] : "";
	}
	
	public static void replaceLineInFile(Path fullPath, String c1, String c2) {
		try {
			Scanner sc = new Scanner(new File(fullPath.toString()));

			// TODO: Check if this is dangerous, StringBuffer is threadsafe (non async), but should take longer.
			// TODO: Check if we've got an async await pattern in Java like we do in other lanaguages
			// TODO: Check different ways to read/write files in Java, I'm used to faster C ways, this seems a bit wonky
			StringBuffer buffer = new StringBuffer();

			while (sc.hasNextLine()) {
				buffer.append(sc.nextLine()+System.lineSeparator());
			}
			String fileContents = buffer.toString();
			sc.close();
			fileContents = fileContents.replaceAll(c1, c2);

			FileWriter writer = new FileWriter(fullPath.toString());

			writer.append(fileContents);
			writer.flush();
			writer.close();
		}
		
		catch (FileNotFoundException e) {
			
		} 
		catch (IOException e) {
			
		}	     
	}
}
