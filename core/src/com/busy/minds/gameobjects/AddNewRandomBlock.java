package com.busy.minds.gameobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * klasa dodawania nowegolosowego hitBlocka
 * Created by Jan on 28.12.2017.
 */
public class AddNewRandomBlock implements IGameAction{

    public AddNewRandomBlock(IBlockManager blockManager){

       this.blockManager=blockManager;
    }
    /**nadpisana metoda dodająca losowy blok*/
    @Override
    public void Execute() {
        List <String>availableBlocks=new ArrayList<>();
        availableBlocks.add("green");
        availableBlocks.add("purple");
        availableBlocks.add("red");
        Collections.shuffle(availableBlocks);
       switch (availableBlocks.get(0)){
           case "green":blockManager.AddGreenBlock();
                break;
           case "purple":blockManager.AddPurpleBlock();
               break;
           case "red":blockManager.AddRedBlock();
               break;
       }

    }
    IBlockManager blockManager;
}
