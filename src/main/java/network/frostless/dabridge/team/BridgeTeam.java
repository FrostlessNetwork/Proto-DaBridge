package network.frostless.dabridge.team;

import lombok.Getter;
import lombok.Setter;
import network.frostless.dabridge.user.BridgeUser;
import network.frostless.glacier.team.Team;
import network.frostless.glacier.team.TeamColor;
import org.bukkit.Location;

@Getter
@Setter
public class BridgeTeam extends Team<BridgeUser> {

    private Location spawnPoint;
    public BridgeTeam(int size, TeamColor color) {
        super(size);
        setTeamColor(color);
    }

}
