package pl.goscioo.c500.c500xposedbutonremap;

public class C500RemapConfigModeActivity {
    private String appName;

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    private String appPackage;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    private String activityName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public C500RemapConfigModeActivity(String appName, String appPackage, String activityName) {
        this.appName = appName;
        this.appPackage = appPackage;
        this.activityName = activityName;
    }

    public C500RemapConfigModeActivity() {
    }
}
