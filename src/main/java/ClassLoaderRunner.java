import java.lang.reflect.Method;

public class ClassLoaderRunner {
    static public void main(String[] args) {
        try {
            String mainClass = args[0];
            String[] arguments = new String[args.length-1];
            System.arraycopy(args, 1, arguments, 0, arguments.length);
            CustomClassLoader ccl = new CustomClassLoader();

            Class mainClassObject = ccl.loadClass(mainClass);
            Class[] mainArgType = {String[].class};
            Method main = mainClassObject.getMethod("main", mainArgType);
            Object[] argsArray = {arguments};
            main.invoke(null, argsArray);

        } catch (Exception e) {
            System.out.println("Error: Incorrect parameters.");
            System.out.println("java ClassLoaderRunner mainclass args");
        }
    }
}
