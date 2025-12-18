package akemi.nodalworkings;

import com.jcraft.jorbis.Block;
import io.github.mattidragon.nodeflow.graph.context.ContextType;
import io.github.mattidragon.nodeflow.graph.data.DataType;
import io.github.mattidragon.nodeflow.graph.node.NodeTypeTags;
import io.github.mattidragon.nodeflow.graph.node.group.TagNodeGroup;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.mattidragon.nodeflow.graph.GraphEnvironment;

public class NodalWorkings implements ModInitializer {
    public static final String MOD_ID = "nodalworkings";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public record nodalPayload(BlockPos pos) implements CustomPayload {
        public static final Identifier NODAL_PAYLOAD_ID = Identifier.of("akemi.nodalworkings","nodalpayload");
        public static final CustomPayload.Id<nodalPayload> ID = new CustomPayload.Id<>(NODAL_PAYLOAD_ID);
        public static final PacketCodec<RegistryByteBuf,nodalPayload> CODEC= PacketCodec.tuple(BlockPos.PACKET_CODEC,nodalPayload::pos, nodalPayload::new);
        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }

    }
    public static final GraphEnvironment ENVIRONMENT = GraphEnvironment.builder()
            // Adds context that nodes need to execute. Stays same during each evaluation
            .addContextTypes(ContextType.SERVER_WORLD, ContextType.BLOCK_POS, ContextType.SERVER)
            // Datatypes that are allowed to be used. Exists for nodes that can act on any data type
            // to know which ones are allowed
            .addDataTypes(DataType.BOOLEAN, DataType.NUMBER, DataType.STRING)
            // Adds groups of nodes at a time. They are also used for grouping in the editor.
            .addNodeGroups(new TagNodeGroup(NodeTypeTags.MATH), new TagNodeGroup(NodeTypeTags.ADVANCED_MATH), 
             new TagNodeGroup(NodeTypeTags.LOGIC))
            .build();


    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        nodalItems.initialize();
        PayloadTypeRegistry.playS2C().register(nodalPayload.ID, nodalPayload.CODEC);
        LOGGER.info("hai im julie :3");

    }
}