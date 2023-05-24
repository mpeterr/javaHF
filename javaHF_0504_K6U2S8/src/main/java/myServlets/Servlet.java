package myServlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> eredmenyek = new ArrayList<>();
        String filePath = getServletContext().getRealPath("masodfokumegoldasok.txt");//ezzel a snapshot mappába kerülünk, de legalább elérjük a fájlt
        try (BufferedReader in = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            String line = null;
            while ((line = in.readLine()) != null){
                String[] eredmeny = line.split("\t");
                for (int i = 0; i < eredmeny.length; i++) {
                    eredmenyek.add(eredmeny[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        request.setAttribute("eredmenyek", eredmenyek);
        request.getRequestDispatcher("eddigieredmenyek.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double a = Double.parseDouble(request.getParameter("a"));
        double b = Double.parseDouble(request.getParameter("b"));
        double c = Double.parseDouble(request.getParameter("c"));

        request.setAttribute("a", a);
        request.setAttribute("b", b);
        request.setAttribute("c", c);

        double diszkriminans = b * b - 4 * a * c;
        request.setAttribute("diszkriminans", diszkriminans);
        double gyok;
        double gyok1;
        double gyok2;
        String output;

        //Path filePath = Paths.get("masodfokumegoldasok.txt");
        //hogyha a Paths.get("masodfokumegoldasok.txt") metódust használom, akkor a userdirectoryt használja,
        // ami ide mutat: C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin, ide hoz létre, ha létezik, ha nem a fájl
        // Itt valóban megtalálható a masodfokumegolsasok.txt benne az összes próbálkozással.
        //String userDirectory = System.getProperty("user.dir");
        //System.out.println(userDirectory);//ezzel találtam meg, hogy hova hozza létre a cuccot

        String filePath = getServletContext().getRealPath("masodfokumegoldasok.txt");//ezzel a snapshot mappába kerülünk, de legalább elérjük a fájlt

        //String filePath = "C:\\Users\\Péter\\source\\Egyetem\\Java\\javaHF_0504_K6U2S8\\masodfokumegoldasok.txt";//Ez lenne az abszolút útvonal
        File file = new File(filePath/*.toString()*/);//toString csak az első Path megoldás miatt kellene

        if (diszkriminans > 0) {
            gyok1 = (-b + Math.sqrt(diszkriminans)) / (2 * a);
            gyok2 = (-b - Math.sqrt(diszkriminans)) / (2 * a);

            try(FileWriter out = new FileWriter(file, true)){
                output = "Paraméterek: a: " + a + " b: " + b + " c: " + c +"\tMegoldás: x1=" + gyok1 + ", x2=" + gyok2 + "\n";
                out.write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
            request.setAttribute("gyok1", gyok1);
            request.setAttribute("gyok2", gyok2);
        } else if (diszkriminans == 0) {
            gyok = -b / (2 * a);

            try(FileWriter out = new FileWriter(file, true)){
                output = "Paraméterek: a: " + a + " b: " + b + " c: " + c +"\tMegoldás: x1=" + gyok + "\n";
                out.write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }

            request.setAttribute("gyok", gyok);
        } else {
            try(FileWriter out = new FileWriter(file, true)){
                output = "Paraméterek: a: " + a + " b: " + b + " c: " + c +"\tMegoldás: Nincs gyök" + "\n";
                out.write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("eredmeny.jsp").forward(request, response);
    }
}
