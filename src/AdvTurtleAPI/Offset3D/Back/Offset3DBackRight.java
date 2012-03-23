package net.minecraft.src.AdvTurtleAPI.Offset3D.Back;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackRightZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DBackRight extends Offset3DBase {
	public Offset3DBackRight() {
		super(new OffsetBackRightX(), new OffsetZero(), new OffsetBackRightZ());
	}
}
