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
}
