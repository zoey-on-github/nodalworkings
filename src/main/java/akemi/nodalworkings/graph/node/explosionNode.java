package akemi.nodalworkings.graph.node;

import com.mojang.datafixers.util.Either;
import io.github.mattidragon.nodeflow.graph.Connector;
import io.github.mattidragon.nodeflow.graph.data.DataValue;
import io.github.mattidragon.nodeflow.graph.node.Node;
import net.minecraft.text.Text;

public class explosionNode extends Node {
    @Override
    public Connector<?>[] getOutputs() {
        return new Connector[0];
    }

    @Override
    public Connector<?>[] getInputs() {
        return new Connector[0];
    }

    @Override
    protected Either<DataValue<?>[], Text> process(DataValue<?>[] dataValues, ContextProvider contextProvider) {
        return null;
    }
}
