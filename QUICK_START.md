# Quick Start Guide: Handling Node Connection Events

## What This Does

This implementation answers the question: **"How do I get something to happen when nodes connect in Nodeflow?"**

## The Solution in 3 Steps

### Step 1: Create a Custom EditorScreen

```java
public class CustomEditorScreen extends EditorScreen {
    @Override
    public void syncGraph() {
        super.syncGraph();
        // Your code here runs when nodes connect!
        onGraphChanged();
    }
    
    private void onGraphChanged() {
        // Do whatever you want when connections change
        graph.getConnections().forEach(connection -> {
            System.out.println("Connection made!");
        });
    }
}
```

### Step 2: Use Your Custom Screen

```java
// Replace this:
new EditorScreen(title, graph)

// With this:
new CustomEditorScreen(title, graph)
```

### Step 3: Add Your Custom Logic

In `onGraphChanged()`, you can:
- Log connections
- Play sounds
- Validate connections
- Send data to server
- Trigger visual effects
- Save graph state
- Any other custom behavior!

## Example: Play Sound on Connect

```java
private void onGraphChanged() {
    if (client != null) {
        client.getSoundManager().play(
            PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0f)
        );
    }
}
```

## Example: Log Connection Details

```java
private void onGraphChanged() {
    graph.getConnections().forEach(connection -> {
        var source = graph.getNode(connection.sourceUuid());
        var target = graph.getNode(connection.targetUuid());
        
        System.out.println("Connected: " + 
            source.type.name() + " -> " + target.type.name());
    });
}
```

## When Does This Fire?

The `syncGraph()` method is called:
- ✅ When a new connection is created
- ✅ When a connection is removed
- ✅ When nodes are moved

## More Information

- **Full implementation:** See `CustomEditorScreen.java`
- **Detailed examples:** See `CONNECTION_EVENTS_EXAMPLE.md`
- **API reference:** See `IMPLEMENTATION_SUMMARY.md`
- **Overview:** See `README.md`

## Testing

1. Build the mod: `./gradlew build`
2. Run Minecraft with the mod loaded
3. Right-click with the spellbook item
4. Connect nodes in the editor
5. Check console for log messages!

---

That's it! You now know how to respond to node connection events in Nodeflow.
