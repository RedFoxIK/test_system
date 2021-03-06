package ua.test.servlet;

import org.apache.log4j.Logger;
import ua.test.commands.Command;
import ua.test.commands.CommandFactory;
import ua.test.constants.Links;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatcher extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ServletDispatcher.class);

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

        if ( command == null ) {
            request.getRequestDispatcher(Links.Jsp.ERROR404_PAGE).forward(request, response);
            return;
        }
        command.execute(request, response);
    }

}
