package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontLeftZ;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetUpY;

public class Offset3DFrontLeftUp extends Offset3DBase {
	Offset3DFrontLeftUp() {
		super(new OffsetFrontLeftX(), new OffsetUpY(), new OffsetFrontLeftZ());
	}
}
