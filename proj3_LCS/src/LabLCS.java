import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * A class that reads sequences from a file and writes the longest common
 * subsequence between each pair to an output file.
 * 
 * @author Jaimee Beckett
 * @version 1.0, May 1, 2021
 */
public class LabLCS {

	/**
	 * This is the driving code for finding, and printing the longest common
	 * subsequence
	 */
	public static void main(String[] args) {
		/* if 0 arguments, print error and end */
		if (args.length == 0) {
			System.out.println("Error: No filename given. Exiting program now.");
			System.exit(0);
		}
		
		try {
			System.setOut(new PrintStream(new FileOutputStream("output.txt")));
		}catch(Exception e) {
			System.out.println("There was an error with the output file: ");
			System.out.println(e.toString());
			System.out.println("The output will not be redirected to a text file");
		}
		
		/* if 2+ arguments, print warning that only first argument will be read */
		if (args.length >= 2) {
			System.out.println("Warning: only first argument is being used. Ignoring"
							   + " the rest");
		}

		ArrayList<String> seqs = read_input(args[0]);
		System.out.println("Number of valid sequences given: " + seqs.size() + "\n");

		/* print out the valid sequences and their lengths */
		for (int i = 0; i < seqs.size(); i++) {
			System.out.println("Sequence #" + (i + 1) + " | Length: " + seqs.get(i)
																		.length());
			System.out.println(seqs.get(i) + "\n");
		}
		System.out.println("*".repeat(70));

		int comparisons = 0;				/*The number of attempted comparisons*/
		int successfulComparisons = 0;		/*The number of successful comparisons*/
		long totalExecutionTime = 0;		/*The run time for all comparisons*/

		for (int i = 0; i < seqs.size(); i++) {
			for (int j = i + 1; j < seqs.size(); j++) {
				comparisons++;
				System.out.println("Comparisons #" + comparisons + ": sequences " 
								   + (i + 1) + " and " + (j + 1) + ":");
				try {
					long startTime = System.nanoTime();
					LCSFinder lcs = new LCSFinder(seqs.get(i), seqs.get(j));
					long endTime = System.nanoTime();
					totalExecutionTime += (endTime - startTime);
					lcs.printLCS();
					successfulComparisons++;

					System.out.println("Execution Time: " + (endTime - startTime) 
									   + " ns");
					System.out.println("*".repeat(70));
					
				} catch (StackOverflowError e) {
					System.out.println(
							"There has been a Stackoverflow Error. You probably "
							+ "enterd a string that was too large.");
					System.out.println("*".repeat(70));
				}
			}
		}
		System.out.println("There were " + successfulComparisons 
						   + " successful pairwise comparisons");
		System.out.println("Total Execution Time: " + totalExecutionTime + " ns");
	}

	/**
	 * This method opens and reads the input file containing sequences, stores each
	 * one an ArrayList before the ArrayList is returned
	 */
	private static ArrayList<String> read_input(String filename) {
		/* Create a list of all of the sequences */
		ArrayList<String> sequences = new ArrayList<String>();

		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));

			String line;
			while ((line = input.readLine()) != null) {
				/* if line is not empty get the string */
				if (!line.isEmpty()) {
					/* Select the sequence from the line. Only accept letters A, T, 
					 * G, or C. Everything that is not these four letters will be 
					 * ignored */
					Pattern p = Pattern.compile("[atgcATGC]+");
					Matcher m = p.matcher(line);

					if (m.find()) {
						/* If there is a value other than ATG or C in the sequence, 
						 * it will not be used
						 */
						if (m.groupCount() >= 1) {
							System.out.println("Error in sequence: " + line 
												+ ". Sequence will not be used.");
						} else {
							sequences.add(m.group(0));
						}
					}
				}
			}
			input.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			System.exit(1);
		}

		/* If there are 0-1 sequences exit the program */
		if (sequences.size() < 2) {
			System.out.println("Error: Insufficient number of sequences. "
								+ "Exiting program now.");
			System.exit(0);
		}
		return sequences;
	}

}
