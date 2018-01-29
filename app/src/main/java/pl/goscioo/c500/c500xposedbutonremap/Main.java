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

    public static final String PACKAGE_NAME = "com.android.systemui";

    public Main() {
        // todo: create setting container? load settings from xml file for nowand expose them to hooks
    }

    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals(PACKAGE_NAME))
            return;


        ModeButtonMethodHook modeButtonMethodHook = new ModeButtonMethodHook();
        findAndHookMethod(modeButtonMethodHook.HOOKED_CLASS_NAME, loadPackageParam.classLoader, modeButtonMethodHook.HOOKED_METHOD_NAME, modeButtonMethodHook);


        ButtonRemapHook buttonRemapHook = new ButtonRemapHook();
        findAndHookMethod(buttonRemapHook.HOOKED_CLASS_NAME, loadPackageParam.classLoader, buttonRemapHook.HOOKED_METHOD_NAME, buttonRemapHook);

    }
}
