package net.minecraft.src.AdvTurtleAPI;

import java.lang.reflect.Constructor;

import net.minecraft.src.TileEntity;
import net.minecraft.src.dan200.turtle.shared.BlockTurtle;
import net.minecraft.src.dan200.turtle.shared.TileEntityTurtle;

public class BlockMyTurtle extends BlockTurtle {

	public BlockMyTurtle(int blockID) {
		super(blockID);
	}

    private TileEntityTurtle createDefaultTurtle(int i, int j)
    {
    	return new TileMyEntityTurtle(j, i);
    }
    
    public TileEntity getBlockEntity(int i)
    {
        return createDefaultTurtle(2 + (i >> 2 & 3), i & 3);
    }    
}
