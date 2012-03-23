package net.minecraft.src.AdvTurtleAPI.Offset;


public class OffsetFrontLeftZ extends OffsetBase {
	private static final int[] zOffset = { 0, 0, -1, 1, 1, -1 };
	
	public OffsetFrontLeftZ() {
		super(zOffset);
	}
}
