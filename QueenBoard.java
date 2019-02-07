public class QueenBoard{

  private int[][] board;
  public QueenBoard(int size){
    board = new int[size][size];
  }
  private boolean addQueen(int r, int c){
    if (board[r][c] == 0){
      board[r][c] = -1;
      return true;
    }
    return false;
  }
  private boolean removeQueen(int r, int c){
    board[r][c] = 0;
    return true;
  }
  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String output = "";
    for (int i = 0; i < board.length; i++){
      if (board[i][i] >= 0){
        output += "_ ";
      }
      else if (board[i][i] == -1){
        output += "Q ";
      }
    }
    return output;
  }
  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    if ()
  }
  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    return 0;
  }
}
