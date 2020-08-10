package com.github.kyazuki.railcraft_tradestationpatch.asm;

import com.github.kyazuki.railcraft_tradestationpatch.asm.transformers.CartBaseLogicClassTransformer;
import com.github.kyazuki.railcraft_tradestationpatch.asm.transformers.TileLogicClassTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.TransformerExclusions({"com.github.kyazuki.railcraft_tradestationpatch"})
public class RailCraftPatchPlugin implements IFMLLoadingPlugin {
  @Override
  public String[] getASMTransformerClass() {
    return new String[]{TileLogicClassTransformer.class.getName(), CartBaseLogicClassTransformer.class.getName()};
  }

  @Override
  public String getModContainerClass() {
    return RailCraftPatchModContainer.class.getName();
  }

  @Nullable
  @Override
  public String getSetupClass() {
    return null;
  }

  @Override
  public void injectData(Map<String, Object> data) {}

  @Override
  public String getAccessTransformerClass() {
    return null;
  }
}
