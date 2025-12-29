package msu.msuteam;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;

public class ScrapsLeftovers implements ModInitializer {
    public static final String MOD_ID = "scraps_and_leftovers";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Map<Item, Item> LEFTOVER_MAP = new HashMap<>();

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Scraps & Leftovers is preparing the leftovers!");

        // Populate the map with food and their leftovers
        LEFTOVER_MAP.put(Items.COOKED_CHICKEN, Items.BONE);
        LEFTOVER_MAP.put(Items.COOKED_PORKCHOP, Items.BONE);
        LEFTOVER_MAP.put(Items.PORKCHOP, Items.BONE);
        LEFTOVER_MAP.put(Items.MELON_SLICE, Items.MELON_SEEDS);
        LEFTOVER_MAP.put(Items.APPLE, Items.STICK);
    }
}
