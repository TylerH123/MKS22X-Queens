public class driver{
  public static void main(String[] args){
    QueenBoard q = new QueenBoard(4);
    //System.out.println(q.solve());
    q.addQueen(0,0);
    System.out.println(q.toString());
  }
}
