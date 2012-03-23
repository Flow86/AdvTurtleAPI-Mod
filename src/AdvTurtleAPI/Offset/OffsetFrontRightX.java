package net.minecraft.src.AdvTurtleAPI.Offset;


public class OffsetFrontRightX extends OffsetBase {
	private static final int[] xOffset = { 0, 0, 1, -1, -1, 1 };
	
	public OffsetFrontRightX() {
		super(xOffset);
	}
}
