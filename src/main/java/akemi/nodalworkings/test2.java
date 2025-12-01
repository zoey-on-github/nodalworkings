package akemi.nodalworkings;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class test2 extends Item {
    public test2(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        NodalWorkings.LOGGER.info("meoww");
        return ActionResult.SUCCESS;
    }
}
