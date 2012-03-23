package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetRightZ;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;

public class Offset3DRight extends Offset3DBase {
	public Offset3DRight() {
		super(new OffsetRightX(), new OffsetZero(), new OffsetRightZ());
	}
}
