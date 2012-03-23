package net.minecraft.src.AdvTurtleAPI.Offset.Back;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetBase;


public class OffsetBackLeftZ extends OffsetBase {
	private static final int[] zOffset = { 0, 0, 1, -1, 1, -1 };
	
	public OffsetBackLeftZ() {
		super(zOffset);
	}
}
