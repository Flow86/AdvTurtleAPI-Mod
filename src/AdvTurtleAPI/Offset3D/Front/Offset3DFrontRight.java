package net.minecraft.src.AdvTurtleAPI.Offset3D.Front;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontRightZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DFrontRight extends Offset3DBase {
	public Offset3DFrontRight() {
		super(new OffsetFrontRightX(), new OffsetZero(), new OffsetFrontRightZ());
	}
}
