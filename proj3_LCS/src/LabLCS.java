import java.io.*;
import java.util.*;
import java.util.regex.*;
/**
 * A class that reads sequences from a file and writes the 
 * longest common subsequence between each pair to an output file.
 * 
 * @author Jaimee Beckett
 * @version 1.0, May 1, 2021
 */
public class LabLCS {
	
	/**
	 * This is the driving code for finding, and printing the longest common subsequence 
	 * @param args
	 */
	public static void main(String[] args) {
		//if 0 arguments, print error and end
		if (args.length == 0) {
			System.out.println("Error: No filename given. Exiting program now.");
			System.exit(0);
		}
		
		//if 2+ arguments, print warning that only first argument will be read
		if (args.length >= 2) {
			System.out.println("Warning; only first argument is being used. Ignoring the rest");
		}

		//call read_input
		ArrayList<String> seqs = read_input(args[0]);
		System.out.println("Number of valid sequences given: " + seqs.size() + "\n");
		
		for (int i=0; i<seqs.size(); i++) {
			System.out.println("Sequence #"+ (i+1) +" | Length: "+ seqs.get(i).length());
			System.out.println(seqs.get(i) + "\n");
		}
		
		for (int i=0; i<seqs.size(); i++) {
			for (int j=i; j<seqs.size(); j++) {
				System.out.println("Comparing sequences " + i + " and " + j);
				LCSFinder pair = new LCSFinder(seqs.get(0), seqs.get(1));
			}
		}
	}
	
	/**
	 * Method that reads the input and stores the sequences into an array
	 * @param filename The name of the file to be opened
	 * @return an ArrayList<String> sequences that contains all of the valid sequences from the input file
	 */
	private static ArrayList<String> read_input(String filename){
		//Create a list of all of the sequences
		ArrayList<String> sequences = new ArrayList<String>();
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));

			String line;
			while ((line = input.readLine()) != null) {
				//if line is not empty get the string
				if (!line.isEmpty()) {
					//Select the sequence from the line. Only select letters A, T, G, or C
					//Values before and after the the sequence will be ignored 
					Pattern p = Pattern.compile("[atgcATGC]+");
					Matcher m = p.matcher(line);
						
					if (m.find()) {
						//If there is a value other than ATG or C in the sequence, it will not be used
						if(m.groupCount() >= 1) {
							System.out.println("Error in sequence: " + line + ". Sequence will not be used.");
						}
						else {
							sequences.add(m.group(0));
						}
					}	
				}
			}
			
			input.close();
			
		}catch (Exception e) {
			System.out.println(e.toString());
			System.exit(1);
		}
		
		//If there are 0-1 sequences exit the program
		if (sequences.size() < 2){
			System.out.println("Error: Insufficient number of sequences. Exiting program now.");
			System.exit(0);
		}
		
		return sequences;
	}
	
	
}
