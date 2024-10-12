package me.spf.teamworks.managers;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.invasion.InvadingGroup;

import java.util.ArrayList;
import java.util.List;

public class InvasionManager {

    private final Teamworks plugin;

    public List<InvadingGroup> activeGroups;
    public List<InvadingGroup> invadingGroups;

    public InvasionManager(Teamworks plugin) {
        this.plugin = plugin;
        this.activeGroups = new ArrayList<>(5);
        this.invadingGroups = new ArrayList<>(5);
    }
}
