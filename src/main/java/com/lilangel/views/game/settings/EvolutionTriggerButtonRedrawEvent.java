package com.lilangel.views.game.settings;

import com.lilangel.views.DrawEvent;

public class EvolutionTriggerButtonRedrawEvent extends DrawEvent {
    public final boolean evolutionActive;
    public EvolutionTriggerButtonRedrawEvent(Object source, int id, String command, boolean isEvolutionActive) {
        super(source, id, command);
        this.evolutionActive = isEvolutionActive;
    }
}
