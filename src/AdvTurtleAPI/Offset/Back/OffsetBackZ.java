package net.minecraft.src.AdvTurtleAPI.Offset.Back;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetBase;


public class OffsetBackZ extends OffsetBase {
	private static final int[] zOffset = { 0, 0, 1, -1, 0, 0 };
	
	public OffsetBackZ() {
		super(zOffset);
	}
}
