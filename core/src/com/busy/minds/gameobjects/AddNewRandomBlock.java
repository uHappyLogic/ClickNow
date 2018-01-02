package com.busy.minds.gameobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * Created by Jan on 28.12.2017.
 */
public class AddNewRandomBlock implements IGameAction{

    public AddNewRandomBlock(IBlockManager blockManager){

       this.blockManager=blockManager;
    }
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

//        Random random = new Random();
//        if(random.nextFloat()>0.6f){
//            blockManager.AddGreenBlock();
//        }else
//        if(random.nextFloat()<0.3f){
//            blockManager.AddRedBlock();
//        }else{
//            blockManager.AddPurpleBlock();
//        }
    }
    IBlockManager blockManager;
}
