package net.minecraft.src.AdvTurtleAPI.Offset3D.Back;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackLeftZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DBackLeftDown extends Offset3DBase {
	public Offset3DBackLeftDown() {
		super(new OffsetBackLeftX(), new OffsetDownY(), new OffsetBackLeftZ());
	}
}
