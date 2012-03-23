package net.minecraft.src.AdvTurtleAPI.Offset3D.Front;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetUpY;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontRightZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DFrontRightUp extends Offset3DBase {
	public Offset3DFrontRightUp() {
		super(new OffsetFrontRightX(), new OffsetUpY(), new OffsetFrontRightZ());
	}
}
