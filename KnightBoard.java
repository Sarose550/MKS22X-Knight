import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

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
				result += Integer.toString(board[i][j]);
			}
		}
		result += "\n";
	}
	return result;
}

public boolean clearBoard(){
	for(int i = 0; i < startingRows; i++){
		for(int j = 0; j < startingCols; j++){
			board[i][j] = 0;
		}
	}
	return true;
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
	int[][] moves = new int[startingRows][startingCols];
	for(int row = 0; row < startingRows; row++){
		for(int col = 0; col < startingCols; col++){
			for(int i = -2; i <= 2; i += 4){
				for(int j = -1; j <= 1; j += 2){
					if(row + i >= 0 && row + i < startingRows){
						if(col + j >= 0 && col + j < startingCols){
							moves[row][col] += 1;
						}
					}
					if(row + j >= 0 && row + j < startingRows){
						if(col + i >= 0 && col + i < startingCols){
							moves[row][col] += 1;
						}
					}
				}
			}
		}
	}
	board[startingRow][startingCol] = 1;
	if(this.solveH(startingRow, startingCol, 1, moves)) return true;
	board[startingRow][startingCol] = 0;
	return false;
}

/*@throws IllegalStateException when the board contains non-zero values. 
@throws IllegalArgumentException when either parameter is negative 
 or out of bounds.*/
public int countSolutions(int startingRow, int startingCol){
	for(int i = startingRow; i < startingRows; i++){
		for(int j = startingCol; j < startingCols; j++){
			if(board[i][j] != 0) throw new IllegalStateException("Board contains non-zero values.");
			if(startingRow < 0 || startingRow >= startingRows || startingCol < 0 || startingCol >= startingCols){
				throw new IllegalArgumentException("Parameter out of bounds.");
			}
		}
	}
	board[startingRow][startingCol] = 1;
	int total = this.countH(startingRow, startingCol, 1);
	board[startingRow][startingCol] = 0;
	return total;

}

private int countH(int row, int col, int level){
	int total = 0;
	for(int i = -2; i <= 2; i += 4){
		for(int j = -1; j <= 1; j += 2){
			if(row + i >= 0 && row + i < startingRows){
				if(col+ j >= 0 && col + j < startingCols){
					if(board[row + i][col + j] == 0){
						board[row + i][col + j] = level + 1;
						if(level == startingRows * startingCols - 1){//you found a solution at max depth
							board[row + i][col + j] = 0;
							return 1;
						}
						total += this.countH(row + i, col + j, level + 1);//add the stuff you find
						board[row + i][col + j] = 0;
					}
				}
			}
			if(row + j >= 0 && row + j < startingRows){
				if(col + i >= 0 && col + i < startingCols){
					if(board[row + j][col + i] == 0){
						board[row + j][col + i] = level + 1;
						if(level == startingRows * startingCols - 1){//you found a solution at max depth
							board[row + j][col + i] = 0;
							return 1;
						}
						total += this.countH(row + j, col + i, level + 1);
						board[row + j][col + i] = 0;
					}
				}
			}
		}
	}
	return total;
}

//Suggestion:
private boolean solveH(int row, int col, int level, int[][] moves){
	ArrayList<Triple> options = new ArrayList<Triple>();
	for(int i = -2; i <= 2; i += 4){
		for(int j = -1; j <= 1; j += 2){
			if(row + i >= 0 && row + i < startingRows){
				if(col + j >= 0 && col + j < startingCols){
					if(board[row + i][col + j] == 0){
						options.add(new Triple(row + i, col + j, moves[row + i][col + j]));
					}
				}
			}
			if(row + j >= 0 && row + j < startingRows){
				if(col + i >= 0 && col + i < startingCols){
					if(board[row + j][col + i] == 0){
						options.add(new Triple(row + j, col + i, moves[row + j][col + i]));
					}
				}
			}
		}
	}
	Collections.sort(options, new SortByMoves());
	for(Triple t : options){
		board[t.x][t.y] = level + 1;
		if(level == startingRows * startingCols - 1) return true;
		for(int i = -2; i <= 2; i += 4){
			for(int j = -1; j <= 1; j += 2){
				if(t.x + i >= 0 && t.x + i < startingRows){
					if(t.y + j >= 0 && t.y + j < startingCols){
						moves[t.x + i][t.y + j] -= 1;
					}
				}
				if(t.x + j >= 0 && t.x + j < startingRows){
					if(t.y + i >= 0 && t.y + i < startingCols){
						moves[t.x + j][t.y + i] -= 1;
					}
				}
			}
		}
		if(this.solveH(t.x, t.y, level + 1, moves) == true) return true;
		for(int i = -2; i <= 2; i += 4){
			for(int j = -1; j <= 1; j += 2){
				if(t.x + i >= 0 && t.x + i < startingRows){
					if(t.y + j >= 0 && t.y + j < startingCols){
						moves[t.x + i][t.y + j] += 1;
					}
				}
				if(t.x + j >= 0 && t.x + j < startingRows){
					if(t.y + i >= 0 && t.y + i < startingCols){
						moves[t.x + j][t.y + i] += 1;
					}
				}
			}
		}
		board[t.x][t.y] = 0;
	}
	return false;
}
// level is the # of the knight
}

class Triple{
	int x, y, n;

	public Triple(){
		x = 0;
		y = 0;
		n = 0;
	}

	public Triple(int x, int y, int n){
		this.x = x;
		this.y = y;
		this.n = n;
	}
}

class SortByMoves implements Comparator<Triple>{
	public int compare(Triple a, Triple b){
		return a.n - b.n;
	}
}
