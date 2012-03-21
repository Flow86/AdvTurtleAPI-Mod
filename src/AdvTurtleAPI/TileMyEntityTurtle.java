package net.minecraft.src.AdvTurtleAPI;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.dan200.computer.api.IComputerAccess;
import net.minecraft.src.dan200.computer.shared.NetworkedComputerHelper;
import net.minecraft.src.dan200.turtle.shared.TileEntityTurtle;

public class TileMyEntityTurtle extends TileEntityTurtle {

	private NetworkedComputerHelper m_computer = null;
	private ItemStack[] m_inventory = null;

	public TileMyEntityTurtle() {
		super();
		hook();
	}

	public TileMyEntityTurtle(int i) {
		super(i);
		hook();
	}

	public TileMyEntityTurtle(int j, int i) {
		super(j, i);
		hook();
	}
	
	public ItemStack[] getInventory() {
		return m_inventory;
	}

	public void hook() {
		if (m_computer == null) {
			try {
				m_computer = (NetworkedComputerHelper) ModLoader
						.getPrivateValue(TileEntityTurtle.class, this,
								"m_computer");
				m_computer.addPeripheralAsAPI(new PeripheralAdvTurtleAPI(this));
				System.out.println("Successfully hooked " + this + " @" + m_computer);
			} catch (Exception e) {
				System.out.println("Hooking " + this + " failed!!!");
				e.printStackTrace();
			}
		}

		try {
			m_inventory = (ItemStack[]) ModLoader.getPrivateValue(
					TileEntityTurtle.class, this, "m_inventory");
			System.out.println("Successfully got inventory @" + m_inventory + " of " + this);
		} catch (Exception e) {
			System.out.println("Getting inventory of " + this + " failed!!!");
			e.printStackTrace();
		}
	}

	@Override
	public void transferStateFrom(TileEntityTurtle tileentityturtle) {
		super.transferStateFrom(tileentityturtle);
		m_computer = null;
		hook();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		hook();
	}
}
