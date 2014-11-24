
package com.anderstolf.summoners;

import javafx.scene.control.ListCell;
import javafx.scene.text.Font;

/**
 *
 * @author Anders
 */
public class SummonerNameCell extends ListCell<Summoner>{
    
    @Override
    protected void updateItem (Summoner item, boolean empty){
        super.updateItem(item, empty);
        
        if(item == null || empty){
            setGraphic(null);
            setText(null);
        }
        else {
            setText(item.nickNameProperty().get());
            setFont(Font.font(java.awt.Font.MONOSPACED, 22));
            
        }
    }
}
