package by.epam.likeit.controller.helper;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/** CommandHelper contains all commands of application.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */

public final class CommandHelper {
    private Map<CommandName, Command> commands = new HashMap<>();

    /**
     * This method initialize CommandHelper with map commands.
     * @param commands Key - CommandName (name of command from enum CommandName).
     *                 Value - Command (class which execute command with name CommandName).
     */
    public void initCommands(Map<CommandName, Command> commands){
        this.commands = commands;
    }

    /**
     * getCommand method returns the Command with name commandName
     * @param commandName name of the command
     * @return command with name commandName
     */
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
