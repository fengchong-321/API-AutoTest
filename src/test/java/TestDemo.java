import farmework.alarm.callback.DefaultAlarmCallback;
import farmework.annotation.*;

public class TestDemo {
    @AutoTest // 第一步, 在这个注解里 加有 @ExtendWith(CaseFormatExtension.class)， 指定的就是后续再流程当中要去回调的类
    @CaseTitle("说清楚这个case是干啥的") // 首先给case加标题便于后续查找和区分，必填
    @CaseDesc(desc = "术语哪个需求，", owner = "zhangsan")
    @CheckPoint("这是第一个检查点，表示这条case的测试重点之1") // 为case添加检查点，必填
    @CheckPoint("这是第一个检查点，表示这条case的测试重点之2")
//    @CaseTag(key = "project", val = "meituan") // 为case添加标签方便后续运行时做筛选，必填
//    @CaseTag(key = "module", val = "pay")
//    @CaseTag(key = "level", val = "normal")
    @CaseTag(key = "", val = "normal")
    public void test222() {  // Method
        // 预期的case编写样式.
        // 1.要扩展出自己的标识
        // TODO 2.扩展出去之后要用于做什么？给到平台，做case管理，做case评审时使用
        // 3.有必填项，也有非必填，那必填项该如何控制其必填？ 不管是否为必填项，必要的参数校验也是需要处理的
    }

    @AutoTest
    @CaseTitle("说清楚这个case是干啥的") // 首先给case加标题便于后续查找和区分，必填
    @CaseDesc(desc = "术语哪个需求，", owner = "zhangsan")
//    @TripalAlarm(token = "")
    @CheckPoint("这是第一个检查点，表示这条case的测试重点之1") // 为case添加检查点，必填
    @CheckPoint("这是第一个检查点，表示这条case的测试重点之2")
//    @CaseTag(key = "", val = "meituan") // 为case添加标签方便后续运行时做筛选，必填
//    @CaseTag(key = "xxx", val = "") // 为case添加标签方便后续运行时做筛选，必填
    @CaseTag(key = "project", val = "meituan") // 为case添加标签方便后续运行时做筛选，必填
    @CaseTag(key = "module", val = "pay")
//    @CaseTag(key = "", val = "xxx")
    public void test333() {
        // 3.有必填项，也有非必填，那必填项该如何控制其必填？ 不管是否为必填项，必要的参数校验也是需要处理的

        // IllegalFormatException

        System.out.println("TestDemo.test333 ========================= ");
    }
}
