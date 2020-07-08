package com.gmail.ak1cec0ld.plugins.spongeexample.listeners;

import com.gmail.ak1cec0ld.plugins.spongeexample.PokemonProvider;
import com.gmail.ak1cec0ld.plugins.spongeexample.SpongeExample;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.world.Location;

import java.util.Optional;

public class Interact {
    private SpongeExample instance;
    //private DataBaseManager db;

    public Interact(SpongeExample plugin) {
        Sponge.getEventManager().registerListeners(plugin, this);
        instance = plugin;
        //db = new DataBaseManager(instance);
    }

    @Listener
    public void onInteract(InteractBlockEvent.Secondary.OffHand event){
        BlockType type = event.getTargetBlock().getState().getType();
        if(!type.equals(BlockTypes.SKULL)) return;
        Optional<Player> playerCause = event.getCause().first(Player.class);
        if (!playerCause.isPresent()) return;
        Player spongePlayer = playerCause.get();

        Location loc = event.getTargetBlock().getLocation().get();
        Pokemon starter = PokemonProvider.get(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ());

        //if(db.dbContains(playerCause.get().getUniqueId().toString()))return;
        //db.dbInsert(playerCause.get().getUniqueId().toString());

        if(spongePlayer.supports(SpongeExample.KEY)){
            instance.getLogger().info("Supported");
        } else {
            instance.getLogger().info("Not Supported");
        }

        DataTransactionResult result = spongePlayer.offer(SpongeExample.KEY, "kanto");

        instance.getLogger().info(result.toString());

        Pixelmon.storageManager.getParty(playerCause.get().getUniqueId()).add(starter);

    }
}