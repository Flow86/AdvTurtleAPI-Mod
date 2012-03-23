package net.minecraft.src.AdvTurtleAPI;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet230ModLoader;
import net.minecraft.src.mod_AdvTurtleAPI;
import net.minecraft.src.mod_CCTurtle;
import net.minecraft.src.mod_ComputerCraft;
import net.minecraft.src.dan200.computer.shared.ItemComputer;
import net.minecraft.src.dan200.computer.shared.NetworkedComputerHelper;
import net.minecraft.src.dan200.computer.shared.WirelessModemPeripheral;
import net.minecraft.src.dan200.turtle.shared.TileEntityTurtle;

public class TileMyEntityTurtle extends TileEntityTurtle {

	private NetworkedComputerHelper m_computer = null;
	private ItemStack[] m_inventory = null;
	private PeripheralAdvTurtleAPI my_peripheral = null;

	public TileMyEntityTurtle() {
		super();
		// System.out.println("TileMyEntityTurtle()");
		hook();
	}

	public TileMyEntityTurtle(int i) {
		super(i);
		// System.out.println("TileMyEntityTurtle(" + i + ")");
		hook();
	}

	public TileMyEntityTurtle(int j, int i) {
		super(j, i);
		// System.out.println("TileMyEntityTurtle(" + j + ", " + i + ")");
		hook();
	}

	private Packet230ModLoader createModemLightPacket() {
		Packet230ModLoader packet230modloader = null;
		Method createModemLightPacket;
		try {
			createModemLightPacket = TileEntityTurtle.class.getDeclaredMethod("createModemLightPacket");
			createModemLightPacket.setAccessible(true);
			packet230modloader = (Packet230ModLoader) createModemLightPacket.invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packet230modloader;
	}

	@Override
	public void updateEntity() {
		boolean destroyed = (Boolean) getSuperField("m_clientState", "destroyed");

		if (m_moved || destroyed)
			return;

		m_computer.update();

		if (!mod_ComputerCraft.isMultiplayerClient()) {
			WirelessModemPeripheral modem = (WirelessModemPeripheral) getSuperField("m_state", "modem");
			if (modem != null && modem.pollChanged()) {
				setSuperField("m_clientState", "modemLight", modem.isActive());

				if (mod_ComputerCraft.isMultiplayerServer()) {
					Packet230ModLoader packet230modloader = createModemLightPacket();
					mod_ComputerCraft.sendToAllPlayers(packet230modloader);
				}
			}

			updateCommands();
		}

		updateAnimation();
	}

	public ItemStack[] getInventory() {
		return m_inventory;
	}

	/**
	 * 
	 */
	private void updateCommands() {
		try {
			Integer animation = (Integer) getSuperField("m_clientState", "animation");
			if (animation != null && animation.intValue() == -1) {
				int cmd = -1;

				LinkedList commandQueue = (LinkedList) getSuperField("m_state", "commandQueue");

				// System.out.println(commandQueue.size());

				boolean base = false;

				Object peek = commandQueue.peek();
				if (peek != null) {
					if (((Integer) peek).intValue() >= mod_AdvTurtleAPI.cmdOffset)
						cmd = ((Integer) commandQueue.remove()).intValue();
					else {
						// System.out.println("Calling base command: " +
						// ((Integer) peek).intValue());
						base = true;
					}
				}

				// System.out.println(commandQueue.size() + " / " +
				// ((LinkedList)getSuperField("m_state",
				// "commandQueue")).size());

				if (base) {
					Method updateCommands;
					try {
						updateCommands = TileEntityTurtle.class.getDeclaredMethod("updateCommands");
						updateCommands.setAccessible(true);
						updateCommands.invoke(this);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
				}

				if (cmd < 0)
					return;

				System.out.println("Calling advturtle command: " + cmd);

				boolean flag = false;

				cmd -= mod_AdvTurtleAPI.cmdOffset;

				switch (cmd) {
				default:
					System.out.println("Unknown command: " + cmd);
					break;

				case 5: {// placeFrontLeftDown
					final int[] xOffsetFLD = { 0, 0, -1, 1, -1, 1 };
					final int[] yOffsetFLD = { 0, 0, -1, -1, -1, -1 };
					final int[] zOffsetFLD = { 0, 0, -1, 1, 1, -1 };

					int dir = (Integer) getSuperField("m_clientState", "dir");
					flag = place(xOffsetFLD[dir], yOffsetFLD[dir], zOffsetFLD[dir], getOppositeDir(dir));

					if (flag)
						startAnimation(4);
					break;
				}
				case 6: {// placeFrontDown
					final int[] xOffsetFD = { 0, 0, 0, 0, -1, 1 };
					final int[] yOffsetFD = { 0, 0, -1, -1, -1, -1 };
					final int[] zOffsetFD = { 0, 0, -1, 1, 0, 0 };

					int dir = (Integer) getSuperField("m_clientState", "dir");
					flag = place(xOffsetFD[dir], yOffsetFD[dir], zOffsetFD[dir], getOppositeDir(dir));

					if (flag)
						startAnimation(4);
					break;
				}
				case 7: {// placeFrontRightDown
					final int[] xOffsetFRD = { 0, 0, 1, -1, -1, 1 };
					final int[] yOffsetFRD = { 0, 0, -1, -1, -1, -1 };
					final int[] zOffsetFRD = { 0, 0, -1, 1, -1, 1 };

					int dir = (Integer) getSuperField("m_clientState", "dir");
					flag = place(xOffsetFRD[dir], yOffsetFRD[dir], zOffsetFRD[dir], getOppositeDir(dir));

					if (flag)
						startAnimation(4);
					break;
				}
				case 8: {// placeFrontLeft
					final int[] xOffsetFL = { 0, 0, -1, 1, -1, 1 };
					final int[] yOffsetFL = { 0, 0, 0, 0, 0, 0 };
					final int[] zOffsetFL = { 0, 0, -1, 1, 1, -1 };

					int dir = (Integer) getSuperField("m_clientState", "dir");
					flag = place(xOffsetFL[dir], yOffsetFL[dir], zOffsetFL[dir], getOppositeDir(dir));

					if (flag)
						startAnimation(4);
					break;
				}
				case 9: {// placeFrontRight
					final int[] xOffsetFR = { 0, 0, 1, -1, -1, 1 };
					final int[] yOffsetFR = { 0, 0, 0, 0, 0, 0 };
					final int[] zOffsetFR = { 0, 0, -1, 1, -1, 1 };

					int dir = (Integer) getSuperField("m_clientState", "dir");
					flag = place(xOffsetFR[dir], yOffsetFR[dir], zOffsetFR[dir], getOppositeDir(dir));

					if (flag)
						startAnimation(4);
					break;
				}
				case 10: {// placeFrontLeftUp
					final int[] xOffsetFLU = { 0, 0, -1, 1, -1, 1 };
					final int[] yOffsetFLU = { 0, 0, 1, 1, 1, 1 };
					final int[] zOffsetFLU = { 0, 0, -1, 1, 1, -1 };

					int dir = (Integer) getSuperField("m_clientState", "dir");
					flag = place(xOffsetFLU[dir], yOffsetFLU[dir], zOffsetFLU[dir], getOppositeDir(dir));

					if (flag)
						startAnimation(4);
					break;
				}
				case 11: {// placeFrontUp
					final int[] xOffsetFU = { 0, 0, 0, 0, -1, 1 };
					final int[] yOffsetFU = { 0, 0, 1, 1, 1, 1 };
					final int[] zOffsetFU = { 0, 0, -1, 1, 0, 0 };

					int dir = (Integer) getSuperField("m_clientState", "dir");
					flag = place(xOffsetFU[dir], yOffsetFU[dir], zOffsetFU[dir], getOppositeDir(dir));

					if (flag)
						startAnimation(4);
					break;
				}
				case 12: {// placeFrontRightUp
					final int[] xOffsetFRU = { 0, 0, 1, -1, -1, 1 };
					final int[] yOffsetFRU = { 0, 0, 1, 1, 1, 1 };
					final int[] zOffsetFRU = { 0, 0, -1, 1, -1, 1 };

					int dir = (Integer) getSuperField("m_clientState", "dir");
					flag = place(xOffsetFRU[dir], yOffsetFRU[dir], zOffsetFRU[dir], getOppositeDir(dir));

					if (flag)
						startAnimation(4);
					break;
				}				
				}

				int commandsProcessed = (Integer) getSuperField("m_state", "commandsProcessed");
				commandsProcessed++;
				setSuperField("m_state", "commandsProcessed", commandsProcessed);

				if (my_peripheral != null)
					my_peripheral.commandProcessed(commandsProcessed, flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setSuperField(String field, String subfield, Object value) {
		Object obj = null;
		try {
			obj = ModLoader.getPrivateValue(TileEntityTurtle.class, this, field);
			// System.out.println("Successfully got "+ field + " @" + obj +
			// " of " + this);
		} catch (Exception e) {
			System.out.println("Getting " + field + " of " + this + " failed!!!");
			e.printStackTrace();
		}

		Field f = null;

		if (obj != null) {
			try {
				f = obj.getClass().getField(subfield);
				f.setAccessible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (f != null) {
			try {
				f.set(obj, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Object getSuperField(String field, String subfield) {
		Object obj = null;
		try {
			obj = ModLoader.getPrivateValue(TileEntityTurtle.class, this, field);
			// System.out.println("Successfully got "+ field + " @" + obj +
			// " of " + this);
		} catch (Exception e) {
			System.out.println("Getting " + field + " of " + this + " failed!!!");
			e.printStackTrace();
		}

		Field f = null;

		if (obj != null) {
			try {
				f = obj.getClass().getField(subfield);
				f.setAccessible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (f != null) {
			try {
				return f.get(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private void startAnimation(int animation) {
		Method startAnimation;
		try {
			startAnimation = TileEntityTurtle.class.getDeclaredMethod("startAnimation", int.class);
			startAnimation.setAccessible(true);
			startAnimation.invoke(this, animation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateAnimation() {
		Method updateAnimation;
		try {
			updateAnimation = TileEntityTurtle.class.getDeclaredMethod("updateAnimation");
			updateAnimation.setAccessible(true);
			updateAnimation.invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hook() {
		if (m_computer == null) {
			try {
				m_computer = (NetworkedComputerHelper) ModLoader.getPrivateValue(TileEntityTurtle.class, this,
						"m_computer");
				my_peripheral = new PeripheralAdvTurtleAPI(this);
				m_computer.addPeripheralAsAPI(my_peripheral);
				// System.out.println("Successfully hooked " + this + " @" +
				// m_computer);
			} catch (Exception e) {
				System.out.println("Hooking " + this + " failed!!!");
				e.printStackTrace();
			}
		}

		if (m_inventory == null) {
			try {
				m_inventory = (ItemStack[]) ModLoader.getPrivateValue(TileEntityTurtle.class, this, "m_inventory");
				// System.out.println("Successfully got inventory @" +
				// m_inventory + " of " + this);
			} catch (Exception e) {
				System.out.println("Getting inventory of " + this + " failed!!!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void transferStateFrom(TileEntityTurtle tileentityturtle) {
		System.out.println("transferStateFrom: " + this + " | " + tileentityturtle);

		super.transferStateFrom(tileentityturtle);

		if (tileentityturtle instanceof TileMyEntityTurtle) {
			m_computer = ((TileMyEntityTurtle) tileentityturtle).m_computer;
			m_inventory = ((TileMyEntityTurtle) tileentityturtle).m_inventory;
			my_peripheral = ((TileMyEntityTurtle) tileentityturtle).my_peripheral;
		}

		hook();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		System.out.println("readFromNBT");
		m_inventory = null;
		hook();
	}

	private int getOppositeDir(int y) {
		Method getOppositeDir;
		try {
			getOppositeDir = TileEntityTurtle.class.getDeclaredMethod("getOppositeDir", int.class);
			getOppositeDir.setAccessible(true);
			y = (Integer) getOppositeDir.invoke(this, new Object[] { y });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return y;
	}

	private ItemStack takePlaceableItem(int x, int y, int z, int m) {
		Method takePlaceableItem;
		ItemStack itemStack = null;
		try {
			takePlaceableItem = TileEntityTurtle.class.getDeclaredMethod("takePlaceableItem", int.class, int.class,
					int.class, int.class);
			takePlaceableItem.setAccessible(true);
			itemStack = (ItemStack) takePlaceableItem.invoke(this, new Object[] { x, y, z, m });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemStack;
	}

	private boolean place(int xOffset, int yOffset, int zOffset, int meta) {
		int x = xCoord + xOffset;
		int y = yCoord + yOffset;
		int z = zCoord + zOffset;

		if (y < 1 || y >= worldObj.getWorldHeight() - 1)
			return false;

		ItemStack itemstack = takePlaceableItem(x, y, z, meta);

		if (itemstack == null)
			return false;

		Item item = Item.itemsList[itemstack.itemID];
		Block block = Block.blocksList[itemstack.itemID];

		// System.out.println("place: " + xOffset + "/" + yOffset + "/" +
		// zOffset + ": " + itemstack.itemID + "@" + meta);

		if (worldObj.setBlockAndMetadataWithNotify(x, y, z, itemstack.itemID,
				item.getMetadata(itemstack.getItemDamage()))) {
			if (worldObj.getBlockId(x, y, z) == itemstack.itemID) {
				block.onBlockPlaced(worldObj, x, y, z, meta);

				if (item instanceof ItemComputer)
					((ItemComputer) item).setupComputerAfterPlacement(itemstack, worldObj, x, y, z);
			}

			mod_CCTurtle.playBlockSound(worldObj, x + 0.5F, y + 0.5F, z + 0.5F, block);
			return true;
		}

		return false;
	}
}
