package net.minecraft.src.AdvTurtleAPI.Offset;

public class OffsetRightZ extends OffsetBase {
	private static final int[] xOffset = { 0, 0, 0, 0, -1, 1 };

	public OffsetRightZ() {
		super(xOffset);
	}
}
