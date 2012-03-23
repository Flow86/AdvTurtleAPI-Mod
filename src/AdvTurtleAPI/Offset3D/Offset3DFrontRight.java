package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetEmpty;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontRightZ;

public class Offset3DFrontRight extends Offset3DBase {
	Offset3DFrontRight() {
		super(new OffsetFrontRightX(), new OffsetEmpty(), new OffsetFrontRightZ());
	}
}
