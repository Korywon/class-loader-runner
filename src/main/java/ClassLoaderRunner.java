import java.lang.reflect.Method;

public class ClassLoaderRunner {
    private static long pid;

    static public void main(String[] args) {
        try {
            // sets up main class arguments
            String mainClass = args[0];
            String[] arguments = new String[args.length-1];
            System.arraycopy(args, 1, arguments, 0, arguments.length);
            CustomClassLoader ccl = new CustomClassLoader();

            // loads and creates the main class
            Class mainClassObject = ccl.loadClass(mainClass);
            Class[] mainArgType = {String[].class};
            Method main = mainClassObject.getMethod("main", mainArgType);
            Object[] argsArray = {arguments};

            // invokes the main method
            main.invoke(null, argsArray);

            StackTraceElement[] ste = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : ste) {
                System.out.println("ClassLoaderRunner: Loaded method:  " + element.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: Incorrect parameters.");
            System.out.println("java ClassLoaderRunner mainclass args");
        }
    }
}
