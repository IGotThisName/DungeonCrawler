package olmic.dungeoncrawler.util;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class ParticleUtil {

    public static void Line(Location pos1, Location pos2, Color color) {
        // get distance between
        double dist = pos1.toVector().distance(pos2.toVector());

        // loop through for each particle
        for (double i = 0; i < dist; i += 0.1) {

            double progress = i / dist;

            pos2.getWorld().spawnParticle(Particle.REDSTONE,
                    Lerp(pos1.toVector(), pos2.toVector(), progress).toLocation(pos1.getWorld()), 1,
                    new Particle.DustOptions(color, 1));
        }
    }

    public static void Slash(Location pos, Vector direction, double forward, double angle, Color color) {
        Location up = pos.clone();
        up.setPitch(pos.getPitch() + 90F);

        double offset1 = Math.random();
        double offset2 = Math.random();

        if (Math.random()*2 >= 1) {
            offset1 *= -1;
        } else {
            offset2 *= -1;
        }

        Location pos1 = pos
                .add(direction.multiply(forward).rotateAroundAxis(up.getDirection(), angle))
                .add(up.getDirection().multiply(offset1))
                .add(direction.multiply(forward));
        Location pos2 = pos
                .add(direction.multiply(forward).rotateAroundAxis(up.getDirection(), -angle))
                .add(up.getDirection().multiply(offset2))
                .add(direction.multiply(forward));


        ParticleUtil.Line(pos1, pos2, color);
    }

    public static void Circle(Location location, double size, float pSize, Color color) {

        for (int d = 0; d <= 90; d += 1) {
            Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
            particleLoc.setX(location.getX() + Math.cos(d) * size);
            particleLoc.setZ(location.getZ() + Math.sin(d) * size);
            location.getWorld().spawnParticle(Particle.REDSTONE, particleLoc, 1, new Particle.DustOptions(color, pSize));
        }

    }

    private static Vector Lerp(Vector a, Vector b, double f) {
        double x = a.getX() * (1.0 - f) + (b.getX() * f);
        double y = a.getY() * (1.0 - f) + (b.getY() * f);
        double z = a.getZ() * (1.0 - f) + (b.getZ() * f);
        return new Vector(x, y, z);
    }
}
