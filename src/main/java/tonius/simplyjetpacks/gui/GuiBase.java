package tonius.simplyjetpacks.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public abstract class GuiBase extends GuiContainer
{
	protected ResourceLocation texture;

	public GuiBase(Container container)
	{
		super(container);
	}

	public GuiBase(Container container, ResourceLocation texture)
	{

		super(container);
		this.texture = texture;
	}
}