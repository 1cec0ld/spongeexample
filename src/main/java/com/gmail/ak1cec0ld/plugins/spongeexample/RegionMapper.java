package com.gmail.ak1cec0ld.plugins.spongeexample;

import org.spongepowered.api.world.Location;

public class RegionMapper {

    public static String getRegion(Location loc){
        int x = loc.getBlockX();
        int z = loc.getBlockZ();

        if(inside(x,z,-640,-493, 400, 566)) return "kanto";
        if(inside(x,z,-1339, -1188, 474, 650)) return "johto";
        if(inside(x,z, -4119,-4043 ,1502 , 1601)) return "hoenn";
        if(inside(x,z, 535, 800, -3320, -3049)) return "sinnoh";

        return null;
    }

    private static boolean inside(int xval, int zval, int xmin, int xmax, int zmin, int zmax){
        return xmin < xval && xval < xmax && zmin < zval && zval < zmax;
    }

}
