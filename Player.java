import java.util.*;

public class Player implements Comparator<Player>{
  String name;
  String nationality;
  int age;
  int rating;
  double points;

  public Player(String n, String na, int a, int rating){
    this.name = n;
    this.nationality = na;
    this.age = a;
    this.rating = rating;
    points = 0;
  }
  public double getPoints(){
    return points;
  }
  public void incrementPoints(){
    points++;
  }
  public void incrementHalfPoints(){
    points+=1/2;
  }
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
