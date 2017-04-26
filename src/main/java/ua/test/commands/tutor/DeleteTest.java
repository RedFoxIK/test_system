package ua.test.commands.tutor;

import ua.test.entity.Test;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteTest implements ua.test.commands.Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTest = Integer.parseInt(request.getParameter("id_test"));
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");

        ServiceFactory.getTestService().deleteTestById(idTest);
        List<Test> tests = ServiceFactory.getTestService().getTestsByUserId(idUser);
        request.setAttribute("tests", tests);
        request.getRequestDispatcher("/pages/tutor/tests.jsp");
    }
}
