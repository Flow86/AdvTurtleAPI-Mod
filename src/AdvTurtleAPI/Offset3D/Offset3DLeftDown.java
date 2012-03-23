package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetLeftZ;

public class Offset3DLeftDown extends Offset3DBase {
	public Offset3DLeftDown() {
		super(new OffsetLeftX(), new OffsetDownY(), new OffsetLeftZ());
	}
}
