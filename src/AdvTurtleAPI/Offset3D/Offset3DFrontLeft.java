package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetEmpty;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontLeftZ;

public class Offset3DFrontLeft extends Offset3DBase {
	Offset3DFrontLeft() {
		super(new OffsetFrontLeftX(), new OffsetEmpty(), new OffsetFrontLeftZ());
	}
}
