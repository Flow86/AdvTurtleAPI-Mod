package net.minecraft.src.AdvTurtleAPI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.EntityList;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ItemList {
	public static final int[] potionValues = new int[] { 0, 16, 32, 64, 8192, 8193, 8194, 8195, 8196, 8197, 8200, 8201, 8202, 8204, 8225, 8226, 8227, 8228,
			8229, 8232, 8233, 8234, 8236, 8257, 8258, 8259, 8260, 8261, 8264, 8265, 8266, 8268, 16384, 16385, 16386, 16387, 16388, 16389, 16392, 16393, 16394,
			16396, 16417, 16418, 16419, 16420, 16421, 16424, 16425, 16426, 16428, 16449, 16450, 16451, 16452, 16453, 16456, 16457, 16458, 16460 };

	public List<ItemStack> itemsList = new ArrayList<ItemStack>();

	private void loadItems() {
		itemsList.clear();

		/*
		 * try { Class rpb = Class.forName("RedPowerBase"); Block blockMicro =
		 * (Block) rpb.getDeclaredField("blockMicro").get((Object) null); Field
		 * bO = Block.class.getDeclaredField("bO");
		 * TMIItemInfo.setMaxDamageException(bO.getInt(blockMicro), 32000); }
		 * catch (ClassNotFoundException e) { } catch (NoClassDefFoundError e) {
		 * } catch (Exception e) { System.out.println(e); }
		 */

		for (int i = 0; i < Item.itemsList.length; ++i) {
			Item item = Item.itemsList[i];

			if (item != null) {
				if (item.shiftedIndex == 383) {
					Iterator egg = EntityList.entityEggs.keySet().iterator();

					while (egg.hasNext()) {
						Object theEgg = egg.next();
						itemsList.add(new ItemStack(383, 64, ((Integer) theEgg).intValue()));
					}

					continue;
				}

				HashSet duplicates = new HashSet();

				for (int j = 0; j <= 15; ++j) {

					ItemStack itemStack = new ItemStack(item, item.getItemStackLimit(), j);

					try {
						int iconIndex = item.getIconIndex(itemStack);
						String name = item.getItemNameIS(itemStack);

						if (itemStack.getItem().getItemDisplayName(itemStack).equals("Unnamed")) {
							if (j == 0)
								break;
						} else {
							if (item.shiftedIndex < Block.blocksList.length && Block.blocksList[item.shiftedIndex] != null) {
								try {
									Block.blocksList[item.shiftedIndex].getBlockTextureFromSideAndMetadata(1, j);
								} catch (Exception e) {
									continue;
								}
							}

							String nameWithIndex = name + "@" + iconIndex;

							if (!duplicates.contains(nameWithIndex)) {
								itemsList.add(itemStack);
								duplicates.add(nameWithIndex);
							}
						}
					} catch (NullPointerException e) {
					} catch (IndexOutOfBoundsException e) {
					}
				}
			}
		}

		for (int i = 0; i < potionValues.length; ++i) {
			ItemStack itemStack = new ItemStack(373, 64, potionValues[i]);
			itemsList.add(itemStack);
		}
	}
	
	public ItemStack findBlock(String blockName) {
		if (itemsList.isEmpty())
			loadItems();

		for (ItemStack itemStack : itemsList) {
			String name = getName(itemStack);
			//System.out.println(name + "@" + itemStack.itemID + ":" + itemStack.getItemDamage() + " vs " + blockName);
			if (name.compareToIgnoreCase(blockName) == 0)
				return itemStack;
		}

		return null;
	}

	public String getName(ItemStack itemStack) {
		String name = itemStack.getItem().getItemDisplayName(itemStack).trim();
		if(name.isEmpty())
			name = itemStack.getItem().getLocalItemName(itemStack).trim();
		return name;
	}
}
