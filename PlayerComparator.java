public class PlayerComparator implements Comparator <Player>{
  public int compare(Player player1, Player player2){
    if(player1.points>player2.points){
      return 1;
    }
    else if(player2.points>player1.points){
      return -1;
    }
    else{
      if(player1.rating>player2.rating){
        return 1;
      }
      else{
        return -1;
      }
    }
  }
}
