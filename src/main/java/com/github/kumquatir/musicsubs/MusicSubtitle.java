package com.github.kumquatir.musicsubs;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.SubtitleOverlay;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.phys.Vec3;

@Environment(EnvType.CLIENT)
public class MusicSubtitle extends SubtitleOverlay.Subtitle {

    private Component text2;

    public MusicSubtitle() {
        super(new TextComponent("If you are seeing this, something has gone horribly wrong"), null);
        updateText();
    }

    public void updateText() {
        text2 = new TextComponent("§b[§d" + MusicState.getCurrentTrackName() + "§b]");
    }

    @Override
    public Component getText() {
        return text2;
    }

    @Override
    public Vec3 getLocation() {
        return Minecraft.getInstance().player == null ? new Vec3(0.0D, 0.0D, 0.0D) : Minecraft.getInstance().player.position();
    }

    @Override
    public long getTime() {
        return MusicState.getPlaying() ? Util.getMillis() : 0;
    }
}
