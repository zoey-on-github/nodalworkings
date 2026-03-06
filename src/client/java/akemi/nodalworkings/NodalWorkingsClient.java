package akemi.nodalworkings;

import akemi.nodalworkings.graph.node.ModNodeTypes;
import io.github.mattidragon.nodeflow.client.ui.screen.EditorScreen;
import io.github.mattidragon.nodeflow.graph.Graph;
import io.github.mattidragon.nodeflow.graph.GraphEnvironment;
import io.github.mattidragon.nodeflow.graph.context.ContextType;
import io.github.mattidragon.nodeflow.graph.data.DataType;
import io.github.mattidragon.nodeflow.graph.node.NodeType;
import io.github.mattidragon.nodeflow.graph.node.NodeTypeTags;
import io.github.mattidragon.nodeflow.graph.node.group.DirectNodeGroup;
import io.github.mattidragon.nodeflow.graph.node.group.TagNodeGroup;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;

import java.util.List;

public class NodalWorkingsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ClientPlayNetworking.registerGlobalReceiver(NodalWorkings.nodalPayload.ID, (nodalPayload, context) -> {
			ClientWorld world = context.client().world;
			if(world == null) {
				return;
			}
			var graph = new Graph(new GraphEnvironment(DataType.REGISTRY.stream().toList(),
					ContextType.REGISTRY.stream().toList(),
					List.of(new TagNodeGroup(NodeTypeTags.LOGIC),
							new TagNodeGroup(NodeTypeTags.DEBUG),
							new TagNodeGroup(NodeTypeTags.MATH),
							new TagNodeGroup(NodeTypeTags.FLOW),
							new TagNodeGroup(NodeTypeTags.ADVANCED_MATH),
							new TagNodeGroup(NodeTypeTags.CONSTANTS),
							new TagNodeGroup(NodeTypeTags.COMPARE_NUMBER),
							new TagNodeGroup(ModNodeTypes.DAMAGE_GROUP),
							DirectNodeGroup.misc(NodeType.REGISTRY.stream().toArray(NodeType[]::new)))));

			MinecraftClient.getInstance().setScreen(new EditorScreen(Text.literal("Test Editor"), graph));
		});
		}
	static void testNodes() {
	}
}