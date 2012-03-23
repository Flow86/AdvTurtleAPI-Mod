package net.minecraft.src.AdvTurtleAPI.Offset.Front;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetBase;


public class OffsetFrontRightX extends OffsetBase {
	private static final int[] xOffset = { 0, 0, 1, -1, -1, 1 };
	
	public OffsetFrontRightX() {
		super(xOffset);
	}
}
