package net.minecraft.src.AdvTurtleAPI.Offset3D.Back;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetUpY;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackLeftX;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackLeftZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DBackLeftUp extends Offset3DBase {
	public Offset3DBackLeftUp() {
		super(new OffsetBackLeftX(), new OffsetUpY(), new OffsetBackLeftZ());
	}
}
