package farmework.profile;

import com.google.common.base.Strings;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ProfileHolder {

    private volatile AtomicBoolean isProfileSet = new AtomicBoolean(false);

    private volatile String active;

    private ProfileHolder() {

    }

    private static final class ClassHolder {
        private static final ProfileHolder INSTANCE = new ProfileHolder();
    }

    public static ProfileHolder of() {
        return ClassHolder.INSTANCE;
    }

    public void setProfile(String profile) {
        if (isProfileSet.get()) {
            return;
        }
        this.active = profile;
        this.isProfileSet.set(true);
    }

    public String getProfile() {
        return Strings.isNullOrEmpty(this.active) ? "default" : this.active;
    }
}
