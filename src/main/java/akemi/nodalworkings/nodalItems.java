package akemi.nodalworkings;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class nodalItems {
    public static final RegistryKey<ItemGroup> NodalItemsKey = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            Identifier.of("akemi.nodalworkings","item_group"));
    public static final ItemGroup NodalItems = FabricItemGroup.builder()
            .icon(() -> new ItemStack(nodalItems.test))
            .displayName(Text.translatable("itemGroup.nodalworkings"))
            .build();
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(NodalItemsKey)
                .register(ItemGroup -> {
                    ItemGroup.add(test);
                    ItemGroup.add(test2);
                });
        Registry.register(Registries.ITEM_GROUP,NodalItemsKey,NodalItems);
    }

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("akemi.nodalworkings",
                name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static final Item test = register("test", Item::new, new Item.Settings());
    public static final Item test2 = register("test2", spellbook::new, new Item.Settings());
}
