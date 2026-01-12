# Nodeflow Connection Event Handler

This project demonstrates how to handle node connection events when using the [Nodeflow library](https://github.com/MattiDragon/nodeflow/).

## What's Implemented

This implementation shows how to respond to node connection events in Nodeflow by:

1. **Custom EditorScreen** (`CustomEditorScreen.java`): A subclass of `EditorScreen` that overrides the `syncGraph()` method to detect and respond to connection changes.

2. **Integration** (`NodalWorkingsClient.java`): Updated to use the custom editor screen instead of the default one.

3. **Example Handler**: The `CustomEditorScreen` logs connection events to the console, showing:
   - When connections are made or removed
   - Details about each connection (source and target nodes)
   - Which connectors are being used

## How It Works

The Nodeflow library calls `syncGraph()` on the `EditorScreen` whenever:
- A new connection is made between nodes
- A connection is removed
- Nodes are moved (for position syncing)

By overriding this method, you can inject custom logic to respond to these events.

## Key Files

- **`src/client/java/akemi/nodalworkings/CustomEditorScreen.java`**: The custom editor that handles connection events
- **`src/client/java/akemi/nodalworkings/NodalWorkingsClient.java`**: Uses the custom editor
- **`CONNECTION_EVENTS_EXAMPLE.md`**: Comprehensive guide with examples

## Usage

When you right-click with the spellbook item in-game, it opens the custom editor. As you connect nodes together:

1. The `syncGraph()` method is called
2. The `onGraphChanged()` method logs the connection details
3. You can see the logs in the console/log file

Check the log output for messages like:
```
[nodalworkings] Graph updated! Current connections: 2
[nodalworkings] Connection: Add [left] -> Number [value]
[nodalworkings] Connection: Number [value] -> Print [value]
```

## Extending This

You can modify `CustomEditorScreen.onGraphChanged()` to:
- Play sounds when nodes connect
- Validate certain connection types
- Send graph data to the server
- Trigger special effects
- Save the graph state
- Update other UI elements

See `CONNECTION_EVENTS_EXAMPLE.md` for detailed examples of what you can do.

## Building and Running

This is a Fabric mod for Minecraft 1.21.6. To build and test:

```bash
./gradlew build
```

Then place the generated jar from `build/libs/` into your Minecraft mods folder.

## API Reference

The key classes from Nodeflow:
- **`Graph`**: Contains nodes and connections
- **`Connection`**: Represents a connection between two node connectors
- **`EditorScreen`**: The UI for editing node graphs
- **`Node`**: A single node in the graph
- **`Connector`**: An input or output on a node

For more details, see the [Nodeflow repository](https://github.com/MattiDragon/nodeflow/).
