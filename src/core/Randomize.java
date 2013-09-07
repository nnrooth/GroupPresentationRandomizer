package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: NNRooth
 * @Creation_Date: 2013-09-02
 * @Description: The actual class which performs IO and group randomization
 */
public class Randomize {

	// The default ASCII output filename
	private final String DEFAULT_OUTPUT_FILENAME = "groups_random.txt";
	
	// IO files
	private File fileIn;
	private File fileOut;
	
	// Constructor
	public Randomize(File fileIn) {
		this.fileIn = fileIn;
		this.fileOut = new File(DEFAULT_OUTPUT_FILENAME);
	}
	
	public void Run() {
		// Create a new ArrayList to temporarily store groups
		ArrayList<String> groups = new ArrayList<String>();
		
		// Attempt to read the input file
		try (BufferedReader buffy = new BufferedReader(new FileReader(fileIn)))
		{
			// Read each line of input
			String readLine;
			while ((readLine = buffy.readLine()) != null) {
				// Check for group keyword and add if found
				if (readLine.toUpperCase().contains("GROUP")) groups.add(readLine.trim());
			}
		} 
		catch (Exception e)
		{
			// Needs more flux capacitors!!!
			System.err.printf("[!] OOPS... we dun goofed\n");
			return;
		}
		
		int n = 0;
		// Create an array of Strings the size of the ArrayList
		String[] randomGroups = new String[groups.size()];
		// Pseudo randomly select a group move it from the ArrayList into the array of Strings
		for (Random randy = new Random(); !groups.isEmpty(); 
				randomGroups[n++] = groups.remove(randy.nextInt(groups.size())));
		
		// Determine newline type to use. If win use CRLF, else assume Unix and use LF
		String newline = System.getProperty("os.name").indexOf("win") >= 0 ? "\r\n" : "\n"; 
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut)))
		{
			// For each group in the randomized groups array
			for (String group : randomGroups) {
				// Write to the output file
				writer.write(group + newline);
				// And to stdio
				System.out.printf("[+] %s%s", group, newline);
			}
		}
		catch (Exception e)
		{
			// Ummm... ARGHH?
			System.err.printf("[!] OOPS... we dun goofed\n");
			return;
		}
		
	}
	
}
