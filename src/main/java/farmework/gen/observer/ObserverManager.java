package farmework.gen.observer;

import com.google.common.collect.Lists;
import farmework.gen.model.GenContext;
import farmework.gen.model.WriteFileContext;

import java.util.List;

public class ObserverManager {
    private final List<IGenObserver> genObservers;
    private final List<AbstractWriteObserver> writeObservers;

    private ObserverManager() {
        this.genObservers = Lists.newArrayList(new CreateCodeGenObserver(), new WriteFileGenObserver());

        this.writeObservers = Lists.newArrayList(new CreateFirstWriteObserver(),
                new LoadFileWriteObserver(),
                new CleanFirstWriteObserver(),
                new ReplaceWriteObserver()
        );
    }

    private static class ClassHolder {
        private static final ObserverManager INSTANCE = new ObserverManager();
    }

    public static ObserverManager of() {
        return ClassHolder.INSTANCE;
    }

    public void gen(GenContext context) {
        for (IGenObserver observer : this.genObservers) {
            observer.update(context);
        }
    }

    public void write(WriteFileContext context) {
        for (AbstractWriteObserver observer : this.writeObservers) {
            observer.doWrite(context);
        }
    }
}
