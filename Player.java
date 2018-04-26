//This class creates an object of type Player. It contains relevant parameters
//such as name, nationality, age, rating, and the number of points they have
//scored in the tournament.

import java.util.*;

public class Player implements Comparator<Player>, Comparable<Player>{

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
    //the player starts at 0 points
    points = 0;
  }

  public double getPoints(){
    return points;
  }
  //called when the player wins
  public void incrementPoints(){
    points++;
  }
  //called when the player draws
  public void incrementHalfPoints(){
    points+=1/2;
  }

  //The Comporator and Comparable are used to sort a list of type Player

  public int compareTo(Player player2){
    if(this.points>player2.points){
      return -1;
    }
    else if(player2.points>this.points){
      return 1;
    }
    else{
      if(this.rating>player2.rating){
        return -1;
      }
      else{
        return 1;
      }
    }
  }

  public int compare(Player player1, Player player2){
    if(player1.points>player2.points){
      return -1;
    }
    else if(player2.points>player1.points){
      return 1;
    }
    else{
      if(player1.rating>player2.rating){
        return -1;
      }
      else{
        return 1;
      }
    }
  }

  public String toString(){
    return(this.name);
  }
}
