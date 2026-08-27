package akemi.nodalworkings;

import akemi.nodalworkings.graph.node.damage.explosionNode;
import io.github.mattidragon.nodeflow.graph.Graph;
import io.github.mattidragon.nodeflow.graph.context.Context;
import io.github.mattidragon.nodeflow.graph.context.ContextType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class spellBook extends Item {
    private static final Map<UUID, Graph> PLAYER_GRAPHS = new ConcurrentHashMap<>();

    public spellBook(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient() && user instanceof ServerPlayerEntity serverPlayer) {
            Graph graph = PLAYER_GRAPHS.computeIfAbsent(serverPlayer.getUuid(), key -> {
                Graph newGraph = new Graph(NodalWorkings.ENVIRONMENT);
                newGraph.addNode(new explosionNode(newGraph));
                return newGraph;
            });
            var context = Context.builder()
                    .put(ContextType.SERVER_WORLD, serverPlayer.getServerWorld())
                    .put(ContextType.BLOCK_POS, user.getBlockPos())
                    .build();
            var errors = graph.evaluate(context);
            if (!errors.isEmpty()) {
                NodalWorkings.LOGGER.warn("Graph execution failed: {}", errors.getFirst().getName().getString());
            }
            NodalWorkings.nodalPayload payload = new NodalWorkings.nodalPayload(user.getBlockPos());
            ServerPlayNetworking.send(serverPlayer, payload);
        }
        return ActionResult.SUCCESS;
    }
}
