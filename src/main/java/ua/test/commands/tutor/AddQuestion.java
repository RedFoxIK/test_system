package ua.test.commands.tutor;

import ua.test.entity.Test;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddQuestion implements ua.test.commands.Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTest = Integer.parseInt(request.getParameter("id_test"));
        Test test = ServiceFactory.getTestService().getTestById(idTest);
        String[] answersId = request.getParameterValues("group");
        List<String> answers = new ArrayList<>();
        Integer numbers = Integer.valueOf(request.getParameter("number_answers"));
        String questionText = request.getParameter("question");
//        boolean multChoice = request.getParameter("mult_choice") != null ? true : false;

//        System.out.println(multChoice);
        for (Integer i = 0; i < numbers; i++ ) {
            answers.add(request.getParameter(i.toString()));
        }
        for ( int i = 0; i < answersId.length; i++ ) {
            System.out.println(answersId[i]);
        }
        ServiceFactory.getQuestionService().addQuestion(idTest, questionText, answers, answersId);
        request.setAttribute("test", test);
        test.addQuestions(ServiceFactory.getTestService().getQuestionsByTestId(idTest));
        request.getRequestDispatcher("/jsp/tutor/test.jsp").forward(request, response);
    }
}
