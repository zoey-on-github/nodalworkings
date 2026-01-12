package akemi.nodalworkings;

import io.github.mattidragon.nodeflow.client.ui.screen.EditorScreen;
import io.github.mattidragon.nodeflow.graph.Graph;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * Custom EditorScreen that demonstrates how to respond to node connection events.
 * 
 * The syncGraph() method is called whenever:
 * - A new connection is made between nodes
 * - A connection is removed
 * - Nodes are moved (for position syncing)
 * 
 * You can override this method to implement custom behavior when nodes connect.
 */
public class CustomEditorScreen extends EditorScreen {

    public CustomEditorScreen(Text title, Graph graph) {
        super(title, graph);
    }

    public CustomEditorScreen(Text title, Graph graph, Identifier texture) {
        super(title, graph, texture);
    }

    /**
     * Called whenever the graph changes, including when connections are made.
     * Override this method to handle connection events.
     */
    @Override
    public void syncGraph() {
        super.syncGraph();
        
        // This is where you can add custom logic when nodes connect
        onGraphChanged();
    }

    /**
     * Custom handler for graph changes. This is called whenever connections are made/removed.
     * You can implement your own logic here.
     */
    private void onGraphChanged() {
        // Log the current state of connections
        var connections = graph.getConnections();
        
        if (!connections.isEmpty()) {
            NodalWorkings.LOGGER.info("Graph updated! Current connections: {}", connections.size());
            
            // Example: Log details about each connection
            connections.forEach(connection -> {
                var sourceNode = graph.getNode(connection.sourceUuid());
                var targetNode = graph.getNode(connection.targetUuid());
                
                if (sourceNode != null && targetNode != null) {
                    NodalWorkings.LOGGER.info("Connection: {} [{}] -> {} [{}]",
                        sourceNode.type.name().getString(),
                        connection.sourceName(),
                        targetNode.type.name().getString(),
                        connection.targetName()
                    );
                }
            });
        }
        
        // You can add custom behavior here, such as:
        // - Validating connections
        // - Triggering effects when specific nodes connect
        // - Saving the graph state
        // - Sending data to the server
        // - Playing sounds or showing visual effects
    }
}
