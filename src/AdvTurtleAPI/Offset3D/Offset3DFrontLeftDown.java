package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontLeftZ;

public class Offset3DFrontLeftDown extends Offset3DBase {
	Offset3DFrontLeftDown() {
		super(new OffsetFrontLeftX(), new OffsetDownY(), new OffsetFrontLeftZ());
	}
}
