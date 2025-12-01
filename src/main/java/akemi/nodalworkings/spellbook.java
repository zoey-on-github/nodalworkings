package akemi.nodalworkings;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.entity.VaultBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class spellbook extends Item {
    public spellbook(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        NodalWorkings.LOGGER.info("meoww");
        return ActionResult.SUCCESS;
    }
}
