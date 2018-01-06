package com.busy.minds.gameobjects;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * klasa dodawania nowegolosowego hitBlocka
 * Created by Jan on 28.12.2017.
 */
public class AddNewRandomBlock implements IGameAction{

    public AddNewRandomBlock(IBlockManager blockManager, HitBlock hitBlock){

       this.blockManager=blockManager;
        this.hitBlock = hitBlock;
    }
    /**nadpisana metoda dodająca losowy blok*/
    @Override
    public void Execute() {
        List <String> availableBlocks=new ArrayList<>();

        if (hitBlock.getColor() == Color.GREEN) {
            addNTimes(availableBlocks, "green", 5);
        } else if (hitBlock.getColor() == Color.RED) {
            addNTimes(availableBlocks, "red", 5);
        } else {
            addNTimes(availableBlocks, "red", 5);
            addNTimes(availableBlocks, "green", 5);
        }

        availableBlocks.add("purple");

        Collections.shuffle(availableBlocks);

       switch (availableBlocks.get(0)) {
           case "green":
               blockManager.AddGreenBlock();
               break;
           case "purple":
               blockManager.AddPurpleBlock();
               break;
           case "red":
               blockManager.AddRedBlock();
               break;
       }
    }

    private void addNTimes(List <String> list,  String blockName, int n) {
        for (int i = 0; i <  n; ++i)
            list.add(blockName);
    }

    /**interface IBlockManager*/
    private final IBlockManager blockManager;
    private final HitBlock hitBlock;
}
