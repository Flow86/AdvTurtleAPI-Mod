package net.minecraft.src.AdvTurtleAPI.Offset;


public class OffsetFrontLeftX extends OffsetBase {
	private static final int[] xOffset = { 0, 0, -1, 1, -1, 1 };
	
	public OffsetFrontLeftX() {
		super(xOffset);
	}
}
