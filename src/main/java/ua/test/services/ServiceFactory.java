package ua.test.services;

public class ServiceFactory {
//    private static final ServiceFactory instance = new ServiceFactory());

//    private ServiceFactory() {}
//
//    public ServiceFactory getInstance() {
//        return instance;
//    }

    public static UserService getUserService() {
        return new UserService();
    }

    public static TestService getTestService() {
        return new TestService();
    }

    public static  ResultService getResultService() {
        return new ResultService();
    }
}
