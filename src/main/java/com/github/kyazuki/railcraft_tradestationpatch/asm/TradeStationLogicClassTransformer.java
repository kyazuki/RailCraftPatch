package com.github.kyazuki.railcraft_tradestationpatch.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;

public class TradeStationLogicClassTransformer implements IClassTransformer {
  private static final String TARGET_CLASS_NAME = "mods.railcraft.common.blocks.logic.TradeStationLogic";

  @Override
  public byte[] transform(String name, String transformedName, byte[] originalClass) {
    if (!transformedName.equals(TARGET_CLASS_NAME)) return originalClass;
    try {
      ClassReader cr = new ClassReader(originalClass);
      ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
      RailCraftPatchClassVisitor cv = new RailCraftPatchClassVisitor(cw);

      cr.accept(cv, ClassReader.EXPAND_FRAMES);

      return cw.toByteArray();
    } catch (Exception e) {
      return originalClass;
    }
  }

  private static class RailCraftPatchClassVisitor extends ClassVisitor {
    public RailCraftPatchClassVisitor(ClassVisitor cv) {
      super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
      MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
      if (name.equals("interact")) {
        return new RailCraftPatchMethodVisitor(mv);
      }
      return mv;
    }
  }

  private static class RailCraftPatchMethodVisitor extends MethodVisitor {
    private final MethodVisitor method;

    public RailCraftPatchMethodVisitor(MethodVisitor method) {
      super(ASM5, null);
      this.method = method;
    }

    @Override
    public void visitCode() {
      int line = 108;
      method.visitCode();
      Label L0 = new Label();
      method.visitLineNumber(line, L0);
      method.visitVarInsn(ALOAD, 0);
      method.visitVarInsn(ALOAD, 1);
      method.visitVarInsn(ALOAD, 2);
      method.visitMethodInsn(INVOKESPECIAL, "mods/railcraft/common/blocks/logic/InventoryLogic", "interact", "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/util/EnumHand;)Z", false);
      Label L1 = new Label();
      method.visitJumpInsn(IFEQ, L1);
      Label L2 = new Label();
      method.visitLabel(L2);
      method.visitLineNumber(++line, L2);
      method.visitInsn(ICONST_1);
      method.visitInsn(IRETURN);
      method.visitLabel(L1);
      method.visitLineNumber(++line, L1);
      method.visitFrame(F_SAME, 0, null, 0, null);
      method.visitVarInsn(ALOAD, 0);
      method.visitMethodInsn(INVOKEVIRTUAL, "mods/railcraft/common/blocks/logic/TradeStationLogic", "getXpCollected", "()I)", false);
      Label L3 = new Label();
      method.visitJumpInsn(IFLE, L3);
      Label L4 = new Label();
      method.visitLabel(L4);
      method.visitLineNumber(++line, L4);
      method.visitVarInsn(ALOAD, 1);
      method.visitVarInsn(ALOAD, 0);
      method.visitMethodInsn(INVOKEVIRTUAL, "mods/railcraft/common/blocks/logic/TradeStationLogic", "getXpCollected", "()I)", false);
      method.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/entity/player/EntityPlayer", "func_71023_q", "(I)V)", false);
      Label L5 = new Label();
      method.visitLabel(L5);
      method.visitLineNumber(++line, L5);
      method.visitVarInsn(ALOAD, 0);
      method.visitMethodInsn(INVOKEVIRTUAL, "mods/railcraft/common/blocks/logic/TradeStationLogic", "clearXp", "()V)", false);
      Label L6 = new Label();
      method.visitLabel(L6);
      method.visitLineNumber(++line, L6);
      method.visitInsn(ICONST_1);
      method.visitInsn(IRETURN);
      method.visitLabel(L3);
      method.visitLineNumber(++line, L3);
      method.visitFrame(F_SAME, 0, null, 0, null);
      method.visitInsn(ICONST_0);
      method.visitInsn(IRETURN);
      Label L7 = new Label();
      method.visitLabel(L7);
      method.visitLocalVariable("this", "Lmods/railcraft/common/blocks/logic/TradeStationLogic;", null, L0, L7, 0);
      method.visitLocalVariable("player", "Lnet/minecraft/entity/player/EntityPlayer;", null, L0, L7, 1);
      method.visitLocalVariable("hand", "Lnet/minecraft/util/EnumHand;", null, L0, L7, 2);
      method.visitMaxs(3, 3);
      method.visitEnd();
    }
  }
}
