package pl.goscioo.c500.c500xposedbutonremap;

import java.util.List;

/**
 * Created by MariuszKnurowski on 1/30/2018.
 */

public class C500RemapConfig {
    public boolean isModeButtonModificationEnabled() {
        return IsModeButtonModificationEnabled;
    }

    public void setModeButtonModificationEnabled(boolean modeButtonModificationEnabled) {
        IsModeButtonModificationEnabled = modeButtonModificationEnabled;
    }

    public List<C500RemapConfigModeActivity> getModeButtonActivities() {
        return ModeButtonActivities;
    }

    public void setModeButtonActivities(List<C500RemapConfigModeActivity> modeButtonActivities) {
        ModeButtonActivities = modeButtonActivities;
    }

    private boolean IsModeButtonModificationEnabled;
    private List<C500RemapConfigModeActivity> ModeButtonActivities;
}

