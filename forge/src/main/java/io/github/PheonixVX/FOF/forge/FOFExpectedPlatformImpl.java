package io.github.PheonixVX.FOF.forge;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.util.Identifier;
import net.minecraftforge.fml.network.NetworkHooks;

public class FOFExpectedPlatformImpl {
    public static Packet<?> createSpawnPacket(Entity entity, Identifier identifier) {
        return NetworkHooks.getEntitySpawningPacket(entity);
    }
}