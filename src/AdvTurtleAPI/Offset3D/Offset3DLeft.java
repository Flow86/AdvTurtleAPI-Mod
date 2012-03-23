package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetLeftZ;

public class Offset3DLeft extends Offset3DBase {
	public Offset3DLeft() {
		super(new OffsetLeftX(), new OffsetZero(), new OffsetLeftZ());
	}
}
