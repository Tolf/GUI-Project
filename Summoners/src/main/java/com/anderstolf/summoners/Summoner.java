
package com.anderstolf.summoners;

import javafx.beans.binding.Bindings;
import static javafx.beans.binding.Bindings.add;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Anders
 */
public class Summoner {
    
    private StringProperty nickName;
    private IntegerProperty wins;
    private IntegerProperty losses;
//    private IntegerProperty games;
    private StringProperty description;
    private NumberBinding totalGames;
    
   
//        totalGames = Bindings.add(wins, losses);
//        games.setValue(totalGames.intValue());
    


    public void setNickname(String name){
        nickNameProperty().set(name);
    }
    
    public void setWins(int wins){
        wins().set(wins);
    }
    
    public void setLosses(int losses){
        losses().set(losses);
    }
    
    public int getLosses(){
        return losses.get();
    }
    
    public int getWins(){
        return wins.get();
    }
    
//    public int getGames(){
//        return games.get();
//    }
    
    public String getNick(){
        return nickName.get();
    }
 
    public StringProperty nickNameProperty(){
        if (nickName == null){
            nickName = new SimpleStringProperty();
        }
        return nickName;
    }
    public IntegerProperty wins(){
        if (wins == null){
            wins = new SimpleIntegerProperty();
        }
        return wins;
    }
    public IntegerProperty losses(){
        if( losses == null){
            losses = new SimpleIntegerProperty();
        }
        return losses;
    }
//    public IntegerProperty games(){
//        if(games == null){
//            games = new SimpleIntegerProperty();
//            games.set(wins().get()+losses().get());
//        }
//        return games;
//    }
    public StringProperty description(){
        if(description == null){
            description = new SimpleStringProperty();
            totalGames = Bindings.add(wins, losses);
        description.setValue("Nickname: "+ this.getNick() 
                          + "\nNumber of games: " + totalGames.getValue()
                          + "\nNumber of wins: " + getWins()
                          + "\nNumber of losses: " + getLosses());
        }
        return description;
    }
    
    
    
    
}

