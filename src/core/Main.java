package core;

import java.io.File;

public class Main {

	/**
	 * @Author: Nate Rooth
	 * @Creation_Date: 2013-09-02
	 * @Description: Simple program that reads an ASCII text file of project groups, 
	 * randomizes the order, and writes out to a new ASCII text file.
	 * @Notes: Project groups must be separated by either a LF or the CRLF characters.
	 * Randomized groups are separated using a CRLF if run in Windows, and CR for all other environments. 
	 * @param args A single argument which names ASCII text file to read in
	 */
	public static void main(String[] args) {
		if (args.length > 0 && args.length < 2) {
			/* 
			 * Create new file object from user supplied filename
			 * Note: file is searched for in current directory
			 */
			String filename = System.getProperty("user.dir") + "\\" + args[0];
			File file = new File(filename);
			
			// Check if file exists
			if (!file.exists()) {
				System.err.printf("[!] %s does not exist\n", filename);
				System.exit(1);
				
			// Check if file is readable
			} else if (!file.canRead()) {
				System.err.printf("[!] Unable to read %s\n", filename);
				System.exit(1);
				
			} else {
				// Run randomizer
				System.out.printf("[+] Using %s\n", args[0]);
				Randomize randomizer = new Randomize(file);
				randomizer.Run();
			}
			
			
		// User didn't provide a filename	
		} else if (args.length < 1) {
			System.err.printf("[!] Please provide a filename");
			System.exit(1);
		
		// User was overzealous in his/her argument usage
		} else {
			System.err.printf("[!] Too many arguments");
			System.exit(1);
			
		}
		
	}

}
