package com.github.kumquatir.musicsubs;

import com.google.gson.stream.JsonReader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MusicSubsMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public ResourceLocation getFabricId() {
                return new ResourceLocation("musicsubs", "musicnamemaps");
            }

            @Override
            public void onResourceManagerReload(ResourceManager resourceManager) {
                for (ResourceLocation rl : resourceManager.listResources("musicnamemaps", path -> path.endsWith(".json"))) {
                    try (InputStream stream = resourceManager.getResource(rl).getInputStream()) {
                        JsonReader reader = new JsonReader(new InputStreamReader(stream));
                        reader.beginObject();
                        while (reader.hasNext()) {
                            MusicState.addNameMapEntry(reader.nextName(), reader.nextString());
                        }
                        reader.endObject();
                    } catch (Exception e) {
                        System.out.println("Error loading music name maps: " + e);
                    }
                }
            }
        });
    }
}
