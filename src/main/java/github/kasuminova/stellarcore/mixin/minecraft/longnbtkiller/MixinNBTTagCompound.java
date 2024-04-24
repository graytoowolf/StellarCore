package github.kasuminova.stellarcore.mixin.minecraft.longnbtkiller;

import github.kasuminova.stellarcore.common.config.StellarCoreConfig;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NBTTagCompound.class)
@SuppressWarnings("MethodMayBeStatic")
public class MixinNBTTagCompound {

    @ModifyConstant(method = "read", constant = @Constant(intValue = 512))
    private int modifyReadMaxDepth(final int constant) {
        return StellarCoreConfig.BUG_FIXES.vanilla.maxNBTDepth;
    }

}
