package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetUpY;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;

public class Offset3DUp extends Offset3DBase {
	public Offset3DUp() {
		super(new OffsetZero(), new OffsetUpY(), new OffsetZero());
	}
}
