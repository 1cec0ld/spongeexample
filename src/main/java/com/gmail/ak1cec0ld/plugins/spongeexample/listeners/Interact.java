package com.gmail.ak1cec0ld.plugins.spongeexample.listeners;

import com.gmail.ak1cec0ld.plugins.spongeexample.SpongeExample;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class Interact {
    private SpongeExample instance;

    public Interact(SpongeExample plugin) {
        Sponge.getEventManager().registerListeners(plugin, this);
        instance = plugin;
    }

    @Listener
    public void onInteract(InteractBlockEvent event){
        instance.getLogger().info(event.getTargetBlock().getState().getType().toString());

        EntityPlayerMP player;
        PlayerPartyStorage storage = Pixelmon.storageManager.getParty(player);
        if (storage == null)
            return;
    }

}