package net.minecraft.src.AdvTurtleAPI.Offset;

public class OffsetLeftZ extends OffsetBase {
	private static final int[] zOffset = { 0, 0, 0, 0, 1, -1 };

	public OffsetLeftZ() {
		super(zOffset);
	}
}
