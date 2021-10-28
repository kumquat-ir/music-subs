package com.github.kumquatir.musicsubs.mixin;

import com.github.kumquatir.musicsubs.DummySoundInstance;
import com.github.kumquatir.musicsubs.MusicState;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.sounds.Music;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MusicManager.class)
public class MusicManagerMixin {

    @Shadow @Nullable private SoundInstance currentMusic;

    @Inject(method = "tick",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/client/sounds/MusicManager;currentMusic:Lnet/minecraft/client/resources/sounds/SoundInstance;",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void injectTick(CallbackInfo ci) {
        MusicState.stopPlaying();
    }

    @Inject(method = "startPlaying",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/sounds/SoundManager;play(Lnet/minecraft/client/resources/sounds/SoundInstance;)V"
            )
    )
    private void injectStartPlaying(Music music, CallbackInfo ci) {
        MusicState.startPlaying();
    }

    @Inject(method = "stopPlaying",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/sounds/SoundManager;stop(Lnet/minecraft/client/resources/sounds/SoundInstance;)V"
            )
    )
    private void injectStopPlaying(CallbackInfo ci) {
        MusicState.stopPlaying();
    }

    @Inject(method = "startPlaying",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/client/sounds/MusicManager;currentMusic:Lnet/minecraft/client/resources/sounds/SoundInstance;",
                    opcode = Opcodes.PUTFIELD,
                    shift = At.Shift.AFTER
            )
    )
    private void redirectStartPlaying(Music music, CallbackInfo ci) {
        this.currentMusic = DummySoundInstance.forMusic(music.getEvent());
    }
}
