public class KnightBoard{

	private int[][] board;
	private int startingRows;
	private int startingCols;
	//@throws IllegalArgumentException when either parameter is negative.
public KnightBoard(int startingRows,int startingCols){
	this.startingRows = startingRows;
	this.startingCols = startingCols;
	board = new int[startingRows][startingCols];
}
 
    //Initialize the board to the correct size and make them all 0's 


public String toString(){
	String result = "";
	for(int i = 0; i < startingRows; i++){
		for(int j = 0; j < startingCols; j++){
			if(startingRows * startingCols >= 10 && board[i][j] < 10){
				result += "  ";
			}
			else{
				result += " ";
			}
			if(board[i][j] == 0){
				result += "_";
			}
			else{
				result += board[i][j].toString();
			}
		}
		result += "\n";
	}
} 
/*see format for toString below
blank boards display 0's as underscores 
you get a blank board if you never called solve or 
when there is no solution 
*/
/*@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative 
 or out of bounds.
 */
public boolean solve(int startingRow, int startingCol){
	for(int i = startingRow; i < startingRows; i++){
		for(int j = startingCol; j < startingCols; j++){
			if(board[i][j] != 0) throw new IllegalStateException("Board contains non-zero values.");
			if(startingRow < 0 || startingRow >= startingRows || startingCol < 0 || startingCol >= startingCols){
				throw new IllegalArgumentException("Parameter out of bounds.");
			}
		}
	}
	this.solveH(startingRow, startingCol, 0);
}

/*@throws IllegalStateException when the board contains non-zero values. 
@throws IllegalArgumentException when either parameter is negative 
 or out of bounds.*/
public int countSolutions(int startingRow, int startingCol)

//Suggestion:
private boolean solveH(int row ,int col, int level){
	
}
// level is the # of the knight
}
