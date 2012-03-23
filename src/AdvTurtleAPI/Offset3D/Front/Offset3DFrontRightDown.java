package net.minecraft.src.AdvTurtleAPI.Offset3D.Front;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontRightZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DFrontRightDown extends Offset3DBase {
	public Offset3DFrontRightDown() {
		super(new OffsetFrontRightX(), new OffsetDownY(), new OffsetFrontRightZ());
	}
}
