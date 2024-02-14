package olmic.dungeoncrawler.util;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleUtil {

    public static void Slash(Location pos1, Location pos2) {

        int distance = (int) Math.floor(pos1.distance(pos2));
        int amount = distance * 10;

        Location currentPos = pos1;
        Location relativePos2 = pos2.subtract(pos1);
        Location increment = relativePos2.multiply(amount^-1);

        for (int i = 0; i < amount + 1; i++) {

            ParticleBuilder particle = new ParticleBuilder(Particle.REDSTONE);
            particle.location(currentPos);
            particle.spawn();

            currentPos = currentPos.add(increment);
        }
    }
}
