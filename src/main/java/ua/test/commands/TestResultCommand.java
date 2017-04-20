package ua.test.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestResultCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String[] test1 = request.getParameterValues("1");
        String[] test2 = request.getParameterValues("4");

        if ( test1!= null ) {
            for ( String s: test1 ) {
                System.out.println(s);
            }
        } else {
            System.out.println("NULL");
        }


        if ( test2 != null ) {
            for ( String s: test2 ) {
                System.out.println(s);
            }
        } else {
            System.out.println("NULL");
        }

        pw.print("I'm ready");
    }
}
