package network.frostless.dabridge.kits.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;
import java.util.Map;

public class ActionParser {

    private static final Type actionMapType = new TypeToken<Map<String, ActionObject>>() {
    }.getType();

    private static final Gson gson = new GsonBuilder().create();

    public static Map<String, ActionObject> parseActions(ItemStack is) {
        final NBTItem nbti = new NBTItem(is);

        if (nbti.getString("tb_actions") == null) return null;

        return gson.fromJson(nbti.getString("tb_actions"), actionMapType);
    }
}
