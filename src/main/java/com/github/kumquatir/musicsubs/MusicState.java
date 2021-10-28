package com.github.kumquatir.musicsubs;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class MusicState {
    private static boolean playing = false;
    private static String currentTrackName = "";
    private static final Map<String, String> nameMap = new HashMap<>();
    private static final MusicSubtitle subtitle = new MusicSubtitle();

    public static void startPlaying() {
        playing = true;
    }

    public static void stopPlaying() {
        playing = false;
    }

    public static boolean getPlaying() {
        return playing;
    }

    public static MusicSubtitle getSubtitle() {
        return subtitle;
    }

    public static void setCurrentResourceLocation(ResourceLocation newResourceLocation) {
        // yes i know this looks stupid
        currentTrackName = nameMap.getOrDefault(newResourceLocation.toString(), newResourceLocation.toString());
        subtitle.updateText();
    }

    public static String getCurrentTrackName() {
        return currentTrackName;
    }

    public static void addNameMapEntry(String location, String name) {
        nameMap.put(location, name);
    }
}
