package akemi.nodalworkings;

import akemi.nodalworkings.graph.node.ModNodeTypes;
import io.github.mattidragon.nodeflow.graph.node.NodeType;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class NodeTypeTagProvider extends FabricTagProvider<NodeType<?>> {
    public NodeTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, NodeType.KEY, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        var valueToKey = (Function<NodeType<?>, RegistryKey<NodeType<?>>>) type -> NodeType.REGISTRY.getKey(type).orElseThrow();
        builder(ModNodeTypes.DAMAGE_GROUP).mapped(valueToKey).add(ModNodeTypes.EXPLOSION);
    }

}


