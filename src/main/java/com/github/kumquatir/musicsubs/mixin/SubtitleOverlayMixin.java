package com.github.kumquatir.musicsubs.mixin;

import com.github.kumquatir.musicsubs.DummySoundInstance;
import com.github.kumquatir.musicsubs.MusicState;
import net.minecraft.client.gui.components.SubtitleOverlay;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.WeighedSoundEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SubtitleOverlay.class)
public class SubtitleOverlayMixin {

    @Shadow @Final private List<SubtitleOverlay.Subtitle> subtitles;

    @Inject(method = "onPlaySound",
            at = @At("HEAD")
    )
    private void injectOnPlaySound(SoundInstance soundInstance, WeighedSoundEvents weighedSoundEvents, CallbackInfo ci) {
        if (soundInstance instanceof DummySoundInstance) {
            MusicState.setCurrentResourceLocation(soundInstance.getSound().getLocation());
            for (SubtitleOverlay.Subtitle subtitle : this.subtitles) {
                if (subtitle.getText().equals(MusicState.getSubtitle().getText())) {
                    return;
                }
			}
            this.subtitles.add(0, MusicState.getSubtitle());
        }
    }

}
