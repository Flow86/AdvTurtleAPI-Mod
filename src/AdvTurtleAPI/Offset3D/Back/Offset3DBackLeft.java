package net.minecraft.src.AdvTurtleAPI.Offset3D.Back;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackLeftZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DBackLeft extends Offset3DBase {
	public Offset3DBackLeft() {
		super(new OffsetBackLeftX(), new OffsetZero(), new OffsetBackLeftZ());
	}
}
