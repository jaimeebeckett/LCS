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

	/**
	 * LCSFinder Constructor
	 * @param seq1: the first sequence
	 * @param seq2: the second sequence
	 */
	public LCSFinder(String seq1, String seq2) {
		this.seq1 = seq1;
		this.seq2 = seq2;
		
		String lcs = this.longestCommonSubstring();
	}

	public String longestCommonSubstring() {
		return "";
	}
	
	
}
