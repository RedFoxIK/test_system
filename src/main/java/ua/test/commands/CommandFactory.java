package ua.test.commands;

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

        tempMap.put("/testing_system/tests", new LoginCommand());
        tempMap.put("/testing_system/test", new TestCommand());
        tempMap.put("/testing_system/registration", new RegistrationCommand());
        tempMap.put("/testing_system/new_user", new NewUserCommand());
        tempMap.put("/testing_system/results", new ResultCommand());

        commands = Collections.unmodifiableMap(tempMap);
    }

    public Command getCommand(HttpServletRequest request) throws IOException {
        return commands.get(request.getRequestURI());
    }
}
