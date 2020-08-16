package tonius.simplyjetpacks.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public abstract class JetpackGui extends GuiContainer {

    protected ResourceLocation texture;

    public JetpackGui(Container container) {
        super(container);
    }

    public JetpackGui(Container container, ResourceLocation texture) {
        super(container);
        this.texture = texture;
    }
}