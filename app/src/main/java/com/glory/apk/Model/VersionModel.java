package com.glory.apk.Model;

public class VersionModel {
    String android_versions;
    String forceble;

    public VersionModel(String android_versions, String forceble) {
        this.android_versions = android_versions;
        this.forceble = forceble;
    }

    public String getAndroid_versions() {
        return android_versions;
    }

    public void setAndroid_versions(String android_versions) {
        this.android_versions = android_versions;
    }

    public String getForceble() {
        return forceble;
    }

    public void setForceble(String forceble) {
        this.forceble = forceble;
    }
}
