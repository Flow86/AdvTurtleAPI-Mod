package net.minecraft.src.AdvTurtleAPI.Offset.Back;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetBase;


public class OffsetBackRightX extends OffsetBase {
	private static final int[] xOffset = { 0, 0, 1, -1, 1, -1 };
	
	public OffsetBackRightX() {
		super(xOffset);
	}
}
