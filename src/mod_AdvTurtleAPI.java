package net.minecraft.src;

import net.minecraft.src.AdvTurtleAPI.BlockMyTurtle;
import net.minecraft.src.AdvTurtleAPI.TileMyEntityTurtle;
import net.minecraft.src.dan200.turtle.shared.BlockTurtle;

public class mod_CCTurtleAPI extends BaseModMp {
	
	@Override
	public String getPriorities() {
		return "after:mod_CCTurtle";
	}

	@Override
	public String getVersion() {
		return "0.1";
	}

	@Override
	public void load() {
		BlockTurtle turtle = mod_CCTurtle.turtle;
		Block.blocksList[turtle.blockID] = null;
		mod_CCTurtle.turtle = new BlockMyTurtle(turtle.blockID);
		mod_CCTurtle.turtle.setHardness(1.0F).setBlockName("turtle").setRequiresSelfNotify();
		mod_CCTurtle.turtle.blockRenderID = turtle.blockRenderID;

		ModLoader.registerBlock(mod_CCTurtle.turtle, mod_CCTurtle.itemTurtle.getClass());

		ModLoader.registerTileEntity(TileMyEntityTurtle.class, "turtle", mod_CCTurtle.turtleRenderer);
	}
}
