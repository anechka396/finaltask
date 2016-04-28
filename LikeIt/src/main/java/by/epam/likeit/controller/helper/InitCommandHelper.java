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

    private static final Logger logger = LogManager.getRootLogger();

    public void init(CommandHelper helper, String xmlFileName){

        Map<CommandName, Command> commands = new HashMap<>();

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            CommandSaxHandler handler = new CommandSaxHandler();
            reader.setContentHandler(handler);
            InputSource inputSource = new InputSource(getClass().getResourceAsStream(xmlFileName));
            reader.parse(inputSource);
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
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            CommandTagName tagName = CommandTagName.valueOf(qName.toUpperCase());
            switch (tagName){
                case NAME: className = text.toString();
                    break;
                case COMMAND: commandName = text.toString();
                    break;
                case CLASS:
                    try {
                        Class clazz = Class.forName(baseName + "." + className);
                        command = (Command) clazz.newInstance();
                        commands.put(CommandName.valueOf(commandName.toUpperCase().replace("-", "_")), command);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        logger.error(e);
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
