package practise;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class JarHellLab {
    public static void main(String[] args) throws Exception {
        findResource();
    }

    static void findResource() throws Exception {
//        Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getSystemResources("practise/JarHellLab.class");
//        Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources("java/lang/System.class");
//        Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources("practise/app.properties");
        Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources(JarHellLab.class.getName().replace('.', '/') + ".class");
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            System.out.println(resource);
        }
    }

    static void printClassPath() {
        for(String path : System.getProperty("java.class.path").split(":")) {
            System.out.println(path);
        }
    }

    static void loadResource() {
        try (Scanner scanner = new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream("practise/app.properties"))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    static void listPackages() {
        List<String> packages = new ArrayList<>();
        for(Package pack : Package.getPackages()) {
            packages.add(pack.getName());
        }
        Collections.sort(packages);
        for(String pack : packages) {
            System.out.println(pack);
        }
    }
}
