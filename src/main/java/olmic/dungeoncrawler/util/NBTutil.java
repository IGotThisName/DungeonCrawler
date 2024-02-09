package olmic.dungeoncrawler.util;

import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTutil {
    public static String getNBT(ItemStack is, String key)
    {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound tags = nmsStack.v();
        if(tags == null)
        {
            return "";
        }
        if(tags.d(key) != 0)
        {
            return tags.l(key);
        }
        else
        {
            return "";
        }
    }

    public static ItemStack setNBT(ItemStack is, String key, String value)
    {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound nbt = nmsStack.v();
        if(nbt == null)
        {
            nbt = new NBTTagCompound();
        }
        // a(a,b) -> putString(a,b)
        nbt.a(key, value);
        // c(nbt) -> setTag(nbt)
        nmsStack.c(nbt);
        is = CraftItemStack.asBukkitCopy(nmsStack);
        return is;
    }

    public static ItemStack removeNBT(ItemStack is, String key)
    {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound nbt = nmsStack.v();
        // c(key) -> remove(key)
        nbt.r(key);
        nmsStack.c(nbt);
        is = CraftItemStack.asBukkitCopy(nmsStack);
        return is;
    }
}
