package com.example.demo.java.dynamicproxy.generateProxy;

import com.example.demo.java.dynamicproxy.Person;

import java.lang.reflect.*;

public final class MyProxy extends Proxy implements Person {
    private static Method m1;
    private static Method m2;
    private static Method m3;
    private static Method m0;

    public MyProxy(InvocationHandler paramInvocationHandler) {
        super(paramInvocationHandler);
    }

    public final boolean equals(Object paramObject) {
        try {
            return ((Boolean)this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
        } catch (Error|RuntimeException error) {
            throw null;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }
    public final String toString() {
        try {
            return (String)this.h.invoke(this, m2, null);
        } catch (Error|RuntimeException error) {
            throw null;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public final void giveMoney() {
        try {
            this.h.invoke(this, m3, null);
            return;
        } catch (Error|RuntimeException error) {
            throw null;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public final int hashCode() {
        try {
            return ((Integer)this.h.invoke(this, m0, null)).intValue();
        } catch (Error|RuntimeException error) {
            throw null;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
            m3 = Class.forName("com.example.demo.java.dynamicproxy.Person").getMethod("giveMoney", new Class[0]);
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
        } catch (NoSuchMethodException noSuchMethodException) {
            throw new NoSuchMethodError(noSuchMethodException.getMessage());
        } catch (ClassNotFoundException classNotFoundException) {
            throw new NoClassDefFoundError(classNotFoundException.getMessage());
        }
    }
}
