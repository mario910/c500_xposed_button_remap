package pl.goscioo.c500.c500xposedbutonremap;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import android.widget.TextView;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
/**
 * Created by MariuszKnurowski on 1/29/2018.
 */

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals("com.android.systemui"))
            return;


        findAndHookMethod("com.android.systemui.statusbar.policy.Clock", loadPackageParam.classLoader, "updateClock", new ModeButtonMethodHook());
    }
}
