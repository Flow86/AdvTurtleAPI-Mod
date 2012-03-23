package net.minecraft.src.AdvTurtleAPI.Offset3D.Back;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackX;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DBackDown extends Offset3DBase {
	public Offset3DBackDown() {
		super(new OffsetBackX(), new OffsetDownY(), new OffsetBackZ());
	}
}
