package us.ichun.mods.tabula.gui.window.element;

import ichun.client.render.RendererHelper;
import org.lwjgl.opengl.GL11;
import us.ichun.mods.tabula.gui.Theme;
import us.ichun.mods.tabula.gui.window.Window;

public class ElementMinimize extends Element
{
    public ElementMinimize(Window window, int x, int y, int w, int h, int id)
    {
        super(window, x, y, w, h, id, true);
    }

    @Override
    public void draw(int mouseX, int mouseY, boolean hover)
    {
        if(parent.docked >= 0)
        {
            return;
        }
        if(hover)
        {
            RendererHelper.drawColourOnScreen(Theme.getAsHex(Theme.font), 255, getPosX() - 0.5D, getPosY(), width, 1, 0);
            RendererHelper.drawColourOnScreen(Theme.getAsHex(Theme.font), 255, getPosX() - 0.5D, getPosY(), 1, height, 0);
            RendererHelper.drawColourOnScreen(Theme.getAsHex(Theme.font), 255, getPosX() - 0.5D, getPosY() + height - 1, width, 1, 0);
            RendererHelper.drawColourOnScreen(Theme.getAsHex(Theme.font), 255, getPosX() + width - 1  - 0.5D, getPosY(), 1, height, 0);
        }
        GL11.glPushMatrix();
        float scale = 2F;
        GL11.glScalef(scale, scale, scale);
        if(parent.minimized)
        {
            parent.workspace.getFontRenderer().drawString("\u25BC", (int)((float)(getPosX() + 2) / scale), (int)((float)(getPosY() - 2) / scale), Theme.getAsHex(Theme.font), false); //down arrow
        }
        else
        {
            parent.workspace.getFontRenderer().drawString("\u25B2", (int)((float)(getPosX() + 2) / scale), (int)((float)(getPosY() - 2) / scale), Theme.getAsHex(Theme.font), false); //up arrow
        }
        GL11.glPopMatrix();
    }

    @Override
    public void resized()
    {
        posX = parent.width - 13;
        posY = 2;
    }

    @Override
    public String tooltip()
    {
        if(parent.docked < 0)
        {
            if(!parent.minimized)
            {
                return "element.minimize";
            }
            else
            {
                return "element.expand";
            }
        }
        return null;
    }

    @Override
    public boolean onClick(int mouseX, int mouseY, int id)
    {
        if(id == 0 && parent.docked < 0)
        {
            //TODO handle when if docked and is the last tab not minimized.
            parent.toggleMinimize();
        }
        return true;
    }
}
