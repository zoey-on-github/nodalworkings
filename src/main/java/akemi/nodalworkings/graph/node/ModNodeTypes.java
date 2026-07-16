package akemi.nodalworkings.graph.node;

import io.github.mattidragon.nodeflow.graph.node.NodeType;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModNodeTypes {
    public static final TagKey<NodeType<?>> DAMAGE_GROUP = TagKey.of(NodeType.KEY, Identifier.of("akemi.nodalworkings", "damage"));
}
