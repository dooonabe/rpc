package com.dooonabe.drpc.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wangtt19184 on 2019/2/16.
 */
public class CGLibProxyTest {
    static class Hello {
        public String sayHello() {
            System.out.println("Hello");
            return "Hello";
        }
    }

    static class CGLibProxy implements MethodInterceptor{
        private Hello target;

        public CGLibProxy(Hello target) {
            this.target = target;
        }

        public Object bind(){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(target.getClass());
            enhancer.setCallback(this);
            return enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return methodProxy.invokeSuper(o, objects);
        }
    }
    public static void main(String[] args) {
        Hello hello = (Hello) new CGLibProxy(new Hello()).bind();
        hello.sayHello();
    }
}
