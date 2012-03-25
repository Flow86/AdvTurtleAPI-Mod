package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontLeftZ;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;

public class Offset3DFrontLeft extends Offset3DBase {
	Offset3DFrontLeft() {
		super(new OffsetFrontLeftX(), new OffsetZero(), new OffsetFrontLeftZ());
	}
}
