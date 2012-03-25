package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontZ;

public class Offset3DFrontDown extends Offset3DBase {
	Offset3DFrontDown() {
		super(new OffsetFrontX(), new OffsetDownY(), new OffsetFrontZ());
	}
}
