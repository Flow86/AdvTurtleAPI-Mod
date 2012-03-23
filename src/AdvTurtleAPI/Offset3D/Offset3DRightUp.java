package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetRightX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetRightZ;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetUpY;

public class Offset3DRightUp extends Offset3DBase {
	public Offset3DRightUp() {
		super(new OffsetRightX(), new OffsetUpY(), new OffsetRightZ());
	}
}
