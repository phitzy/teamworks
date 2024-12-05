package me.spf.teamworks.managers;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.invasion.InvadingGroup;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class InvasionManager {

    public static final @NotNull String INVASION_TOKEN = "Invasion Token";
    private final Teamworks plugin;

    public final List<InvadingGroup> activeGroups; // all created groups
    public final List<InvadingGroup> invadingGroups; // currently invading groups

    public InvasionManager(Teamworks plugin) {
        this.plugin = plugin;
        this.activeGroups = new ArrayList<>(5);
        this.invadingGroups = new ArrayList<>(5);
    }
}
