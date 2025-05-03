package com.example.ornitorrincomod.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OrnitorrincoRenderer extends GeoEntityRenderer<OrnitorrincoEntity> {
    public OrnitorrincoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new OrnitorrincoModel());
    }
}