package ua.test.commands;

import ua.test.commands.general.*;
import ua.test.commands.student.*;
import ua.test.commands.tutor.*;
import ua.test.commands.general.ShowMyProfileCommand;
import ua.test.constants.Links;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static CommandFactory instance = new CommandFactory();

    private Map<String, Command> commands = new HashMap<>();

    public static CommandFactory getInstance(){
        return instance;
    }

    private CommandFactory(){
        Map<String, Command> tempMap = new HashMap<>();

        tempMap.put(Links.Uri.HOME_PAGE, new FirstCommand());
        tempMap.put(Links.Uri.SIGN_IN_PAGE, new SigInCommand());
        tempMap.put(Links.Uri.REGISTRATION_PAGE, new RegistrationCommand());
        tempMap.put(Links.Uri.SIGN_OUT_PAGE, new SignOutCommand());
        tempMap.put(Links.Uri.TESTS_PAGE, new ShowTestsCommand());
        tempMap.put(Links.Uri.PROFILE_PAGE, new ShowMyProfileCommand());
        tempMap.put(Links.Uri.CHANGE_PASSWORD_PAGE, new ChangeUserPasswordCommand());
        tempMap.put(Links.Uri.CHANGE_EMAIL_PAGE, new ChangeUserEmailCommand());

        tempMap.put(Links.Uri.START_TEST_PAGE, new StartTestCommand());
        tempMap.put(Links.Uri.STUDENT_TEST_PAGE, new ShowTestCommand());
        tempMap.put(Links.Uri.ALL_STUDENT_RESULTS_PAGE, new ShowAllResultsCommand());
        tempMap.put(Links.Uri.END_TEST_PAGE, new EndTestCommand());
        tempMap.put(Links.Uri.TEST_RESULT_PAGE, new ShowTestResultCommand());

        tempMap.put(Links.Uri.TUTOR_TEST_PAGE, new ShowTutorTestCommand());
        tempMap.put(Links.Uri.CREATE_QUESTION_PAGE, new CreateQuestionCommand());
        tempMap.put(Links.Uri.ADD_QUESTION_PAGE, new AddQuestionCommand());
        tempMap.put(Links.Uri.DELETE_QUESTION_PAGE, new DeleteQuestionCommand());
        tempMap.put(Links.Uri.RESULTS_FOR_TEST_PAGE, new ResultForTestCommand());
        tempMap.put(Links.Uri.CHANGE_STATE_TEST_PAGE, new ChangeTestStateCommand());
        tempMap.put(Links.Uri.ADD_TEST_PAGE, new AddTestCommand());
        tempMap.put(Links.Uri.EDIT_TEST_PAGE, new EditTestCommand());
        tempMap.put(Links.Uri.DELETE_TEST_PAGE, new DeleteTestCommand());

        commands = Collections.unmodifiableMap(tempMap);
    }

    public Command getCommand(HttpServletRequest request) throws IOException {
        return commands.get(request.getRequestURI());
    }

    Command getCommand(String uri) {
        return commands.get(uri);
    }
}
