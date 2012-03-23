package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetBase;

public abstract class Offset3DBase {

	protected final OffsetBase xOffset_;
	protected final OffsetBase yOffset_;
	protected final OffsetBase zOffset_;

	protected Offset3DBase(OffsetBase xOffset, OffsetBase yOffset, OffsetBase zOffset) {
		xOffset_ = xOffset;
		yOffset_ = yOffset;
		zOffset_ = zOffset;
	}

	public int xOffset(int face) {
		return xOffset_.offset(face);
	}

	public int yOffset(int face) {
		return yOffset_.offset(face);
	}

	public int zOffset(int face) {
		return zOffset_.offset(face);
	}
}
