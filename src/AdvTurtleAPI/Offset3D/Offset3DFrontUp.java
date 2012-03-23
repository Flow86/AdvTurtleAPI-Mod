package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontZ;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetUpY;

public class Offset3DFrontUp extends Offset3DBase {
	Offset3DFrontUp() {
		super(new OffsetFrontX(), new OffsetUpY(), new OffsetFrontZ());
	}
}
