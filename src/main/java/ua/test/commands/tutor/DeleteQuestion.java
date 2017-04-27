package ua.test.commands.tutor;

import ua.test.commands.Command;
import ua.test.entity.Test;
import ua.test.services.QuestionService;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteQuestion implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idQuestion = Integer.parseInt(request.getParameter("id_question"));
        int idTest = Integer.parseInt(request.getParameter("id_test"));
        ServiceFactory.getQuestionService().deleteQuestion(idQuestion);
        Test test = ServiceFactory.getTestService().getTestById(idTest);

        test.setQuestions(ServiceFactory.getTestService().getQuestionsByTestId(idTest));
        request.setAttribute("test", test);
        response.sendRedirect("/testing_system/tutor/test?id_test="+test.getId());
    }
}
