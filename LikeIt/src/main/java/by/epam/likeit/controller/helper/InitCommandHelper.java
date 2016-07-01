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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** InitCommandHelper class used for initialization of commandHelper from file.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public class InitCommandHelper {

    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * This method initialize CommandHelper helper from file with name xmlFileName.
     * @param helper CommandHelper
     * @param xmlFileName Name of file with commands
     */
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
            LOGGER.error(e);
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
            String tagConfig = CommandTagName.TC_CONFIG.toString().toLowerCase();
            String baseAttr = CommandTagName.BASE.toString().toLowerCase();
            if(qName.replace(":", "_").equals(tagConfig)){
                baseName = attributes.getValue(baseAttr);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            CommandTagName tagName = CommandTagName.valueOf(qName.toUpperCase().replace(":", "_"));
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
                        LOGGER.error(e);
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
