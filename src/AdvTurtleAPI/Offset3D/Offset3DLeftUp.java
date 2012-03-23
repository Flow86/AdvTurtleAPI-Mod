package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetLeftZ;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetUpY;

public class Offset3DLeftUp extends Offset3DBase {
	public Offset3DLeftUp() {
		super(new OffsetLeftX(), new OffsetUpY(), new OffsetLeftZ());
	}
}
