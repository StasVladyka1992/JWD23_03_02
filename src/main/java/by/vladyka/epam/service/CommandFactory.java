package by.vladyka.epam.service;

import by.vladyka.epam.service.impl.ParseCommand;

public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private CommandFactory (){}

    public static CommandFactory getInstance() {
        return instance;
    }
    public Command  getCommand(String command){
        CommandName commandName = CommandName.valueOf(command);
        switch (commandName){
            case PARSE:{
                return new ParseCommand();
            }
            default:{
                return null;
            }
        }

    }
}
