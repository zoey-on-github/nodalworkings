package akemi.nodalworkings;

import io.github.mattidragon.nodeflow.graph.context.ContextType;
import io.github.mattidragon.nodeflow.graph.data.DataType;
import io.github.mattidragon.nodeflow.graph.node.group.NodeGroup;
import io.github.mattidragon.nodeflow.graph.node.group.TagNodeGroup;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.mixin.content.registry.LandPathNodeMakerMixin;
import net.minecraft.block.entity.VaultBlockEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.mattidragon.nodeflow.graph.GraphEnvironment;

public class NodalWorkings implements ModInitializer {
    public static final String MOD_ID = "nodalworkings";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final GraphEnvironment ENVIRONMENT = GraphEnvironment.builder()
            // Adds context that nodes need to execute. Stays same during each evaluation
            .addContextTypes(ContextType.SERVER_WORLD, ContextType.BLOCK_POS, ContextType.SERVER)
            // Datatypes that are allowed to be used. Existssymotion-linemarks) for nodes that can act on any data type
            // to know which ones are allowed
            .addDataTypes(DataType.BOOLEAN, DataType.NUMBER, DataType.STRING)
            // Adds groups of nodes at a time. They are also used for grouping in the editor.
            //.addNodeGroups(new TagNodeGroup(NodeGroup.MATH), new TagNodeGroup(NodeGroup.ADVANCED_MATH), new 
            //TagNodeGroup(NodeGroup.LOGIC), new TagNodeGroup(ModNodeTypes.REDSTONE_GROUP))
            .build();


    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        nodalItems.initialize();
        LOGGER.info("hai im julie :3");

    }
}