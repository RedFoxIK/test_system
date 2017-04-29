package ua.test.commands;

import ua.test.commands.student.RegistrationCommand;
import ua.test.commands.student.ShowTestCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandHandler {
    private final static CommandHandler instance = new CommandHandler();

    private static final String START_TEST_URI = "/testing_system/student/start_test";
    private static final String TEST_URI = "/testing_system/student/test";
    private static final String END_TEST_URI = "/testing_system/student/test_end";
    private static final String HOME_PAGE = "/testing_system/";
    private static final String REGISTRATION_PAGE = "/testing_system/registration";
    private static final String SIGN_IN_PAGE = "/testing_system/sign_in";

    CommandHandler() {}

    public static CommandHandler getInstance() {
        return instance;
    }

    public Command getUnexpectedCommand(HttpServletRequest request) {
        HttpSession httpSession = ((HttpServletRequest) request).getSession(false);
        String uri = request.getRequestURI();

        if ( httpSession == null || httpSession.getAttribute("idUser") == null ) {
            if ( !(uri.equals(HOME_PAGE) || uri.equals(REGISTRATION_PAGE) || uri.equals(SIGN_IN_PAGE)) ) {
                return CommandFactory.getInstance().getCommand(HOME_PAGE);
            }
        }

        if ( request.getSession().getAttribute("userTest") != null ) {
            if ( !(uri.equals(START_TEST_URI) || uri.equals(TEST_URI) || uri.equals(END_TEST_URI)) )
                return CommandFactory.getInstance().getCommand(TEST_URI);
        }
        return null;
    }
}
