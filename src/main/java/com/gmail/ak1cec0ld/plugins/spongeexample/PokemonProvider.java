package com.gmail.ak1cec0ld.plugins.spongeexample;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.enums.items.EnumPokeballs;

import java.util.HashMap;

public class PokemonProvider {

    private static HashMap<String, EnumSpecies> coordToStarter;

    public PokemonProvider(){
        coordToStarter = new HashMap<>();
        coordToStarter.put("-556 51 500", EnumSpecies.Bulbasaur);
        coordToStarter.put("-554 51 500", EnumSpecies.Squirtle);
        coordToStarter.put("-552 51 500", EnumSpecies.Charmander);
    }

    public static Pokemon get(int x, int y, int z){
        EnumSpecies choice = coordToStarter.getOrDefault(x + " " + y + " " + z, null);
        if(choice == null) return null;
        Pokemon starter = Pixelmon.pokemonFactory.create(choice);
        if(starter == null) return null;
        starter.setLevel(5);
        starter.setCaughtBall(EnumPokeballs.CherishBall);
        starter.setGrowth(EnumGrowth.Ordinary);
        return starter;
    }

}
