package msu.msuteam.mixin;

import msu.msuteam.ScrapsLeftovers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(
        method = "eatFood",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/advancement/criterion/ConsumeItemCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/item/ItemStack;)V"
        ),
        locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    private void onEatFood(World world, ItemStack foodStack, CallbackInfoReturnable<ItemStack> cir) {
        // Run on the logical server
        if (!world.isClient) {
            LivingEntity entity = (LivingEntity) (Object) this;
            
            // Check if the entity is a player
            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;
                Item foodItem = foodStack.getItem();

                // Check if the consumed food has a leftover item
                if (ScrapsLeftovers.LEFTOVER_MAP.containsKey(foodItem)) {
                    Item leftoverItem = ScrapsLeftovers.LEFTOVER_MAP.get(foodItem);
                    ItemStack leftoverStack = new ItemStack(leftoverItem);

                    // Try to add to inventory, otherwise drop it
                    if (!player.getInventory().insertStack(leftoverStack)) {
                        player.dropItem(leftoverStack, false);
                    }
                }
            }
        }
    }
}
