package ua.test.services;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserService();
    private final TestService testService = new TestService();
    private final ResultService resultService = new ResultService();
    private final QuestionService questionService = new QuestionService();
    private final Validation validation = new Validation();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public TestService getTestService() {
        return testService;
    }

    public ResultService getResultService() {
        return resultService;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }


}
