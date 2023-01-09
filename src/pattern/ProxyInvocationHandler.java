package pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler<T> implements InvocationHandler {

    private T target;

    public void setTarget(T target) {
        this.target = target;
    }

    public ProxyInvocationHandler(T target) {
        this.target = target;
    }

    public T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method: " + method.getName());
        return method.invoke(target, args);
    }

    public static void main(String[] args) {
        BaseClass baseClass = new BaseClassImpl();
        ProxyInvocationHandler<BaseClass> baseClassProxyInvocationHandler = new ProxyInvocationHandler(baseClass);
        BaseClass proxy = baseClassProxyInvocationHandler.getProxy();
        System.out.println(proxy.methodA("aaa"));
        System.out.println(proxy.methodB("aaa"));
    }
}

interface BaseClass {
    String methodA(String input);

    String methodB(String input);
}

class BaseClassImpl implements BaseClass {
    public String methodA(String input) {
        return "methodA -> " + input;
    }

    public String methodB(String input) {
        return "methodB -> " + input;
    }
}
