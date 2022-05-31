package network.frostless.dabridge;

import network.frostless.dabridge.events.GracePeriodEvent;
import network.frostless.dabridge.map.BridgeMapMeta;
import network.frostless.dabridge.team.BridgeTeam;
import network.frostless.dabridge.user.BridgeUser;
import network.frostless.glacier.countdown.impl.DefaultGameStartCountdown;
import network.frostless.glacier.game.SimpleGame;
import network.frostless.glacier.team.TeamColor;
import network.frostless.glacierapi.map.MapMeta;
import org.bukkit.Location;

public class BridgeGame extends SimpleGame<BridgeUser, BridgeTeam> {

    TeamColor[] teams = new TeamColor[]{
            TeamColor.RED,
            TeamColor.BLUE
    };

    public BridgeGame() {
        super(8, 32);
    }

    @Override
    protected void init() {
        addCountdown(new DefaultGameStartCountdown<>(60, 8, this));

        for (TeamColor team : teams) {
            BridgeTeam bridgeTeam = new BridgeTeam(16, team);
            getTeams().add(bridgeTeam);
        }
        addGameEvent(new GracePeriodEvent());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean canEnd() {
        return false;
    }

    @Override
    public void endGame() {

    }

    @Override
    public void applyMapMapper(MapMeta mapMeta) {
        BridgeMapMeta bridgeMeta = (BridgeMapMeta) mapMeta;
        for (BridgeTeam team : getTeams()) {
            Location location = bridgeMeta.getTeamSpawns().get(team.getTeamColor().name());
            if (location != null) {

                final Location normalizedWorld = location.clone();
                System.out.println(getWorld());
                System.out.println("Normalized world: " + getWorld());
                normalizedWorld.setWorld(getWorld());
                logger.info("{} is spawning at [{},{},{}]", team.getTeamColor().name(), normalizedWorld.getX(), normalizedWorld.getY(), normalizedWorld.getZ());
                team.setSpawnPoint(normalizedWorld);
            }
        }
    }
}
