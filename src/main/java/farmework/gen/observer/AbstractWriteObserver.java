package farmework.gen.observer;


import farmework.gen.model.WriteFileContext;

public abstract class AbstractWriteObserver {
    protected abstract boolean preWrite(WriteFileContext context);

    protected abstract void onWrite(WriteFileContext context);

    public void doWrite(WriteFileContext context) {
        if (preWrite(context)) {
            onWrite(context);
        }
    }
}
