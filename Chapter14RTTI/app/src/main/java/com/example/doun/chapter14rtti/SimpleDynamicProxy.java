//: typeinfo/SimpleDynamicProxy.java
package com.example.doun.chapter14rtti;

import java.lang.reflect.*;

class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() +
                ", method: " + method + ", args: " + args);
//        System.out.println("**** proxy: " + proxy +//默认调用tostring，出现了java.lang.StackOverflowError错误。
//                ", method: " + method + ", args: " + args);
//        @Override
//        public final String toString() {
//            try {
//                return (String) super.h.invoke(this, m2, null);
//            } catch (Throwable throwable) {
//                throw new UndeclaredThrowableException(throwable);
//            }
//        }
//        可以看到，调用toString方法的时候，调用了h的invoke方法，而h就是InvocationHandler的实例，所以是
// 递归调用，所以就会出现上述所说的java.lang.StackOverflowError错误。
//        这里顺便说一下简单编写java动态代理的过程：
//
//        1.定义一个接口I
//        2.编写该接口I的实现类Impl
//        3.编写InvocationHandler接口的实现类H，构造H类对象的时候可以把要代理的对象target传入，target
// 完成实际的动作。在里面的invoke方法里编写自己想实现的逻辑，然后再调用实际要完成的动作就可以。
//        4.调用Proxy.newProxyInstance方法，传递的三个参数分别是代理类的类加载器（可以用Impl实例的
// getClass().getClassLoader()）
//、代理类要实现的接口列表（可以用Impl实例getClass().getInterfaces()）、InvocationHandler实现类的实例。
//
//        这样就生成了$Proxy0类的对象，由于$Proxy0类实现了I接口，所以可以将对象强制转型成I。
//
//
//        再说一下Proxy.newProxyInstance方法的实际过程：
//        1.使用传入的InvocationHandler实例参数将Proxy类的h实例初始化，注意，如果传入空对象的话，会抛出空
// 指针错误，即h不能为空。
//        2.运行时生成代理Class,即$Proxy0
//        3.利用上面动态生成的$Proxy0类，构造出该类的对象，并返回。
        if (args != null)
            for (Object arg : args)
                System.out.println("  " + arg);
        return method.invoke(proxied, args);
    }
}

class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        // Insert a proxy and call again:
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{Interface.class},
                new DynamicProxyHandler(real));
        consumer(proxy);
    }
} /* Output: (95% match)	
doSomething
somethingElse bonobo
**** proxy: class $Proxy0, method: public abstract void Interface.doSomething(), args: null
doSomething
**** proxy: class $Proxy0, method: public abstract void Interface.somethingElse(java.lang.String), args: [Ljava.lang.Object;@42e816
  bonobo
somethingElse bonobo
*///:~
