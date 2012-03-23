package net.minecraft.src.AdvTurtleAPI.Offset;

public abstract class OffsetBase {
	private final int[] offset_;

	public OffsetBase(int[] offset) {
		offset_ = offset;
	}

	public int offset(int face) {
		return offset_[face];
	}
}
