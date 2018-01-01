package com.busy.minds.gameobjects;

import java.util.Random;

/**
 * Created by Jan on 28.12.2017.
 */
public class AddNewRandomBlock implements IGameAction{

    public AddNewRandomBlock(IBlockManager blockManager){

       this.blockManager=blockManager;
    }
    @Override
    public void Execute() {
        Random random = new Random();
        if(random.nextFloat()>0.6f){
            blockManager.AddGreenBlock();
        }else
        if(random.nextFloat()<0.3f){
            blockManager.AddRedBlock();
        }else{
            blockManager.AddPurpleBlock();
        }
    }
    IBlockManager blockManager;
}
