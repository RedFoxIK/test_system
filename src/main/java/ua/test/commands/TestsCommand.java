package ua.test.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestsCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.print("I'm ready to work with you");
    }
}
