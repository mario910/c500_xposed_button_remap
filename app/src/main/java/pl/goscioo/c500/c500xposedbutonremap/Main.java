package pl.goscioo.c500.c500xposedbutonremap;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public static final String XML_FILE_NAME = "c500_xposed_btn.xml";

    public static C500RemapConfig config;

    public Main() {

        //String path = String.format("%s/%s", Environment.getExternalStorageDirectory(), XML_FILE_NAME);

        //File configFile = new File(path);
        //if (configFile.exists()) {
        //    try {
        //        XmlMapper xmlMapper = new XmlMapper();
        //        String xml = inputStreamToString(new FileInputStream(configFile));
        //        config = xmlMapper.readValue(xml, C500RemapConfig.class);
        //    }
        //    catch (FileNotFoundException e) {
        //        e.printStackTrace();
        //    } catch (IOException e) {
        //        e.printStackTrace();
        //    }
        //}
        if (config == null)
        {
            config = new C500RemapConfig();
            config.setModeButtonModificationEnabled(true);
            ArrayList<C500RemapConfigModeActivity> modeButtonActivities = new ArrayList<>();
            modeButtonActivities.add(new C500RemapConfigModeActivity("PowerAmp", "com.maxmpz.audioplayer", "com.maxmpz.audioplayer.PlayListActivity"));
            modeButtonActivities.add(new C500RemapConfigModeActivity("Google Music", "com.google.android.music", "com.android.music.MusicBrowserActivity"));
            config.setModeButtonActivities(modeButtonActivities);

            //try {
            //    String path = String.format("%s/%s", Environment.getExternalStorageDirectory(), XML_FILE_NAME);
            //    File configFile = new File(path);
            //    XmlMapper xmlMapper = new XmlMapper();
            //    xmlMapper.writeValue(configFile, config);
            //} catch (JsonGenerationException e) {
            //    e.printStackTrace();
            //} catch (JsonMappingException e) {
            //    e.printStackTrace();
            //} catch (IOException e) {
            //    e.printStackTrace();
            //}
        }



    }

    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals(PACKAGE_NAME) && !loadPackageParam.packageName.equals("com.ts.MainUI"))
            return;

        Log.d(TAG, "In handleLoadPackage");

        try {
            ModeButtonMethodHook modeButtonMethodHook = new ModeButtonMethodHook(config);
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
