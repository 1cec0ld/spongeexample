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
        coordToStarter.put("-1287 48 555", EnumSpecies.Cyndaquil);
        coordToStarter.put("-1284 48 555", EnumSpecies.Totodile);
        coordToStarter.put("-1281 48 555", EnumSpecies.Chikorita);
        coordToStarter.put("-4068 51 1551", EnumSpecies.Mudkip);
        coordToStarter.put("-4068 51 1549", EnumSpecies.Torchic);
        coordToStarter.put("-4068 51 1553", EnumSpecies.Treecko);
        coordToStarter.put("703 32 -3218", EnumSpecies.Turtwig);
        coordToStarter.put("699 32 -3218", EnumSpecies.Chimchar);
        coordToStarter.put("701 32 -3218", EnumSpecies.Piplup);
        coordToStarter.put("-4031 69 1553", EnumSpecies.Mudkip);
        coordToStarter.put("-4032 69 1552", EnumSpecies.Torchic);
        coordToStarter.put("-4032 69 1551", EnumSpecies.Treecko);
        coordToStarter.put("-4 57 152", EnumSpecies.Hitmonchan);
        coordToStarter.put("-5 57 152", EnumSpecies.Hitmonlee);
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
