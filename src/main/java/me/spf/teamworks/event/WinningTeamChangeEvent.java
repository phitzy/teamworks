package me.spf.teamworks.event;

import me.spf.teamworks.team.TeamName;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class WinningTeamChangeEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private TeamName newWinningTeam;
    private TeamName oldWinningTeam;

    private boolean cancelled;

    public WinningTeamChangeEvent(TeamName newWinningTeam, TeamName oldWinningTeam) {
        this.newWinningTeam = newWinningTeam;
        this.oldWinningTeam = oldWinningTeam;
        this.cancelled = false;
    }

    public TeamName getNewWinningTeam() {
        return newWinningTeam;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public HandlerList getHandlersList() {
        return HANDLERS;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
