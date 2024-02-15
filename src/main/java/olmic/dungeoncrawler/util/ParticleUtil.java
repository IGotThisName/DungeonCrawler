package olmic.dungeoncrawler.util;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class ParticleUtil {

    public static void Slash(Location pos1, Location pos2) {

        pos1.subtract(pos2);

        double distance = pos1.distance(pos2);
        Vector direction = pos1.subtract(pos2).toVector();

        for (double i = 0; i < distance; i += 0.1) {

            Location spawn = pos1.add(direction.normalize().multiply(0.1));

            spawn.getWorld().spawnParticle(Particle.REDSTONE, spawn, 1, new Particle.DustOptions(Color.fromRGB(189,
                    242, 255), 1));

        }
    }
}
