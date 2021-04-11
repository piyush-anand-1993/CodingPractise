public class PrintInOrder {
    int printCounter;

    public PrintInOrder() {
        printCounter = 0;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (this) {
            while(printCounter != 0)
                this.wait();
            printFirst.run();
            printCounter = 1;
            this.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (this) {
            while(printCounter != 1)
                this.wait();
            printSecond.run();
            printCounter = 2;
            this.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (this) {
            while (printCounter != 2)
                this.wait();
            printThird.run();
            printCounter = 0;
            this.notifyAll();
        }
    }
}
