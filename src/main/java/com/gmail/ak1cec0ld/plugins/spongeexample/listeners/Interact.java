package com.gmail.ak1cec0ld.plugins.spongeexample.listeners;

import com.gmail.ak1cec0ld.plugins.spongeexample.PokemonProvider;
import com.gmail.ak1cec0ld.plugins.spongeexample.RegionMapper;
import com.gmail.ak1cec0ld.plugins.spongeexample.SpongeExample;
import com.gmail.ak1cec0ld.plugins.spongeexample.storage.UsagePermission;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.CommandBlock;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Interact {
    private Map<String, String> verifying;
    SpongeExample pl;


    public Interact(SpongeExample plugin) {
        Sponge.getEventManager().registerListeners(plugin, this);
        verifying = new HashMap<>();
        pl = plugin;
    }

    @Listener
    public void onInteract(InteractBlockEvent.Secondary.OffHand event){
        Location<World> loc = event.getTargetBlock().getLocation().orElse(null);
        if(loc==null)return;
        fixCMD(loc);
        BlockType type = event.getTargetBlock().getState().getType();
        if(!type.equals(BlockTypes.SKULL)) return;
        Optional<Player> playerCause = event.getCause().first(Player.class);
        if (!playerCause.isPresent()) return;
        Player spongePlayer = playerCause.get();

        if(UsagePermission.checkPlayer(spongePlayer, RegionMapper.getRegion(loc))) return;
        Pokemon starter = PokemonProvider.get(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ());
        if(starter==null)return;

        if(!verifying.containsKey(spongePlayer.getUniqueId().toString()) ||
                (verifying.containsKey(spongePlayer.getUniqueId().toString()) &&
                        !verifying.get(spongePlayer.getUniqueId().toString()).equals(starter.getDisplayName()))){
            verifying.put(spongePlayer.getUniqueId().toString(), starter.getDisplayName());
            spongePlayer.sendMessage(Text.of(TextColors.AQUA, TextStyles.ITALIC, "Are you sure you want to pick "+ starter.getDisplayName() +"? Click again."));
            return;
        }
        verifying.remove(spongePlayer.getUniqueId().toString());

        Pixelmon.storageManager.getParty(playerCause.get().getUniqueId()).add(starter);
        UsagePermission.setPlayer(spongePlayer, RegionMapper.getRegion(loc));
    }

    private void fixCMD(Location<World> location){
        for(int x=-5; x <= +5; x++){
            for(int y=-5; y <= +5; y++){
                for(int z=-5; z <= +5; z++){
                    if(location.add(x,y,z).getTileEntity().orElse(null) instanceof CommandBlock){
                        CommandBlock cmd = (CommandBlock)location.add(x,y,z).getTileEntity().get();
                        String commandString = cmd.storedCommand().get();
                        String finalCommandString = "";
                        finalCommandString = commandString.replace("distance=..","r=");
                        cmd.offer(Keys.COMMAND, finalCommandString);
                        pl.getLogger().info(cmd.getCommandData().storedCommand().get());
                    }
                }
            }
        }
    }
}