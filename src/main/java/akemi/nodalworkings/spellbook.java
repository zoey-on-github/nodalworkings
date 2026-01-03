package akemi.nodalworkings;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.entity.VaultBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerInventory;

public class spellbook extends Item {
    public spellbook(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        NodalWorkings.LOGGER.info("meoww");
        if (!world.isClient() && user instanceof ServerPlayerEntity serverPlayer) {
            NodalWorkings.nodalPayload payload = new NodalWorkings.nodalPayload(user.getBlockPos());
            ServerPlayNetworking.send(serverPlayer, payload);
        }
        return ActionResult.SUCCESS;
    }
}

