package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontRightZ;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;

public class Offset3DFrontRight extends Offset3DBase {
	Offset3DFrontRight() {
		super(new OffsetFrontRightX(), new OffsetZero(), new OffsetFrontRightZ());
	}
}
