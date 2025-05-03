package com.example.ornitorrincomod;

import com.example.ornitorrincomod.entity.ModEntities;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrnitorrincoMod implements ModInitializer {
    public static final String MOD_ID = "ornitorrincomod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModEntities.registerEntities();
        LOGGER.info("Ornitorrinco Mod initialized!");
    }
}