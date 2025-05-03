package com.example.ornitorrincomod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class OrnitorrincoEntity extends AnimalEntity implements Saddleable, GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient BREEDING_ITEMS = Ingredient.ofItems(Items.COD, Items.SALMON);
    private boolean saddled;
    private boolean perry;

    public OrnitorrincoEntity(EntityType entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createOrnitorrincoAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 14.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(2, new TemptGoal(this, 1.25, BREEDING_ITEMS, false));
        this.goalSelector.add(3, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        
        if (itemStack.isOf(Items.SADDLE) && !this.isSaddled()) {
            this.saddle(null);
            itemStack.decrement(1);
            return ActionResult.success(this.getWorld().isClient);
        }
        
        return super.interactMob(player, hand);
    }

    @Override
    public boolean isSaddled() {
        return this.saddled;
    }

    @Override
    public void saddle(@Nullable SoundEvent sound) {
        this.saddled = true;
    }

    @Override
    public boolean canBeSaddled() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Saddled", this.saddled);
        nbt.putBoolean("Perry", this.perry);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.saddled = nbt.getBoolean("Saddled");
        this.perry = nbt.getBoolean("Perry");
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // Animation controllers will be added here
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.ORNITORRINCO.create(world);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ORNITORRINCO_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ORNITORRINCO_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ORNITORRINCO_HURT;
    }
}