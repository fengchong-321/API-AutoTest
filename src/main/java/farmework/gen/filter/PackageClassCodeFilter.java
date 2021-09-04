package farmework.gen.filter;

import com.google.common.base.Strings;
import farmework.gen.model.GenContext;

public class PackageClassCodeFilter implements ICreateCodeFilter {

    @Override
    public void doFilter(GenContext context) {
        String packageName = context.getPackageName();
        if (Strings.isNullOrEmpty(packageName)) {
            return;
        }

//        List<Class<?>> classList = ClassPathUtils.listClassByPackage(packageName).parallelStream()
//        List<Class<?>> classList = Lists.newArrayList().parallelStream()
//                .filter(clazz -> {
//                    if (Strings.isNullOrEmpty(context.getPrefixClassName())) {
//                        return true;
//                    }
////                    return clazz.getName().startsWith(context.getPrefixClassName());
//                    return false;
//                })
//                .filter(clazz -> {
//                    if (Strings.isNullOrEmpty(context.getSuffixClassName())) {
//                        return true;
//                    }
//
//                    return Objects.isNull(clazz);
////                    return clazz.getName().endsWith(context.getSuffixClassName());
//                }).collect(Collectors.toList());

//        context.addForBeanSetterClasses(classList);
    }
}
