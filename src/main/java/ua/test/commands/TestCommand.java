package ua.test.commands;

import ua.test.entity.Answer;
import ua.test.entity.Question;
import ua.test.services.ServiceFactory;
import ua.test.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TestCommand implements Command {
    TestService testService = ServiceFactory.getTestService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = null;
        int testId = Integer.parseInt(request.getParameter("id_test"));

        try {
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.setAttribute("test_name", testService.getTestCaptionById(testId));
        request.setAttribute("questions", testService.getQuestionsByTestId(testId));
        List<Question> questions = new ArrayList<>();
        questions = testService.getQuestionsByTestId(testId);

        for ( Question question: questions ) {
            System.out.println(question);
            List<Answer> answers = question.getAnswers();
            for ( Answer answer: answers ) {
                System.out.println(answer);
            }
        }
        System.out.println(questions.size());
        request.getRequestDispatcher("/jsp/student/test.jsp").forward(request, response);
    }
}
