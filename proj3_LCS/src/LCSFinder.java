import java.util.Arrays;

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
	protected int m;
	protected int n;
	protected int[][] matrix;
	protected int lcs_length;
	protected String lcs = "";
	

	/**
	 * LCSFinder Constructor
	 * @param seq1: the first sequence
	 * @param seq2: the second sequence
	 */
	public LCSFinder(String seq1, String seq2) {
		this.seq1 = seq1;
		this.seq2 = seq2;
		this.m = seq1.length();
		this.n = seq2.length();
		this.matrix = new int[seq1.length()+1][seq2.length()+1];
		
		this.createTable();
		this.longestCommonSubsequence(this.m, this.n);
	}

	private void createTable() {
		for (int i=1; i<=this.m; i++) {
			for (int j=1; j<=this.n; j++) {
				if (this.seq1.charAt(i-1) == this.seq2.charAt(j-1)) {
					this.matrix[i][j] = this.matrix[i-1][j-1]+1;
				} 
				else if(this.matrix[i-1][j] >= this.matrix[i][j-1]) {
					this.matrix[i][j] = this.matrix[i-1][j];
				}
				else {
					this.matrix[i][j] = this.matrix[i][j-1];
				}
			}
		}
		this.lcs_length = this.matrix[this.m][this.n];
	}
	
	private void longestCommonSubsequence(int i, int j) {
		if(i == 0 || j==0) {
			return;
		}
		if(this.seq1.charAt(i-1) == this.seq2.charAt(j-1)) {
			this.longestCommonSubsequence(i-1, j-1);
			this.lcs += this.seq1.charAt(i-1);
		}
		else if(this.matrix[i-1][j] >= this.matrix[i][j-1]) {
			this.longestCommonSubsequence(i-1, j);
		}
		else {
			this.longestCommonSubsequence(i, j-1);
		}
	}
	
	public void printLCS() {
		System.out.println("Longest Common Subsequence: ");
		System.out.println(this.lcs);
		System.out.println("Length: " + this.lcs_length);
		System.out.println();
	}
	
}
