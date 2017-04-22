package ua.test.commands;

import ua.test.commands.general.*;
import ua.test.commands.student.NewStudent;
import ua.test.commands.tutor.MyProfileCommand;

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
        tempMap.put("/testing_system/tests", new SigIn());
        tempMap.put("/testing_system/test", new TestCommand());
        tempMap.put("/testing_system/new_user", new NewStudent());
        tempMap.put("/testing_system/results", new ResultCommand());
        tempMap.put("/testing_system/test_result", new TestResultCommand());
        tempMap.put("/testing_system/log_out", new SignOut());
        tempMap.put("/testing_system/my_profile", new MyProfileCommand());
        tempMap.put("/testing_system/update_pass", new ChangeUserPassword());
        tempMap.put("/testing_system/update_email", new ChangeUserEmail());

        tempMap.put("/testing_system/test_edit", new EditTest());


        commands = Collections.unmodifiableMap(tempMap);
    }

    public Command getCommand(HttpServletRequest request) throws IOException {
        return commands.get(request.getRequestURI());
    }
}
