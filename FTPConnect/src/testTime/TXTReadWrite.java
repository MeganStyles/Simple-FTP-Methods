package testTime;

/**
 * @project 	HealthAlliance Mule Migration 
 * @author  	ZafraanA
 * @Team    	End2End Quality Team @ Assurity.co.nz
 * @date    	Jun 21, 2017
 * @Description Class Description
 */
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.*;
import org.junit.Test;

public class TXTReadWrite {

	public String readFromFile(String fileNamePath) throws IOException {
		try {
			return new String(Files.readAllBytes(Paths.get(fileNamePath)), StandardCharsets.UTF_8);
			
		} catch(IOException e) {
			throw new IOException("Could not read from file.", e);
		}
	}

	public void writeToFile(String fileNamePath, String dataToWrite, boolean insertInNewLine) throws IOException {
		try {
			if (insertInNewLine) {
				Files.write(Paths.get(fileNamePath), (System.lineSeparator() + dataToWrite).getBytes(),
						StandardOpenOption.APPEND);
			} else {
				Files.write(Paths.get(fileNamePath), dataToWrite.getBytes(), StandardOpenOption.APPEND);
			}
		} catch (IOException e) {
			throw new IOException("Could not write to file.", e);
		}

	}
	
	public int countInstance(String instance, String processedString) throws Exception	{
		int count = 0;
		char[] array = processedString.toCharArray();
		char firstMatch = instance.charAt(0);
		for (int currentChar = 0; currentChar < array.length-1; currentChar++) {
			if (array[currentChar] == firstMatch) {
				try {
					for (int matchChar = 1; matchChar<instance.length(); matchChar++) {	
						currentChar++;
						if (instance.charAt(matchChar) == array[currentChar]) {
							if (array[currentChar] == instance.charAt(instance.length()-1)) {
								count++;
							}
						} else{
							break;
						}
					
					}
				} catch(Exception e) {
					throw new IOException("Could not count word.", e);
				}
			}
		}
		return count;
	}
	
}

