package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontRightZ;

public class Offset3DFrontRightDown extends Offset3DBase {
	Offset3DFrontRightDown() {
		super(new OffsetFrontRightX(), new OffsetDownY(), new OffsetFrontRightZ());
	}
}
