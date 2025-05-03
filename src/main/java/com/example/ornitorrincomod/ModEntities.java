package com.example.ornitorrincomod.entity;

import com.example.ornitorrincomod.OrnitorrincoMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<OrnitorrincoEntity> ORNITORRINCO = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(OrnitorrincoMod.MOD_ID, "ornitorrinco"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, OrnitorrincoEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.6f))
                    .build()
    );

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(ORNITORRINCO, OrnitorrincoEntity.createOrnitorrincoAttributes());
    }
}