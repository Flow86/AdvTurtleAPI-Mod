package net.minecraft.src.AdvTurtleAPI.Offset3D.Back;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackRightZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DBackRightDown extends Offset3DBase {
	public Offset3DBackRightDown() {
		super(new OffsetBackRightX(), new OffsetDownY(), new OffsetBackRightZ());
	}
}
