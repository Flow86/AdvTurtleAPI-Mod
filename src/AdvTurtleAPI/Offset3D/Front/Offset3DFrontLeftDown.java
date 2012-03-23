package net.minecraft.src.AdvTurtleAPI.Offset3D.Front;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontLeftZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DFrontLeftDown extends Offset3DBase {
	public Offset3DFrontLeftDown() {
		super(new OffsetFrontLeftX(), new OffsetDownY(), new OffsetFrontLeftZ());
	}
}
