package akemi.nodalworkings.graph.node;

import akemi.nodalworkings.graph.node.damage.explosionNode;
import io.github.mattidragon.nodeflow.graph.node.NodeType;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModNodeTypes {
    public static final TagKey<NodeType<?>> DAMAGE_GROUP = TagKey.of(NodeType.KEY, Identifier.of("akemi.nodalworkings", "damage"));
    public static final NodeType<explosionNode> EXPLOSION = new NodeType<>(explosionNode::new);

    public static void register() {
        //NodeType.register(EXPLOSION, id("explosion"));
        NodeType.register(EXPLOSION, Identifier.of("akemi.nodalworkings","explosion"));
    }
}
