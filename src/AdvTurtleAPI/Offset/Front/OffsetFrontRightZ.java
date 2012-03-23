package net.minecraft.src.AdvTurtleAPI.Offset.Front;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetBase;


public class OffsetFrontRightZ extends OffsetBase {
	private static final int[] zOffset = { 0, 0, -1, 1, -1, 1 };
	
	public OffsetFrontRightZ() {
		super(zOffset);
	}
}
