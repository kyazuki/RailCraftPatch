package com.github.kyazuki.railcraft_tradestationpatch.asm.transformers;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;

public class TileLogicClassTransformer implements IClassTransformer {
  private static final String TARGET_CLASS_NAME = "mods.railcraft.common.blocks.TileLogic";

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
      if (name.equals("blockActivated")) {
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
      int line = 82;
      method.visitCode();
      Label L0 = new Label();
      method.visitLineNumber(line, L0);
      method.visitVarInsn(ALOAD, 1);
      method.visitVarInsn(ALOAD, 2);
      method.visitMethodInsn(INVOKESTATIC, "mods/railcraft/common/plugins/forge/PlayerPlugin", "doesItemBlockActivation", "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/util/EnumHand;)Z", false);
      Label L1 = new Label();
      method.visitJumpInsn(IFEQ, L1);
      Label L2 = new Label();
      method.visitLabel(L2);
      method.visitLineNumber(++line, L2);
      method.visitInsn(ICONST_0);
      method.visitInsn(IRETURN);
      method.visitLabel(L1);
      method.visitLineNumber(++line, L1);
      method.visitFrame(F_SAME, 0, null, 0, null);
      method.visitVarInsn(ALOAD, 0);
      method.visitFieldInsn(GETFIELD, "mods/railcraft/common/blocks/TileLogic", "logic", "Lmods/railcraft/common/blocks/logic/Logic;");
      method.visitTypeInsn(INSTANCEOF, "mods/railcraft/common/blocks/logic/TradeStationLogic");
      Label L3 = new Label();
      method.visitJumpInsn(IFEQ, L3);
      Label L4 = new Label();
      method.visitLabel(L4);
      method.visitLineNumber(++line, L4);
      method.visitVarInsn(ALOAD, 0);
      method.visitVarInsn(ALOAD, 1);
      method.visitMethodInsn(INVOKEVIRTUAL, "mods/railcraft/common/blocks/TileLogic", "openGui", "(Lnet/minecraft/entity/player/EntityPlayer;)Z", false);
      Label L5 = new Label();
      method.visitJumpInsn(IFNE, L5);
      method.visitInsn(ICONST_0);
      method.visitInsn(IRETURN);
      method.visitLabel(L5);
      method.visitLineNumber(++line, L5);
      method.visitFrame(F_SAME, 0, null, 0, null);
      method.visitVarInsn(ALOAD, 0);
      method.visitFieldInsn(GETFIELD, "mods/railcraft/common/blocks/TileLogic", "logic", "Lmods/railcraft/common/blocks/logic/Logic;");
      method.visitVarInsn(ALOAD, 1);
      method.visitVarInsn(ALOAD, 2);
      method.visitMethodInsn(INVOKEVIRTUAL, "mods/railcraft/common/blocks/logic/Logic", "interact", "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/util/EnumHand;)Z", false);
      method.visitInsn(IRETURN);
      method.visitLabel(L3);
      method.visitLineNumber(++line, L3);
      method.visitFrame(F_SAME, 0, null, 0, null);
      method.visitVarInsn(ALOAD, 0);
      method.visitFieldInsn(GETFIELD, "mods/railcraft/common/blocks/TileLogic", "logic", "Lmods/railcraft/common/blocks/logic/Logic;");
      method.visitVarInsn(ALOAD, 1);
      method.visitVarInsn(ALOAD, 2);
      method.visitMethodInsn(INVOKEVIRTUAL, "mods/railcraft/common/blocks/logic/Logic", "interact", "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/util/EnumHand;)Z", false);
      Label L6 = new Label();
      method.visitJumpInsn(IFNE, L6);
      method.visitVarInsn(ALOAD, 0);
      method.visitVarInsn(ALOAD, 1);
      method.visitMethodInsn(INVOKEVIRTUAL, "mods/railcraft/common/blocks/TileLogic", "openGui", "(Lnet/minecraft/entity/player/EntityPlayer;)Z", false);
      Label L7 = new Label();
      method.visitJumpInsn(IFEQ, L7);
      method.visitLabel(L6);
      method.visitFrame(F_SAME, 0, null, 0, null);
      method.visitInsn(ICONST_1);
      Label L8 = new Label();
      method.visitJumpInsn(GOTO, L8);
      method.visitLabel(L7);
      method.visitFrame(F_SAME, 0, null, 0, null);
      method.visitInsn(ICONST_0);
      method.visitLabel(L8);
      method.visitFrame(F_SAME1, 0, null, 0, new Object[]{"I"});
      method.visitInsn(IRETURN);
      Label L9 = new Label();
      method.visitLabel(L9);
      method.visitLocalVariable("this", "Lmods/railcraft/common/blocks/TileLogic;", null, L0, L9, 0);
      method.visitLocalVariable("player", "Lnet/minecraft/entity/player/EntityPlayer;", null, L0, L9, 1);
      method.visitLocalVariable("hand", "Lnet/minecraft/util/EnumHand;", null, L0, L9, 2);
      method.visitLocalVariable("side", "Lnet/minecraft/util/EnumFacing;", null, L0, L9, 3);
      method.visitLocalVariable("hitX", "F", null, L0, L9, 4);
      method.visitLocalVariable("hitY", "F", null, L0, L9, 5);
      method.visitLocalVariable("hitZ", "F", null, L0, L9, 6);
      method.visitMaxs(3, 7);
      method.visitEnd();
    }
  }
}
