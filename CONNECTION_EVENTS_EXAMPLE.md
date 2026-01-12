# How to Handle Node Connection Events in Nodeflow

This guide explains how to respond to node connection events when using the Nodeflow library.

## Overview

The Nodeflow library provides a `syncGraph()` method in the `EditorScreen` class that is called whenever:
- A new connection is made between nodes
- A connection is removed
- Nodes are moved (for position syncing)

## Implementation

To handle connection events, create a custom subclass of `EditorScreen` and override the `syncGraph()` method:

```java
public class CustomEditorScreen extends EditorScreen {

    public CustomEditorScreen(Text title, Graph graph) {
        super(title, graph);
    }

    @Override
    public void syncGraph() {
        super.syncGraph(); // Important: call the parent implementation first
        
        // Add your custom logic here
        onGraphChanged();
    }

    private void onGraphChanged() {
        // Access the graph connections
        var connections = graph.getConnections();
        
        // Iterate through all connections
        for (var connection : connections) {
            var sourceNode = graph.getNode(connection.sourceUuid());
            var targetNode = graph.getNode(connection.targetUuid());
            
            // Do something with the connection
            System.out.println("Connection from " + sourceNode.type.name().getString() + 
                             " to " + targetNode.type.name().getString());
        }
    }
}
```

## Usage Example

Replace the default `EditorScreen` with your custom implementation:

```java
// Before:
MinecraftClient.getInstance().setScreen(new EditorScreen(Text.literal("Editor"), graph));

// After:
MinecraftClient.getInstance().setScreen(new CustomEditorScreen(Text.literal("Editor"), graph));
```

## What You Can Do in syncGraph()

When `syncGraph()` is called, you can:

1. **Log connection events**: Track when nodes are connected for debugging
2. **Validate connections**: Check if certain connections are allowed
3. **Trigger effects**: Play sounds, show particles, or display messages
4. **Save state**: Persist the graph to disk or send to server
5. **Update UI**: Refresh other parts of your interface
6. **Execute logic**: Run code when specific node types connect

## Example: Play Sound on Connection

```java
@Override
public void syncGraph() {
    super.syncGraph();
    
    // Play a sound when connections change
    if (client != null) {
        client.getSoundManager().play(
            PositionedSoundInstance.master(SoundEvents.BLOCK_NOTE_BLOCK_BELL.value(), 1.0f)
        );
    }
}
```

## Example: Validate Specific Connections

```java
private void onGraphChanged() {
    var connections = graph.getConnections();
    
    for (var connection : connections) {
        var sourceNode = graph.getNode(connection.sourceUuid());
        var targetNode = graph.getNode(connection.targetUuid());
        
        // Check if we're connecting two specific types
        if (sourceNode != null && targetNode != null) {
            String sourceType = sourceNode.type.name().getString();
            String targetType = targetNode.type.name().getString();
            
            // Prevent certain connections
            if (sourceType.equals("Number") && targetType.equals("String")) {
                // Remove invalid connection
                graph.removeConnections(connection.getTargetConnector(graph));
                
                // Show warning
                showToast(Text.literal("Cannot connect Number to String!").formatted(Formatting.RED));
            }
        }
    }
}
```

## Example: Send to Server

```java
@Override
public void syncGraph() {
    super.syncGraph();
    
    // Send the updated graph to the server
    if (client != null && client.player != null) {
        // Serialize and send your graph data
        var nbt = graph.writeNbt(client.player.getRegistryManager());
        // ... send to server via networking
    }
}
```

## Accessing Connection Details

The `Connection` class provides:
- `sourceUuid()` - UUID of the source node
- `sourceName()` - Name of the output connector
- `targetUuid()` - UUID of the target node  
- `targetName()` - Name of the input connector
- `getSourceConnector(graph)` - Get the actual source connector
- `getTargetConnector(graph)` - Get the actual target connector

## Graph API Methods

The `Graph` class provides useful methods:
- `getConnections()` - Get all connections
- `getConnections(UUID nodeId)` - Get connections for a specific node
- `getConnections(Connector<?> connector)` - Get connections for a specific connector
- `getNode(UUID id)` - Get a node by ID
- `getNodes()` - Get all nodes
- `addConnection(Connector<?> target, Connector<?> source)` - Add a connection
- `removeConnections(Connector<?> connector)` - Remove connections to a connector

## See Also

- Check `CustomEditorScreen.java` for a working implementation
- See `NodalWorkingsClient.java` for how it's integrated
