package akemi.nodalworkings.graph.node.damage;

import akemi.nodalworkings.graph.node.ModNodeTypes;
import com.mojang.datafixers.util.Either;
import io.github.mattidragon.nodeflow.graph.Connector;
import io.github.mattidragon.nodeflow.graph.Graph;
import io.github.mattidragon.nodeflow.graph.context.ContextType;
import io.github.mattidragon.nodeflow.graph.data.DataType;
import io.github.mattidragon.nodeflow.graph.data.DataValue;
import io.github.mattidragon.nodeflow.graph.node.Node;
import io.github.mattidragon.nodeflow.graph.node.NodeType;
import net.minecraft.text.Text;

import java.util.List;

public  class explosionNode extends Node {

    public explosionNode(Graph graph) {
        super(ModNodeTypes.EXPLOSION, List.of(ContextType.SERVER_WORLD), graph);
    }

    @Override
    public Connector<?>[] getOutputs() {
        return new Connector[] {
                DataType.NUMBER.makeRequiredInput("first coord", this),
                DataType.NUMBER.makeRequiredInput("second coord", this),
                DataType.NUMBER.makeRequiredInput("third coord", this)
        };
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
