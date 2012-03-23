package net.minecraft.src.AdvTurtleAPI.Offset;


public class OffsetFrontRightZ extends OffsetBase {
	private static final int[] zOffset = { 0, 0, -1, 1, -1, 1 };
	
	public OffsetFrontRightZ() {
		super(zOffset);
	}
}
