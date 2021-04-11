package JavaSample;

//Java 9 allows private methods inside the interface
interface utilInterface {
    default int getNoOfCores() {
        return 4;
    }

    static int getNoOfCore2() {
        return 4;
    }

    //use private methods within the default methods, also private method cannot be default method
    private int getCount() {
        return 4;
    }

    private static int getCount2() {
        return 4;
    }
}

public class Sample9 {
    public static void main(String[] args) {
        Resource oldWay = new Resource();
        oldWay.op1();
        try(ResourceNew newWay = new ResourceNew()) {
            newWay.op1();
        }

    }
}

class Resource {

    public Resource() {
        System.out.println("Creating external resource");
    }

    public void op1() {
        System.out.println("op1");
    }

    //finally has been finally deprecated, it was a bad idea
    @Override
    public void finalize() {
        System.out.println("Cleaning up resource...using finalize");
    }
}

class ResourceNew implements AutoCloseable {
    public ResourceNew() {
        System.out.println("Creating external resource");
    }

    public void op1() {
        System.out.println("op2");
    }

    @Override
    public void close() {
        System.out.println("Cleaning up resource...Close called");
    }
}
