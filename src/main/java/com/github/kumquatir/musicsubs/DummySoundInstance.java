package com.github.kumquatir.musicsubs;

import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class DummySoundInstance extends SimpleSoundInstance {
    public DummySoundInstance(ResourceLocation resourceLocation, SoundSource soundSource, float f, float g, boolean bl, int i, Attenuation attenuation, double d, double e, double h, boolean bl2) {
        super(resourceLocation, soundSource, f, g, bl, i, attenuation, d, e, h, bl2);
    }

    public static DummySoundInstance forMusic(SoundEvent soundEvent) {
		return new DummySoundInstance(soundEvent.getLocation(), SoundSource.MUSIC, 1.0F, 1.0F, false, 0, SoundInstance.Attenuation.NONE, 0.0D, 0.0D, 0.0D, true);
	}
}
