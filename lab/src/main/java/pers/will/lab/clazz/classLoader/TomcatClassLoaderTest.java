package pers.will.lab.clazz.classLoader;

import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.Bootstrap;
import org.apache.catalina.startup.ClassLoaderFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TomcatClassLoaderTest extends HttpServlet {

    public static void main(String[] args) {
        Bootstrap bs;
        ClassLoaderFactory cf;
        WebappLoader wl;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ClassLoader loader = this.getClass().getClassLoader();
        while (loader != null) {
            out.write(loader.getClass().getName() + "<br/>");
            loader = loader.getParent();
        }
        out.write(String.valueOf(loader));
        out.flush();
        out.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
