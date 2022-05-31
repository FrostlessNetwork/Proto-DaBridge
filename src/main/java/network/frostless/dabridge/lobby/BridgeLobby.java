package network.frostless.dabridge.lobby;

import com.google.common.collect.Maps;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import it.unimi.dsi.fastutil.Pair;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import network.frostless.glacier.Glacier;
import network.frostless.glacier.items.ImmutableItem;
import network.frostless.glacier.lobby.AbstractLobby;
import network.frostless.glacier.utils.LazyLocation;
import network.frostless.glacier.utils.PlayerUtils;
import network.frostless.glacierapi.events.game.LobbyJoinEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.function.Consumer;

public class BridgeLobby extends AbstractLobby {

    private final MiniMessage mm = Glacier.miniMessage;

    private static final Component rightClick = Component.space().append(Component.text("(right click)", NamedTextColor.GRAY));

    @Getter
    private final Map<Integer, Pair<ImmutableItem, Consumer<PlayerInteractEvent>>> items = Maps.newConcurrentMap();

    public BridgeLobby() {
        super(new LazyLocation("TheBridge_InGame_Lobby_Tropic_VM", 0, 0, 0));

        ItemStack mapItem = ItemBuilder.from(Material.MAP)
                .name(mm.deserialize("<reset>Vote for a map").append(rightClick)).build();
        ItemStack kitItem = ItemBuilder.from(Material.IRON_SWORD)
                .name(mm.deserialize("<reset><green>Select Kit").append(rightClick)).build();
        ItemStack leaveItem = ItemBuilder.from(Material.COMPARATOR)
                .name(mm.deserialize("<reset><red>Leave").append(rightClick)).build();

        ImmutableItem mapSelector = new ImmutableItem(mapItem);
        ImmutableItem kitSelector = new ImmutableItem(kitItem);
        ImmutableItem leaveSelector = new ImmutableItem(leaveItem);

        items.put(0, Pair.of(mapSelector, (evt) -> evt.getPlayer().sendMessage("Map selector")));
        items.put(3, Pair.of(kitSelector, (evt) -> evt.getPlayer().sendMessage("Kit Selector")));
        items.put(8, Pair.of(leaveSelector, (evt) -> evt.getPlayer().sendMessage("Leave")));
    }

    @Override
    protected void onLobbyJoin(LobbyJoinEvent event) {
        final Player player = event.getGameUser().getPlayer();

        player.sendMessage(Component.text("You have joined lobby " + Glacier.get().getFrostbite().getServerIdentifier()));

        Glacier.get().getScoreboardManager().provideScoreboard(player, new BridgeLobbyBoard(event.getGameUser()));
        event.getGameUser().getGame().executeUsers(u ->
                u.sendMessage(event.getGameUser().getDisplayName().append(Component.space()).append(mm.deserialize(String.format("<gray>has joined the game %d/%d (%d needed to start)", u.getGame().getPlayers().size(), u.getGame().getMaxPlayers(), u.getGame().getMinPlayers()))))
        );

        PlayerUtils.reset(player);
        giveItems(player);
    }
}
