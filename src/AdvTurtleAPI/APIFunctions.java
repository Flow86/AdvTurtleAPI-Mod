package net.minecraft.src.AdvTurtleAPI;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.mod_CCTurtle;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;
import net.minecraft.src.dan200.computer.shared.ItemComputer;

public class APIFunctions {
	private static boolean isBlockInWorld(TileMyEntityTurtle turtle, int y) {
		return y >= 0 && y < turtle.worldObj.getWorldHeight();
	}

    private static ArrayList<ItemStack> getBlockDropped(int blockID, World world, int x, int y, int z)
    {
        Block block = Block.blocksList[blockID];
        int meta = world.getBlockMetadata(x, y, z);
        return block.getBlockDropped(world, x, y, z, meta, 0);
    }
	
	public static boolean place(TileMyEntityTurtle turtle, Offset3DBase offset, int face) {
		int x = turtle.xCoord + offset.xOffset(face);
		int y = turtle.yCoord + offset.yOffset(face);
		int z = turtle.zCoord + offset.zOffset(face);
		int meta = turtle.getOppositeDir(face);

		if(!isBlockInWorld(turtle, y))
			return false;

		ItemStack itemstack = turtle.takePlaceableItem(x, y, z, meta);

		if (itemstack == null)
			return false;

		Item item = Item.itemsList[itemstack.itemID];
		Block block = Block.blocksList[itemstack.itemID];

		// System.out.println("place: " + xOffset + "/" + yOffset + "/" +
		// zOffset + ": " + itemstack.itemID + "@" + meta);

		if (turtle.worldObj.setBlockAndMetadataWithNotify(x, y, z, itemstack.itemID, item.getMetadata(itemstack.getItemDamage()))) {
			if (turtle.worldObj.getBlockId(x, y, z) == itemstack.itemID) {
				block.onBlockPlaced(turtle.worldObj, x, y, z, meta);

				if (item instanceof ItemComputer)
					((ItemComputer) item).setupComputerAfterPlacement(itemstack, turtle.worldObj, x, y, z);
			}

			mod_CCTurtle.playBlockSound(turtle.worldObj, x + 0.5F, y + 0.5F, z + 0.5F, block);
			return true;
		}

		return false;
	}

	public static boolean dig(TileMyEntityTurtle turtle, Offset3DBase offset, int face) {
		if (!turtle.hasPick())
			return false;

		int x = turtle.xCoord + offset.xOffset(face);
		int y = turtle.yCoord + offset.yOffset(face);
		int z = turtle.zCoord + offset.zOffset(face);
		int meta = turtle.getOppositeDir(face);

		if(!isBlockInWorld(turtle, y))
			return false;

		int i1 = turtle.worldObj.getBlockId(x, y, z);

		if (i1 == 0 || i1 == Block.bedrock.blockID) {
			return false;
		}

		for(ItemStack itemStack : getBlockDropped(i1, turtle.worldObj, x, y, z))
		{
			boolean flag = turtle.storeItemStack(itemStack);
			if (!flag)
				turtle.dropStack(itemStack, meta);
		}

		Block block = Block.blocksList[i1];
		turtle.worldObj.playAuxSFX(2001, x, y, z, block.blockID + turtle.worldObj.getBlockMetadata(x, y, z) * 4096);
		turtle.worldObj.setBlockWithNotify(x, y, z, 0);
		
		return true;
	}
}
