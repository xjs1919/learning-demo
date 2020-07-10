package com.github.xjs.aopdemo.demo1.cglibdemo;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

public class Test4 {
    public static void main(String[] args)throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service4.class);
        //创建2个Callback
        Callback[] callbacks = {
                //这个用来拦截所有insert开头的方法
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        long starTime = System.nanoTime();
                        Object result = methodProxy.invokeSuper(o, objects);
                        long endTime = System.nanoTime();
                        System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
                        return result;
                    }
                },
                //下面这个用来拦截所有get开头的方法，返回固定值的
                new FixedValue() {
                    @Override
                    public Object loadObject() throws Exception {
                        return "路人甲Java";
                    }
                }
        };
        //调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbacks);
        /**
         * 设置过滤器CallbackFilter
         * CallbackFilter用来判断调用方法的时候使用callbacks数组中的哪个Callback来处理当前方法
         * 返回的是callbacks数组的下标
         */
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                //获取当前调用的方法的名称
                String methodName = method.getName();
                /**
                 * 方法名称以insert开头，
                 * 返回callbacks中的第1个Callback对象来处理当前方法，
                 * 否则使用第二个Callback处理被调用的方法
                 */
                return methodName.startsWith("insert") ? 0 : 1;
            }
        });
        Service4 proxy = (Service4) enhancer.create();
        System.out.println("---------------");
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());

    }
}
