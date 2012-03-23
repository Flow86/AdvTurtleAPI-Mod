package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontRightZ;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetUpY;

public class Offset3DFrontRightUp extends Offset3DBase {
	Offset3DFrontRightUp() {
		super(new OffsetFrontRightX(), new OffsetUpY(), new OffsetFrontRightZ());
	}
}
