package net.minecraft.src.AdvTurtleAPI.Offset;


public class OffsetFrontZ extends OffsetBase {
	private static final int[] zOffset = { 0, 0, -1, 1, 0, 0 };
	
	public OffsetFrontZ() {
		super(zOffset);
	}
}
