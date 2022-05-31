package network.frostless.dabridge.lobby;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import network.frostless.glacier.scoreboard.GameBoard;
import network.frostless.glacierapi.user.GameUser;
import org.apache.commons.lang.StringUtils;

public class BridgeLobbyBoard extends GameBoard {
    public BridgeLobbyBoard(GameUser gameUser) {
        super(gameUser, getObjectiveText());
    }

    @Override
    public void updateBoard() {
        String[] lines = new String[]{
                StringUtils.center("<gray><dtf>", getObjectiveText().length() + 4),
                " ",
                String.format("<gray>Status: <yellow>%s", "Voting"),
                String.format("<gray>Selected map: <yellow>%s", "Monkey Island"),
                String.format("<gray>Selected kit: <yellow>%s", "None"),
                " ",
                String.format("<gray>Players: (%d/%d)", game.getPlayers().size(), game.getMaxPlayers()),
                " ",
                "<aqua>play.frostless.network"
        };

        lines = formatPC(lines);

        updateLines(lines);

    }
    private static String getObjectiveText() {
        Component component = Component
                .text("The Bridge").color(TextColor.color(0xFAEF38)).decorate(TextDecoration.BOLD);

        return LegacyComponentSerializer.legacySection().serialize(component);
    }
}
