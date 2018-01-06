package com.busy.minds.gameobjects;

/**
 * interface zarządzania blokami
 * Created by Jan on 28.12.2017.
 */
public interface IBlockManager {

    void RemoveBlock(HitBlock hitBlock);
    void AddGreenBlock();
    void AddRedBlock();
    void AddPurpleBlock();
}
