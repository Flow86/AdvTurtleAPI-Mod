package net.minecraft.src.AdvTurtleAPI.Offset3D;

import net.minecraft.src.AdvTurtleAPI.Offset.OffsetDownY;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontX;
import net.minecraft.src.AdvTurtleAPI.Offset.OffsetFrontZ;

public class Offset3DFrontDown extends Offset3DBase {
	private static final int[] xOffset = { 0, 0, 0, 0, -1, 1 };
	private static final int[] yOffset = { 0, 0, -1, -1, -1, -1 };
	private static final int[] zOffset = { 0, 0, -1, 1, 0, 0 };

	Offset3DFrontDown() {
		super(new OffsetFrontX(), new OffsetDownY(), new OffsetFrontZ());
	}
}
