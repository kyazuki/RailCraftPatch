package com.github.kyazuki.railcraft_tradestationpatch;

import net.minecraftforge.fml.common.Mod;

@Mod(modid = RailCraftPatch.MODID, name = RailCraftPatch.NAME, version = RailCraftPatch.VERSION, dependencies = "required-after:railcraft@[12.0.0];")
public class RailCraftPatch {
  public static final String MODID = "railcraft_tradestationpatch";
  public static final String NAME = "RailCraftPatch";
  public static final String VERSION = "1.12.2-0.1.0";

  @Mod.Instance(MODID)
  public static RailCraftPatch INSTANCE;
}
