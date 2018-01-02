package com.busy.minds.gameobjects;

/**
 * Created by Jan on 28.12.2017.
 */
public class RemoveBlockAction implements IGameAction {

    public RemoveBlockAction(
        IBlockManager blockManager
        , HitBlock hitBlock
    ) {
        this.blockManager = blockManager;
        this.hitBlock = hitBlock;
    }

    @Override
    public void Execute() {
        blockManager.RemoveBlock(hitBlock);
    }

    private final IBlockManager blockManager;
    private final HitBlock hitBlock;
}
