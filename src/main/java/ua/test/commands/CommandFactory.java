package ua.test.commands;

import ua.test.commands.general.*;
import ua.test.commands.student.*;
import ua.test.commands.tutor.*;
import ua.test.commands.general.ShowMyProfileCommand;

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

        tempMap.put("/testing_system/", new FirstCommand());

        tempMap.put("/testing_system/sign_in", new SigInCommand());
        tempMap.put("/testing_system/registration", new RegistrationCommand());

        tempMap.put("/testing_system/user/tests", new ShowTestsCommand());
        tempMap.put("/testing_system/user/log_out", new SignOutCommand());
        tempMap.put("/testing_system/user/my_profile", new ShowMyProfileCommand());
        tempMap.put("/testing_system/user/update_pass", new ChangeUserPasswordCommand());
        tempMap.put("/testing_system/user/update_email", new ChangeUserEmailCommand());

        tempMap.put("/testing_system/student/start_test", new StartTestCommand());
        tempMap.put("/testing_system/student/test", new ShowTestCommand());
        tempMap.put("/testing_system/student/results", new ShowAllResultsCommand());
        tempMap.put("/testing_system/student/test_end", new EndTestCommand());
        tempMap.put("/testing_system/student/show_result", new ShowTestResultCommand());

        tempMap.put("/testing_system/tutor/test_edit", new EditTest());
        tempMap.put("/testing_system/tutor/create_question", new CreateQuestion());
        tempMap.put("/testing_system/tutor/add_question", new AddQuestion());
        tempMap.put("/testing_system/tutor/delete_question", new DeleteQuestion());
        tempMap.put("/testing_system/tutor/results_for_test", new ResultForTestCommand());
        tempMap.put("/testing_system/tutor/change_test_state", new ChangeTestState());
        tempMap.put("/testing_system/tutor/add_test", new AddTest());
        tempMap.put("/testing_system/tutor/delete_test", new DeleteTest());

        commands = Collections.unmodifiableMap(tempMap);
    }

    public Command getCommand(HttpServletRequest request) throws IOException {
        return commands.get(request.getRequestURI());
    }
}
