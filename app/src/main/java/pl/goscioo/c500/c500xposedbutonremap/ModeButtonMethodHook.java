package pl.goscioo.c500.c500xposedbutonremap;
import android.app.AndroidAppHelper;
import android.content.pm.PackageManager;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
/**
 * Created by MariuszKnurowski on 1/29/2018.
 */

public class ModeButtonMethodHook extends XC_MethodHook {

    public static final String TAG = "ModeButtonMethodHook";

    public static final String HOOKED_CLASS_NAME = "com.ts.main.common.WinShow";
    public static final String HOOKED_METHOD_NAME = "DealModeKey";

    public C500RemapConfig config;

    private static int currentModeIndex = 0;

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        if (config.isModeButtonModificationEnabled() && !config.getModeButtonActivities().isEmpty()) {
            Log.d(TAG, "In beforeHookedMethod for MODE button");

            C500RemapConfigModeActivity currentModeActivity = config.getModeButtonActivities().get(currentModeIndex);

            // todo: skip to next if already in foreground?
            boolean isAppInst = isAppInstalled(currentModeActivity.getAppPackage());
            String msg = String.format("Is app %s installed: %s", currentModeActivity.getAppPackage(), isAppInst);
            Log.d(TAG, msg);

            try {
                showApp(currentModeActivity.getAppName(), currentModeActivity.getActivityName());
                // todo: option to send play signal?
            }
            catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            currentModeIndex++;
            if (currentModeIndex >= config.getModeButtonActivities().size())
                currentModeIndex = 0;

            param.setResult(null);
        }
    }


    private void showApp(String pkg, String activity) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(pkg, activity));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AndroidAppHelper.currentApplication().startActivity(intent);
    }

    private boolean isAppInstalled(String uri) {
        PackageManager pm = AndroidAppHelper.currentApplication().getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
}
