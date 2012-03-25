package net.minecraft.src.AdvTurtleAPI;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;

import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet230ModLoader;
import net.minecraft.src.mod_AdvTurtleAPI;
import net.minecraft.src.mod_ComputerCraft;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DDown;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DLeft;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DLeftDown;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DLeftUp;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DRight;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DRightDown;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DRightUp;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DUp;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Back.Offset3DBack;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Back.Offset3DBackDown;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Back.Offset3DBackLeft;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Back.Offset3DBackLeftDown;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Back.Offset3DBackLeftUp;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Back.Offset3DBackRight;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Back.Offset3DBackRightDown;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Back.Offset3DBackRightUp;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Back.Offset3DBackUp;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Front.Offset3DFront;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Front.Offset3DFrontDown;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Front.Offset3DFrontLeft;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Front.Offset3DFrontLeftDown;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Front.Offset3DFrontLeftUp;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Front.Offset3DFrontRight;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Front.Offset3DFrontRightDown;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Front.Offset3DFrontRightUp;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Front.Offset3DFrontUp;
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

	public synchronized ItemStack[] getInventory() {
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

				cmd -= mod_AdvTurtleAPI.cmdOffset;

				System.out.println("Calling advturtle command: " + cmd + " = " + my_peripheral.getMethodNames()[cmd]);

				Object flag = false;

				//@formatter:off
				final Offset3DBase[] offsets = {
						// front
						new Offset3DFrontLeftDown(),
						new Offset3DFrontDown(),
						new Offset3DFrontRightDown(),
						new Offset3DFrontLeft(),
						new Offset3DFront(), // this is the same as "turtle.place"
						new Offset3DFrontRight(),
						new Offset3DFrontLeftUp(),
						new Offset3DFrontUp(),
						new Offset3DFrontRightUp(),

						// center
						new Offset3DLeftDown(),
						new Offset3DDown(), // this is the same as "turtle.placeDown"
						new Offset3DRightDown(),
						new Offset3DLeft(),
						//null,               // here is the turtle
						new Offset3DRight(),
						new Offset3DLeftUp(),
						new Offset3DUp(), // this is the same as "turtle.placeUp"
						new Offset3DRightUp(),

						// back
						new Offset3DBackLeftDown(),
						new Offset3DBackDown(),
						new Offset3DBackRightDown(),
						new Offset3DBackLeft(),
						new Offset3DBack(),
						new Offset3DBackRight(),
						new Offset3DBackLeftUp(),
						new Offset3DBackUp(),
						new Offset3DBackRightUp()
				};
				//@formatter:on

				// place
				final int offset = 6;
				// dig
				final int offset2 = offset + offsets.length;
				// compare
				final int offset3 = offset2 + offsets.length;
				// detect
				final int offset4 = offset3 + offsets.length;

				int face = (Integer) getSuperField("m_clientState", "dir");

				// place
				if (cmd >= offset && cmd < offset2) {
					System.out.println("Offset:" + offset + " / " + (cmd - offset));
					flag = APIFunctions.place(this, offsets[cmd - offset], face);
					if ((Boolean) flag)
						startAnimation(4);
				}

				// dig
				else if (cmd >= offset2 && cmd < offset2 + offsets.length) {
					System.out.println("Offset2:" + offset2 + " / " + (cmd - offset2));
					flag = APIFunctions.dig(this, offsets[cmd - offset2], face);
					if ((Boolean) flag)
						startAnimation(3);
				}

				// compare
				else if (cmd >= offset3 && cmd < offset3 + offsets.length) {
					System.out.println("Offset3:" + offset3 + " / " + (cmd - offset3));
					flag = APIFunctions.compare(this, offsets[cmd - offset3], face);
				}

				// detect
				else if (cmd >= offset4 && cmd < offset4 + offsets.length) {
					System.out.println("Offset4:" + offset4 + " / " + (cmd - offset4));
					flag = APIFunctions.detect(this, offsets[cmd - offset4], face);
					if((Integer)flag == 0)
						flag = false;
				}

				else {
					System.out.println("Unknown command: " + cmd);
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

	public Object getSuperField(String field, String subfield) {
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

	public void startAnimation(int animation) {
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

	public boolean storeItemStack(ItemStack itemStack) {
		Method storeItemStack;
		try {
			storeItemStack = TileEntityTurtle.class.getDeclaredMethod("storeItemStack", ItemStack.class);
			storeItemStack.setAccessible(true);
			return (Boolean) storeItemStack.invoke(this, new Object[] { itemStack });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void dropStack(ItemStack itemStack, int meta) {
		Method dropStack;
		try {
			dropStack = TileEntityTurtle.class.getDeclaredMethod("dropStack", ItemStack.class, int.class);
			dropStack.setAccessible(true);
			dropStack.invoke(this, new Object[] { itemStack, meta });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hook() {
		if (m_computer == null) {
			try {
				m_computer = (NetworkedComputerHelper) ModLoader.getPrivateValue(TileEntityTurtle.class, this, "m_computer");
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
			if(my_peripheral != null)
				my_peripheral.setTurtle(this);
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

	public int getOppositeDir(int y) {
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

	public ItemStack takePlaceableItem(int x, int y, int z, int m) {
		boolean flag = false;

		synchronized (m_inventory) {
			for (int i = 0; i < 9; i++) {
				int slot = (getSelectedSlot() + i) % 9;

				if (m_inventory[slot] != null && m_inventory[slot].stackSize <= 0) {
					m_inventory[slot] = null;
					flag = true;
				}

				if (m_inventory[slot] == null)
					continue;

				int itemID = m_inventory[slot].itemID;

				if (itemID <= 0)
					continue;

				if(!worldObj.canBlockBePlacedAt(itemID, x, y, z, false, m))
					continue;
				
				/*
				 * Item item = Item.itemsList[itemID];
				 * 
				 * if (!(item instanceof ItemBlock) ||
				 * !worldObj.canBlockBePlacedAt(itemID, x, y, z, false, m))
				 * continue;
				 */

				ItemStack itemStack = null;
				if (m_inventory[slot].stackSize == 1) {
					itemStack = m_inventory[slot];
					m_inventory[slot] = null;
				} else
					itemStack = m_inventory[slot].splitStack(1);

				onInventoryChanged();
				return itemStack;
			}
		}

		if (flag)
			onInventoryChanged();

		return null;
	}
}
