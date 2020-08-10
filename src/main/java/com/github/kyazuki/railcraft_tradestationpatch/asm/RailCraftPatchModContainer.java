package com.github.kyazuki.railcraft_tradestationpatch.asm;

import com.github.kyazuki.railcraft_tradestationpatch.RailCraftPatch;
import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class RailCraftPatchModContainer extends DummyModContainer {
  public RailCraftPatchModContainer() {
    super(new ModMetadata());
    ModMetadata metadata = getMetadata();
    metadata.modId = RailCraftPatch.MODID + "-core";
    metadata.name = RailCraftPatch.NAME + "Core";
    metadata.version = RailCraftPatch.VERSION;
    metadata.authorList.add("kyazuki");
    this.setEnabledState(true);
  }

  @Override
  public boolean registerBus(EventBus bus, LoadController controller) {
    bus.register(this);
    return true;
  }
}
