package akemi.nodalworkings.graph.node.damage;

import akemi.nodalworkings.graph.node.ModNodeTypes;
import com.mojang.datafixers.util.Either;
import io.github.mattidragon.nodeflow.graph.Connector;
import io.github.mattidragon.nodeflow.graph.Graph;
import io.github.mattidragon.nodeflow.graph.context.ContextType;
import io.github.mattidragon.nodeflow.graph.data.DataValue;
import io.github.mattidragon.nodeflow.graph.node.Node;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public  class explosionNode extends Node {

    public explosionNode(Graph graph) {
        super(ModNodeTypes.EXPLOSION, List.of(ContextType.SERVER_WORLD, ContextType.BLOCK_POS), graph);
    }

    @Override
    public Connector<?>[] getInputs() {
        return new Connector[0];
    }

    @Override
    public Connector<?>[] getOutputs() {
        return new Connector[0];
    }

    @Override
    protected Either<DataValue<?>[], Text> process(DataValue<?>[] dataValues, ContextProvider contextProvider) {
        try {
            var world = contextProvider.get(ContextType.SERVER_WORLD);
            BlockPos pos = contextProvider.get(ContextType.BLOCK_POS);
            world.createExplosion(
                    null,
                    pos.getX() + 0.5,
                    pos.getY(),
                    pos.getZ() + 0.5,
                    3.0f,
                    World.ExplosionSourceType.BLOCK
            );
            return Either.left(new DataValue[0]);
        } catch (RuntimeException exception) {
            return Either.right(Text.literal("Explosion node failed: " + exception.getMessage()));
        }
    }
}
