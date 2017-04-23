package ua.test.commands.tutor;

import ua.test.commands.Command;
import ua.test.entity.Answer;
import ua.test.entity.Question;
import ua.test.entity.Test;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditTest implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("id_test"));
        Test test = ServiceFactory.getTestService().getTestById(testId);
        List<Question> questions = ServiceFactory.getTestService().getQuestionsByTestId(testId);
        for ( Question question: questions) {
            List<Answer> answers = question.getAnswers();
            for ( Answer answer: answers ) {
                System.out.println(answer.isRight());
            }
        }
        test.addQuestions(questions);
        request.setAttribute("test", test);
        System.out.println("1 " + questions);
        System.out.println("2 " + test.getQuestions());
        request.getRequestDispatcher("/jsp/tutor/test.jsp").forward(request, response);
    }
}
