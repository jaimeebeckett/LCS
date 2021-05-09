/**
 * LCSFinder
 * This class computes the longest common subsequence between two strings.
 * @author Jaimee Beckett
 * @version 1.0, May 1, 2021
 * 
 */
public class LCSFinder {
	protected String seq1;					/* The first sequence */
	protected String seq2;					/* The second sequence */
	protected int m;						/* The length of seq1 */
	protected int n;						/* The length of seq2*/
	protected int[][] matrix;				/* The matrix used for finding LCS */
	protected int lcsLength;				/* The length of the LCS */
	protected String lcs = "";				/* The LCS */
	protected int lengthComparisons = 0; /* Num of comparisons when creating matrix*/
	protected int printComparisons = 0;  /* Num of comparisons when printing LCS */

	/**
	 * LCSFinder Constructor that sets values for seq1, seq2, m, n, and creates 
	 * the matrix. createTable and longestCommonSubsequence are called.
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
	/*
	 * Finds the length of LCS by creating a matrix
	 */
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
				this.lengthComparisons++;
			}
		}
		this.lcsLength = this.matrix[this.m][this.n];
	}
	
	/*
	 * This is a recursive function that finds the LCS by using this.matrix
	 */
	private void longestCommonSubsequence(int i, int j) {
		this.printComparisons++;
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
	
	/*
	 * Prints relevant information associated with this class
	 */
	public void printLCS() {
		System.out.println("Longest Common Subsequence: ");
		System.out.println(this.lcs);
		System.out.println("Length: " + this.lcsLength);
		System.out.println("Create Table Comparison: " + this.lengthComparisons);
		System.out.println("Find the Longest Common Subsequence Comparisons: " 
						   + this.printComparisons);
	}
	
}
