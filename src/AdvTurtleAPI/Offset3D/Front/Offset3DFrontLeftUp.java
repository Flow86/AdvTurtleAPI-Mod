package net.minecraft.src.AdvTurtleAPI.Offset3D.Front;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetUpY;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.Front.OffsetFrontLeftZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DFrontLeftUp extends Offset3DBase {
	public Offset3DFrontLeftUp() {
		super(new OffsetFrontLeftX(), new OffsetUpY(), new OffsetFrontLeftZ());
	}
}
