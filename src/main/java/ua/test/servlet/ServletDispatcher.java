package ua.test.servlet;

import ua.test.commands.Command;
import ua.test.commands.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatcher extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.getInstance().getCommand(request);

        System.out.println(request.getRequestURI());
        if ( command == null ) {
            request.getRequestDispatcher("/jsp/errors/404.jsp").forward(request, response);
            return;
        }
        command.execute(request, response);
    }
}
