package farmework.format.observer;

import com.google.common.collect.Lists;
import farmework.format.FormatObserver;

import java.lang.reflect.Method;
import java.util.List;

public enum FormatManager {
    INSTANCE;

    private final List<FormatObserver> observers;

    FormatManager() {
        // 第五步: 注册所有消息变更时的观察者. 在第四步被调用时，整个链条的观察者都会被执行
        this.observers = Lists.newArrayList(new CaseTagFormatObserver(),
                new CheckPointFormatObserver(),
                new DingTalkFormatObserver(),
                new CaseDescFormatObserver(),
                new CaseTitleFormatObserver(),
                new CaseGroupFormatObserver());
    }

    // 第四步,回调消息到达,触发所有观察者来处理我们的消息.
    public void doFormatCheck(Method testMethod) {
        for (FormatObserver observer : this.observers) {
            observer.format(testMethod);
        }
    }
}
