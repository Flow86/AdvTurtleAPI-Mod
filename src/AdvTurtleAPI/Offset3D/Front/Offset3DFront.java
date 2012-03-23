package net.minecraft.src.AdvTurtleAPI.Offset3D.Front;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontX;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DFront extends Offset3DBase {
	public Offset3DFront() {
		super(new OffsetFrontX(), new OffsetZero(), new OffsetFrontZ());
	}
}
