package com.exemplo.ornitorrincomod.client.model;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import com.exemplo.ornitorrincomod.entity.OrnitorrincoEntity;

public class OrnitorrincoModel extends GeoModel<OrnitorrincoEntity> {
    @Override
    public Identifier getModelResource(OrnitorrincoEntity object) {
        return new Identifier("ornitorrincomod", "geo/ornitorrinco.geo.json");
    }

    @Override
    public Identifier getTextureResource(OrnitorrincoEntity object) {
        return new Identifier("ornitorrincomod", "textures/entity/ornitorrinco.png");
    }

    @Override
    public Identifier getAnimationResource(OrnitorrincoEntity animatable) {
        return new Identifier("ornitorrincomod", "animations/ornitorrinco.animation.json");
    }
}
