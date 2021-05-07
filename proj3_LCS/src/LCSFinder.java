/**
 * 
 */

/**
 * The class containing two strings and their longest common subsequence 
 * 
 * @author Jaimee Beckett
 * @version 1.0, May 1, 2021
 */
public class LCSFinder {
	protected String seq1;
	protected String seq2;
	protected int[][] matrix; 

	/**
	 * LCSFinder Constructor
	 * @param seq1: the first sequence
	 * @param seq2: the second sequence
	 */
	public LCSFinder(String seq1, String seq2) {
		this.seq1 = seq1;
		this.seq2 = seq2;
		this.matrix = new int[seq1.length()][seq2.length()];
		
		String lcs = this.longestCommonSubstring();
	}

	public String longestCommonSubstring() {
		for (int i=0; i<matrix.length; i++) {
			
		}
		
		return "";
	}
	
	public String interpretMatrix() {
		return "";
	}
	
	
}
