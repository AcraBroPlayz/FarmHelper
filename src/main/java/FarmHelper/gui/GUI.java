package FarmHelper.gui;

import FarmHelper.FarmHelper;
import FarmHelper.config.Config;
import FarmHelper.config.CropEnum;
import FarmHelper.config.FarmEnum;
import FarmHelper.gui.buttons.GuiBetterButton;
import FarmHelper.gui.buttons.GuiCustomButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.awt.*;
import java.io.IOException;

public class GUI extends GuiScreen{


    int buttonWidth = 125;
    int buttonHeight = 45;

    private static final ResourceLocation quarterI = new ResourceLocation(FarmHelper.MODID, "textures/gui/a.png");
    private static final ResourceLocation quarterII = new ResourceLocation(FarmHelper.MODID, "textures/gui/b.png");
    private static final ResourceLocation quarterIII = new ResourceLocation(FarmHelper.MODID, "textures/gui/c.png");
    private static final ResourceLocation quarterIV = new ResourceLocation(FarmHelper.MODID, "textures/gui/d.png");

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {



    }
    @Override
    public void initGui() {
        super.initGui();


        this.buttonList.add(new GuiCustomButton(100, this.width/2,  this.height/2 - 40, 0,0,  quarterI));
        this.buttonList.add(new GuiCustomButton(1, this.width/2 - 100 ,  this.height / 2 - 140, 100, 100, quarterI));
        this.buttonList.add(new GuiCustomButton(2, this.width/2,  this.height / 2 - 140, 100,100,  quarterII));
        this.buttonList.add(new GuiCustomButton(3, this.width/2 - 100 ,  this.height / 2 - 40, 100,100,  quarterIII));
        this.buttonList.add(new GuiCustomButton(4, this.width/2 ,  this.height / 2 - 40, 100,100,  quarterIV));
        this.buttonList.add(new GuiBetterButton(6, this.width / 2 - buttonWidth / 2, this.height / 2 - buttonHeight / 2 + 130, buttonWidth, buttonHeight, "Settings"));

        GuiCustomButton temp = (GuiCustomButton) this.buttonList.get(Config.CropType.ordinal());
        temp.select();

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(0, 0, this.width, this.height, new Color(0, 0, 0, 225).getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }

    void deactivateOthers(int currentButtonIndex){
        for (int i = 0; i < 4; i++){
            if(i != currentButtonIndex) {
                GuiCustomButton others = (GuiCustomButton) this.buttonList.get(i);
                others.setDeactivate();
            }
        }
    }


    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        if(button.id == 1){

             GuiCustomButton temp = (GuiCustomButton) this.buttonList.get(0);
             temp.select();
             deactivateOthers(0);
             Config.CropType = CropEnum.WHEAT;
            Config.writeConfig();
        }
        if(button.id == 2){

            GuiCustomButton temp = (GuiCustomButton) this.buttonList.get(1);
            temp.select();
            deactivateOthers(1);
            Config.CropType = CropEnum.NETHERWART;
            Config.writeConfig();
        }
        if(button.id == 3){
            GuiCustomButton temp = (GuiCustomButton) this.buttonList.get(2);
            temp.select();
            deactivateOthers(2);
            Config.CropType = CropEnum.POTATO;
            Config.writeConfig();
        }if(button.id == 4){
            GuiCustomButton temp = (GuiCustomButton) this.buttonList.get(3);
            temp.select();
            deactivateOthers(3);
            Config.CropType = CropEnum.CARROT;
            Config.writeConfig();
        }
        if(button.id == 5){
            Config.FarmType =
                    Config.FarmType.equals(FarmEnum.VERTICAL)? FarmEnum.LAYERED : FarmEnum.VERTICAL;
            Config.writeConfig();

        }

        if(button.id == 6){
            mc.thePlayer.closeScreen();
            mc.displayGuiScreen(new GuiSettings());
        }



    }






}
