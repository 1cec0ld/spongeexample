package com.gmail.ak1cec0ld.plugins.spongeexample;

import com.gmail.ak1cec0ld.plugins.spongeexample.listeners.Interact;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "spongeexample", name = "Sponge Example", version = "1.0", description = "Example")
public class SpongeExample {

    @Inject
    private Logger logger;
    public Logger getLogger(){
        return logger;
    }


    public static Key<Value<String>> KEY;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Successfully running ExamplePlugin!!!");

        new Interact(this);
        new PokemonProvider();
    }

}
