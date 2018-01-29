package pl.goscioo.c500.c500xposedbutonremap;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
/**
 * Created by MariuszKnurowski on 1/29/2018.
 */

public class ButtonRemapHook extends XC_MethodHook {

    public static final String HOOKED_CLASS_NAME = "Class";
    public static final String HOOKED_METHOD_NAME = "Method";

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

    }
}
