import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/*
 * Immutable Maps initialization
 * Type witness
 */
public class JavaTopic {

    public void fun(List<Integer> list) {

    }

    public void fun(Set<Integer> set) {

    }

    public static void main(String args[]) throws IOException {
        Map<Integer, Integer> testMap = Map.of(1, 1, 2,2 , 3, 3);

        for(Map.Entry e: testMap.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }

        JavaTopic obj = new JavaTopic();
        List<Integer> abc = new ArrayList<>();
        obj.fun((List<Integer>) anyObject());
        obj.fun(JavaTopic.<List<Integer>>anyObject());

        // auto closable streams

        //before
        InputStream oldWay = new FileInputStream("someFile.txt");
        try {
            //do something
        }
        finally {
            try{
                oldWay.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        //what you should have actually done
        try{
            //do something
            oldWay.close();
        }
        catch (IOException e) {
            try{
                oldWay.close();
            }
            catch (IOException e1) {
                e.addSuppressed(e1);
            }
            throw e;
        }

        //new way
        try(InputStream newWay = new FileInputStream("someFile.txt")) {
            //do stuff
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T anyObject() {
        return null;
    }
}
