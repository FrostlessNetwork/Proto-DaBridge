package network.frostless.dabridge.map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import network.frostless.glacierapi.map.SimpleMapMeta;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class BridgeMapMeta extends SimpleMapMeta {

    private Map<String, Location> teamSpawns = new HashMap<>();

    private List<Location> shopSpawns = new ArrayList<>();
}
