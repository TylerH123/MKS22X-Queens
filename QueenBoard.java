public class QueenBoard{

  private int[][] board;
  public QueenBoard(int size){
    board = new int[size][size];
  }
  private boolean addQueen(int r, int c){
    if (board[r][c] == 0){
      //mark places that other queen cannot go
      for (int i = 0; i < board.length;i++){
        //vertically
        board[i][c]++;
        //horizontally
        board[r][i]++;
        //diagonally y = x
        //check bounds
        if (r-i > 0 && c+i < board.length) board[r-i][i+c]++;
        //diagonally y = -x
        //check bounds
        if (r+i < board.length && c+i < board.length) board[r+i][c+i]++;
      }
      //place queen
      board[r][c] = -1;
      return true;
    }
    return false;
  }
  private boolean removeQueen(int r, int c){
    if (r >= board.length || c >= board.length) return false;
    //check to make sure the loc is a queen
    if (board[r][c] == -1){
      //remove the places that have been marked
      for (int i = 0; i < board.length; i++){
        //vertically
        board[i][c]--;
        //horizontally
        board[r][i]--;
        //diagonally y = x
        //check bounds
        if (r-i > 0 && c+i < board.length) board[r-i][i+c]--;
        //diagonally y = -x
        //check bounds
        if (r+i < board.length && c+i < board.length) board[r+i][c+i]--;
      }
      board[r][c] = 0;
      return true;
    }
    return false;
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
    return solve(0,0);
  }
  //helper function for solve
  public boolean solve(int r, int c){
    //one of the base cases
    //will only happen if you can't place a queen
    if (r >= board.length){
      return false;
    }
    if (c >= board.length){
      return true;
    }
    //recursive call if queen can be placed
    if (addQueen(r,c)){
      //then move to next column
      return solve(r,c+1);
    }
    //if cannot add queen
    else{
      //remove the queen that was placed
      removeQueen(r,c);
      //if the row is greater than board size then go back a column
      if (r >= board.length){
        return solve(r,c-1);
      }
      //otherwise just go down a row
      return solve(r+1,c);
    }
  }
  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    return 0;
  }
}
