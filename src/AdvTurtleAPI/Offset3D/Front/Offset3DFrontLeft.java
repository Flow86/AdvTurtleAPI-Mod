package net.minecraft.src.AdvTurtleAPI.Offset3D.Front;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontLeftZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DFrontLeft extends Offset3DBase {
	public Offset3DFrontLeft() {
		super(new OffsetFrontLeftX(), new OffsetZero(), new OffsetFrontLeftZ());
	}
}
