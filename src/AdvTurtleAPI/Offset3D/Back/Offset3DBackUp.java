package net.minecraft.src.AdvTurtleAPI.Offset3D.Back;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetUpY;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackX;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DBackUp extends Offset3DBase {
	public Offset3DBackUp() {
		super(new OffsetBackX(), new OffsetUpY(), new OffsetBackZ());
	}
}
