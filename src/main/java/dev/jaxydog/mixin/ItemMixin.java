package dev.jaxydog.mixin;

import dev.jaxydog.utility.NbtUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Provide an NBT tag to disable enchantment glint */
@Mixin(Item.class)
public abstract class ItemMixin {

	@Inject(method = "hasGlint", at = @At("HEAD"), cancellable = true)
	private void hasGlintInject(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfo) {
		if (NbtUtil.contains(stack, NbtUtil.SET_GLINT_KEY)) {
			callbackInfo.setReturnValue(NbtUtil.getBoolean(stack, NbtUtil.SET_GLINT_KEY));
		}
	}

}
