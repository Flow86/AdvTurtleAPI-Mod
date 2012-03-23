package net.minecraft.src.AdvTurtleAPI.Offset3D.Front;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontX;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DFrontDown extends Offset3DBase {
	public Offset3DFrontDown() {
		super(new OffsetFrontX(), new OffsetDownY(), new OffsetFrontZ());
	}
}
