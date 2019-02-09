package by.vladyka.epam.service;

import by.vladyka.epam.service.impl.NoSuchCommand;
import by.vladyka.epam.service.impl.ParseCommand;
import by.vladyka.epam.service.impl.ShowMenuCommand;

public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private CommandFactory (){}

    public static CommandFactory getInstance() {
        return instance;
    }
    public Command  getCommand(String command) {
        CommandName commandName = CommandName.valueOf(command);
        switch (commandName){
            case PARSE:{
                return new ParseCommand();
            }
            case SHOW_MENU:{
                return new ShowMenuCommand();
            }
            default:{
                return new NoSuchCommand();
            }
        }

    }
}
