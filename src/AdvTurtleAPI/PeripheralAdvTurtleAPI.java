package net.minecraft.src.AdvTurtleAPI;

import net.minecraft.src.ItemStack;
import net.minecraft.src.mod_AdvTurtleAPI;
import net.minecraft.src.dan200.computer.api.IComputerAccess;
import net.minecraft.src.dan200.computer.api.IPeripheral;
import net.minecraft.src.dan200.turtle.shared.ITurtleListener;

public class PeripheralAdvTurtleAPI implements IPeripheral, ITurtleListener {

	TileMyEntityTurtle my_turtle;
	ItemList my_list;
	IComputerAccess my_computer;

	public PeripheralAdvTurtleAPI(TileMyEntityTurtle turtle) {
		my_turtle = turtle;
		my_list = new ItemList();

		ItemStack valvepipe = (new ItemList()).findBlock("valve pipe");
	}

	private int parseInteger(Object aobj[], int param) throws Exception {
		if (aobj.length < param + 1 || !(aobj[param] instanceof Double))
			throw new Exception("Expected number");

		int i = (int) ((Double) aobj[param]).doubleValue();

		return i;
	}

	private String parseString(Object aobj[], int param) throws Exception {
		if (aobj.length < param + 1 || !(aobj[param] instanceof String))
			throw new Exception("Expected string");

		return ((String) aobj[param]).trim();
	}

	private int findSlotForBlock(ItemStack itemStack) {
		ItemStack[] inventory = my_turtle.getInventory();

		for (int i = 0; i < inventory.length; ++i) {
			if (inventory[i] != null && my_list.getName(inventory[i]).compareTo(my_list.getName(itemStack)) == 0)
				return i + 1;
		}

		return 0;
	}

	private Object[] findBlockID(Object[] aobj) throws Exception {
		int blockID = parseInteger(aobj, 0);
		if (blockID < 1 || blockID > 65535)
			throw new Exception((new StringBuilder()).append("block id ").append(blockID).append(" out of range").toString());

		int damageID = 0;
		try {
			damageID = parseInteger(aobj, 1);
		} catch (Exception e) {
		}

		int slot = findSlotForBlock(new ItemStack(blockID, 1, damageID));
		if (slot == 0)
			return null;

		return new Object[] { slot };

	}

	private Object[] findBlock(Object[] aobj) throws Exception {
		String blockName = parseString(aobj, 0);

		System.out.println("searching for " + blockName);

		ItemStack itemStack = my_list.findBlock(blockName);

		System.out.println("Found: " + itemStack);

		if (itemStack == null)
			return null;

		int slot = findSlotForBlock(itemStack);
		if (slot == 0)
			return null;

		return new Object[] { slot };

	}

	private Object[] tryCommand(int cmd) {
		int j = my_turtle.issueCommand(mod_AdvTurtleAPI.cmdOffset + cmd);
		return (new Object[] { Integer.valueOf(j) });
	}

	@Override
	public Object[] callMethod(IComputerAccess icomputeraccess, int cmd, Object[] aobj) throws Exception {

		switch (cmd) {
		case 0: // X
			return new Object[] { my_turtle.xCoord };
		case 1: // Y
			return new Object[] { my_turtle.yCoord };
		case 2: // Z
			return new Object[] { my_turtle.zCoord };
		case 3: // findBlockID
			return findBlockID(aobj);
		case 4: // findBlock
			return findBlock(aobj);
		default: // placeFrontLeftDown, etc
			return tryCommand(cmd);
		}

		// return null;
	}

	@Override
	public boolean canAttachToSide(int i) {
		return true;
	}

	@Override
	public void attach(IComputerAccess icomputeraccess, String s) {
		System.out.println("attach " + icomputeraccess);

		my_computer = icomputeraccess;
		icomputeraccess.mountFixedDir("rom/apis/advturtle", "mods/AdvTurtleAPI/lua/advturtle.lua", true);
	}

	@Override
	public void detach(IComputerAccess icomputeraccess) {
		System.out.println("detach " + icomputeraccess);
		icomputeraccess.unmount("rom/apis/advturtle");
		my_computer = null;
	}

	@Override
	public String[] getMethodNames() {
		//@formatter:off
		return new String[] { 
				"x", "y", "z", 
				"findBlockID", "findBlock", 
				
				"placeFrontLeftDown", "placeFrontDown", "placeFrontRightDown", 
				"placeFrontLeft", "placeFrontRight", 
				"placeFrontLeftUp", "placeFrontUp", "placeFrontRightUp",
				
				"placeLeftDown", "placeRightDown",
				"placeLeft", "placeRight",
				"placeLeftUp", "placeRightUp", 
				
				"placeBackLeftDown", "placeBackDown", "placeBackRightDown", 
				"placeBackLeft", "placeBack", "placeBackRight", 
				"placeBackLeftUp", "placeBackUp", "placeBackRightUp",

				"digFrontLeftDown", "digFrontDown", "digFrontRightDown", 
				"digFrontLeft", "digFrontRight", 
				"digFrontLeftUp", "digFrontUp", "digFrontRightUp",
				
				"digLeftDown", "digRightDown",
				"digLeft", "digRight",
				"digLeftUp", "digRightUp", 
				
				"digBackLeftDown", "digBackDown", "digBackRightDown", 
				"digBackLeft", "digBack", "digBackRight", 
				"digBackLeftUp", "digBackUp", "digBackRightUp",
		
		};
		//@formatter:on
	}

	@Override
	public String getType() {
		return "advturtle";
	}

	@Override
	public void commandProcessed(int cmd, boolean flag) {
		if (my_computer != null) {
			my_computer.queueEvent("advturtle_response", new Object[] { Integer.valueOf(cmd), Boolean.valueOf(flag) });
		}
	}
}
