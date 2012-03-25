package net.minecraft.src.AdvTurtleAPI;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.mod_CCTurtle;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;
import net.minecraft.src.dan200.computer.shared.ItemComputer;

public class APIFunctions {
	private static EntityPlayer tempPlayer = null;

	private static EntityPlayer getTempPlayer(World world) {
		if (tempPlayer == null) {
			tempPlayer = new EntityPlayer(world) {
				@Override
				public void func_6420_o() {
				}
			};
		}
		return tempPlayer;
	}

	private static boolean isBlockInWorld(TileMyEntityTurtle turtle, int y) {
		return y >= 0 && y < turtle.worldObj.getWorldHeight();
	}

	private static ArrayList<ItemStack> getBlockDropped(int blockID, World world, int x, int y, int z) {
		Block block = Block.blocksList[blockID];
		int meta = world.getBlockMetadata(x, y, z);
		return block.getBlockDropped(world, x, y, z, meta, 0);
	}

	public static boolean place(TileMyEntityTurtle turtle, Offset3DBase offset, int face) {
		int x = turtle.xCoord + offset.xOffset(face);
		int y = turtle.yCoord + offset.yOffset(face);
		int z = turtle.zCoord + offset.zOffset(face);
		int meta = turtle.getOppositeDir(face);

		if (!isBlockInWorld(turtle, y))
			return false;

		ItemStack itemStack = turtle.takePlaceableItem(x, y, z, meta);
		System.out.println(itemStack);

		if (itemStack == null)
			return false;

		System.out.println("place: " + offset.xOffset(face) + "/" + offset.yOffset(face) + "/" + offset.zOffset(face) + ": " + itemStack.itemID + "@"
				+ meta);

		Item item = itemStack.getItem();
		if(item == null)
			return false;
		
		if (item.onItemUse(itemStack, getTempPlayer(turtle.worldObj), turtle.worldObj, x, y - 1, z, 1)) {
			if (item instanceof ItemComputer)
				((ItemComputer) item).setupComputerAfterPlacement(itemStack, turtle.worldObj, x, y, z);

			Block block = Block.blocksList[turtle.worldObj.getBlockId(x, y, z)];
			if(block != null)
				mod_CCTurtle.playBlockSound(turtle.worldObj, x + 0.5F, y + 0.5F, z + 0.5F, block);
			return true;
		}

		/*
		 * if (turtle.worldObj.setBlockAndMetadataWithNotify(x, y, z,
		 * itemStack.itemID, item.getMetadata(itemStack.getItemDamage()))) { if
		 * (turtle.worldObj.getBlockId(x, y, z) == itemStack.itemID) {
		 * block.onBlockPlaced(turtle.worldObj, x, y, z, meta);
		 * 
		 * }
		 * 
		 * mod_CCTurtle.playBlockSound(turtle.worldObj, x + 0.5F, y + 0.5F, z +
		 * 0.5F, block); return true; }
		 */

		return false;
	}

	public static boolean dig(TileMyEntityTurtle turtle, Offset3DBase offset, int face) {
		if (!turtle.hasPick())
			return false;

		int x = turtle.xCoord + offset.xOffset(face);
		int y = turtle.yCoord + offset.yOffset(face);
		int z = turtle.zCoord + offset.zOffset(face);
		int meta = turtle.getOppositeDir(face);

		if (!isBlockInWorld(turtle, y))
			return false;

		int i1 = turtle.worldObj.getBlockId(x, y, z);

		if (i1 == 0 || i1 == Block.bedrock.blockID) {
			return false;
		}

		for (ItemStack itemStack : getBlockDropped(i1, turtle.worldObj, x, y, z)) {
			boolean flag = turtle.storeItemStack(itemStack);
			if (!flag)
				turtle.dropStack(itemStack, meta);
		}

		Block block = Block.blocksList[i1];
		turtle.worldObj.playAuxSFX(2001, x, y, z, block.blockID + turtle.worldObj.getBlockMetadata(x, y, z) * 4096);
		turtle.worldObj.setBlockWithNotify(x, y, z, 0);

		return true;
	}

	public static int detect(TileMyEntityTurtle turtle, Offset3DBase offset, int face) {
		int x = turtle.xCoord + offset.xOffset(face);
		int y = turtle.yCoord + offset.yOffset(face);
		int z = turtle.zCoord + offset.zOffset(face);
		int meta = turtle.getOppositeDir(face);

		if (!isBlockInWorld(turtle, y))
			return 0;

		int blockID = turtle.worldObj.getBlockId(x, y, z);

		if (blockID != 0 && blockID != Block.waterStill.blockID && blockID != Block.waterMoving.blockID && blockID != Block.lavaStill.blockID
				&& blockID != Block.lavaMoving.blockID && blockID != Block.fire.blockID && blockID != Block.snow.blockID) {
			Block block = Block.blocksList[blockID];

			if (!block.isBlockReplaceable(turtle.worldObj, x, y, z))
				return blockID;
		}

		return 0;
	}

	public static boolean compare(TileMyEntityTurtle turtle, Offset3DBase offset, int face) {
		int itemID = -1;
		int itemMeta = -1;

		ItemStack[] inventory = turtle.getInventory();
		synchronized (inventory) {
			ItemStack itemStack = inventory[turtle.getSelectedSlot()];

			if (itemStack == null || itemStack.itemID == 0)
				return (detect(turtle, offset, face) != 0);

			Item item = Item.itemsList[itemStack.itemID];

			if (itemStack.itemID > 0 && (item instanceof ItemBlock)) {
				itemID = itemStack.itemID;

				if (item.getHasSubtypes())
					itemMeta = item.getMetadata(itemStack.getItemDamage());
			}
		}

		int x = turtle.xCoord + offset.xOffset(face);
		int y = turtle.yCoord + offset.yOffset(face);
		int z = turtle.zCoord + offset.zOffset(face);

		int blockID = 0;
		int blockMeta = 0;

		if (isBlockInWorld(turtle, y)) {
			blockID = turtle.worldObj.getBlockId(x, y, z);
			blockMeta = turtle.worldObj.getBlockMetadata(x, y, z);
		}

		return itemID == blockID && (itemMeta == -1 || itemMeta == blockMeta);
	}
}
