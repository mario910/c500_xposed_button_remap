package pl.goscioo.c500.c500xposedbutonremap;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
/**
 * Created by MariuszKnurowski on 1/29/2018.
 */

public class Main implements IXposedHookLoadPackage {

    public static final String TAG = "C500HookInit";

    public static final String PACKAGE_NAME = "com.ts.main.common";

    public C500RemapConfig config;

    public Main() {
        // todo: create setting container? load settings from xml file for nowand expose them to hooks
        config = new C500RemapConfig();
        config.setModeButtonModificationEnabled(true);
        ArrayList<C500RemapConfigModeActivity> modeButtonActivities = new ArrayList<>();
        modeButtonActivities.add(new C500RemapConfigModeActivity("PowerAmp", "com.maxmpz.audioplayer", "com.maxmpz.audioplayer.PlayListActivity"));
        modeButtonActivities.add(new C500RemapConfigModeActivity("Google Music", "com.google.android.music", "com.android.music.MusicBrowserActivity"));
        config.setModeButtonActivities(modeButtonActivities);

    }

    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals(PACKAGE_NAME))
            return;

        try {
            ModeButtonMethodHook modeButtonMethodHook = new ModeButtonMethodHook();
            findAndHookMethod(modeButtonMethodHook.HOOKED_CLASS_NAME, loadPackageParam.classLoader, modeButtonMethodHook.HOOKED_METHOD_NAME, modeButtonMethodHook);
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        // try {
        // ButtonRemapHook buttonRemapHook = new ButtonRemapHook();
        // findAndHookMethod(buttonRemapHook.HOOKED_CLASS_NAME, loadPackageParam.classLoader, buttonRemapHook.HOOKED_METHOD_NAME, buttonRemapHook);
        // }
        // catch (Exception e) {
        //     Log.e(TAG, e.getMessage());
        // }
    }
}
