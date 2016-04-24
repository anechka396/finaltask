package by.epam.likeit.controller.helper;

import by.epam.likeit.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InitCommandHelper {

    private static final String xmlFileName = "/command.xml";
    private static final Logger logger = LogManager.getRootLogger();

    public void init(CommandHelper helper){
        logger.trace(xmlFileName);
        Map<CommandName, Command> commands = new HashMap<>();
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            CommandSaxHandler handler = new CommandSaxHandler();
            reader.setContentHandler(handler);
            logger.trace(xmlFileName);

            reader.parse(new InputSource(getClass().getResourceAsStream(xmlFileName)));
            logger.trace(xmlFileName);
            commands = handler.getCommands();
            helper.initCommands(commands);
        } catch (SAXException | IOException e) {
            logger.trace(e);
        }
    }

    private class CommandSaxHandler extends DefaultHandler {

        private Map<CommandName, Command> commands = new HashMap<>();
        private Command command;
        private String baseName;
        private String className;
        private String commandName;
        private StringBuilder text;

        public Map<CommandName, Command> getCommands() {
            return commands;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            text = new StringBuilder();
            String tagConfig = CommandTagName.CONFIGURATION.toString().toLowerCase();
            String baseAttr = CommandTagName.BASE.toString().toLowerCase();
            if(qName.equals(tagConfig)){
                baseName = attributes.getValue(baseAttr);
                logger.trace(baseName);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            CommandTagName tagName = CommandTagName.valueOf(qName.toUpperCase());
            switch (tagName){
                case NAME: className = text.toString();
                    logger.trace("Class NAME: " + className);
                    break;
                case COMMAND: commandName = text.toString();
                    logger.trace("Command: " + commandName);
                    break;
                case CLASS:
                    try {
                        Class clazz = Class.forName(baseName + "." + className);
                        Command command = (Command) clazz.newInstance();
                        logger.trace(command.toString());
                        commands.put(CommandName.valueOf(commandName.toUpperCase()), command);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    command = null;
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            text.append(ch, start, length);
        }
    }

}