public class QueenBoard{

  private int[][] board;
  public QueenBoard(int size){
    board = new int[size][size];
  }
  private void clear(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length;c++){
        board[r][c] = 0;
      }
    }
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
        //System.out.println(toString());
        System.out.println(r+i + "" + c+i);
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
        if (r-i > 0 && c+i < board.length) board[r-i][i+c]--;
        //diagonally y = -x
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
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        //for debugging
        if (board[r][c] > 0){
          output += "x ";
        }
        if (board[r][c] == 0){
          output += "_ ";
        }
        if (board[r][c] == -1){
          output += "Q ";
        }
      }
      output += "\n";
    }
    return output;
  }
  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    clear();
    return solve(0,0);
  }
  //helper function for solve
  public boolean solve(int r, int c){
    //will only happen if you can't place a queen and backtracking
    if (c < 0){
      return false;
    }
    //will only happen if all queens have been placed
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
        return solve(0,c-1);
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
  //for debugging
  public int[][] board(){
    return board;
  }


  public static void main(String[] args){
    QueenBoard q = new QueenBoard(4);
    //System.out.println(q.solve());
    q.addQueen(0,0);
    int[][] b = q.board();
    /**for (int i = 0; i < b.length; i++){
      for (int j = 0; j < b.length; j++){
        System.out.print(b[i][j] + " ");
      }
      System.out.println();
    }**/
    System.out.println(q.toString());
  }
}
