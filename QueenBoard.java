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
        if (r-i >= 0 && c+i < board.length) board[r-i][i+c]++;
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
        if (r-i >= 0 && c+i < board.length) board[r-i][i+c]--;
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
        //if (board[r][c] > 0){
        //  output += "x ";
        //}
        if (board[r][c] >= 0){
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
    return solve(0);
  }
  //helper function for solve
  public boolean solve(int c){
    //will only happen if all queens have been placed
    if (c >= board.length){
      return true;
    }
    for (int i = 0; i < board.length; i++){
      //recursive call if queen can be placed
      if (addQueen(i,c)){
        //then move to next column
        if (solve(c+1)){
          return true;
        }
        //if cannot add queen to the next column
        else{
          //backtrack
          //remove the queen that was placed
          removeQueen(i,c);
        }
      }
    }
    return false;
  }
  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    //clear board
    clear();
    return countSolutions(0);
  }
  public int countSolutions(int c){
    //count of solutions
    int count = 0;
    //will only happen if all queens have been placed
    if (c >= board.length){
      return 1;
    }
    for (int i = 0; i < board.length; i++){
      //if queen can be added
      if (addQueen(i,c)){
        //then go to next column
        count += countSolutions(c+1);
        //remove queen after
        removeQueen(i,c);
      }
    }
    return count;
  }
  //for debugging
  public int[][] board(){
    return board;
  }


  public static void main(String[] args){
    QueenBoard q = new QueenBoard(4);
    System.out.println(q.solve());
    //q.addQueen(0,0);
    System.out.println(q.toString());
    int[][] b = q.board();
    /**for (int i = 0; i < b.length; i++){
      for (int j = 0; j < b.length; j++){
        System.out.print(b[i][j] + " ");
      }
      System.out.println();
    }**/
    /**System.out.println(q.toString());
    q.addQueen(2,1);
    System.out.println(q.toString());
    q.removeQueen(0,0);
    System.out.println(q.toString());
    **/
  }
}
