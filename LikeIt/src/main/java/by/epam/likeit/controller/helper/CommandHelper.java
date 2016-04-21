package by.epam.likeit.controller.helper;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Пользователь on 17.04.2016.
 */
public final class CommandHelper {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandHelper(){
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.LOCALE, new ChangeLocaleCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
    }


    public Command getCommand(String commandName){
        Command command = null;
        CommandName key = null;

        commandName = commandName.replace('-', '_').toUpperCase();
        key = CommandName.valueOf(commandName);

        command = commands.get(key);

        if(command == null){
            command = new UnknownCommand();
        }

        return command;
    }
}
