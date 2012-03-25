package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;

public class Offset3DDown extends Offset3DBase {
	public Offset3DDown() {
		super(new OffsetZero(), new OffsetDownY(), new OffsetZero());
	}
}
