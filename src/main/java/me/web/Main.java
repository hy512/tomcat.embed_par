package me.web;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.JarScanner;
import org.apache.tomcat.util.scan.StandardJarScanFilter;
import org.apache.tomcat.util.scan.StandardJarScanner;


public class Main {

    public static void main(String[] args) throws ServletException, LifecycleException, IOException {
        String hostname = "localhost";
        String contextPath = "/im";
        int port = 8080;
        // org.apache.catalina.startup.

        Tomcat cat = new Tomcat();

        cat.setPort(port);
        cat.setHostname(hostname);
        cat.setBaseDir(getAbsolutePath() + "tomcat.me");

        Host host = cat.getHost();
        host.setAppBase(".");

        Context context = cat.addWebapp(contextPath, getAbsolutePath() + "src/main/webapp");
        // context.addLifecycleListener(new Fix);
        // context.setJarScanner(new EmbededStandardJarScann);
        // cat.getDefaultWebXmlListener();

        ClassLoader classLoader = Main.class.getClassLoader();
        context.setParentClassLoader(classLoader);
        // StandardJarScanFilter jarScanFilter = new StandardJarScanFilter();
        // StandardJarScanner jarScanner = new StandardJarScanner();
        // jarScanFilter.setTldSkip("*.jar");
        // jarScanner.setJarScanFilter(jarScanFilter);
        // JarScanner jarScanner = new EmbededStandardJarScanner();
        // context.setJarScanner(jarScanner);

        cat.start();
        cat.getServer().await();

    }

    private static String getAbsolutePath() {
        String path = null;
        String folderPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath(); // .substring(1);
        if (folderPath.indexOf("target") > 0) {
            path = folderPath.substring(0, folderPath.indexOf("target"));
        }
        return path;
    }
}