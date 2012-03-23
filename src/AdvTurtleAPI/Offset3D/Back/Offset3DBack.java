package net.minecraft.src.AdvTurtleAPI.Offset3D.Back;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetZero;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackX;
import net.minecraft.src.AdvTurtleAPI.Offset.Back.OffsetBackZ;
import net.minecraft.src.AdvTurtleAPI.Offset3D.Offset3DBase;

public class Offset3DBack extends Offset3DBase {
	public Offset3DBack() {
		super(new OffsetBackX(), new OffsetZero(), new OffsetBackZ());
	}
}
