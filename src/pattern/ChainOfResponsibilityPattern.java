package pattern;

public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        Handler handlerA = new HandlerA();
        Handler handlerB = new HandlerB();
        handlerA.setNext(handlerB);
        handlerA.handleRequest("HandlerA");
        handlerA.handleRequest("HandlerB");
        handlerA.handleRequest("Handler?");
    }
}

abstract class Handler {
    private Handler next;
    public void setNext(Handler next) {
        this.next = next;
    }
    public void handleRequest(String input) {
        if (this.doJob(input) == 0) {
            System.out.println(String.format("当前handler: %s 已经处理完成", this.getClass().getName()));
            return;
        }
        if (this.next != null) {
            this.next.handleRequest(input);
        } else {
            System.out.println("所有handler均无法处理");
        }
    }
    public abstract int doJob(String input);
}

class HandlerA extends Handler {

    @Override
    public int doJob(String input) {
        if ("HandlerA".equals(input)) {
            return 0;
        } else {
            return 1;
        }
    }
}

class HandlerB extends Handler {

    @Override
    public int doJob(String input) {
        if ("HandlerB".equals(input)) {
            return 0;
        } else {
            return 1;
        }
    }
}
