package akemi.nodalworkings;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class spellBook extends Item {
    public spellBook(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient() && user instanceof ServerPlayerEntity serverPlayer) {
            NodalWorkings.nodalPayload payload = new NodalWorkings.nodalPayload(user.getBlockPos());
            ServerPlayNetworking.send(serverPlayer, payload);
        }
        return ActionResult.SUCCESS;
    }
}

