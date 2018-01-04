package com.busy.minds.gameobjects;

/**
 *klasa usuwania bloków z osi poziomej
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

    /**nadpisuje medę pozwalajacą na usunięcie bloku*/
    @Override
    public void Execute() {
        blockManager.RemoveBlock(hitBlock);
    }
    //interfejs
    private final IBlockManager blockManager;
    //HitBlock
    private final HitBlock hitBlock;
}
