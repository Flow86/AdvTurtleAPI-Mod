package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetRightZ;

public class Offset3DRightDown extends Offset3DBase {
	public Offset3DRightDown() {
		super(new OffsetRightX(), new OffsetDownY(), new OffsetRightZ());
	}
}
