package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.ServiceFactory;
import by.epam.likeit.service.UserService;
import by.epam.likeit.service.exception.ServiceException;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public class ChangeImageCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String USER = "user";
    private static final String URL = "url";
    private static final String METHOD = "method";
    private static final String REDIRECT = "redirect";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        File file = getFileFromRequest(request);
        User user = (User) request.getSession().getAttribute(USER);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        try {
            String imageURL = userService.changeImage(user, file);
            user.setImageURL(imageURL);
            request.getSession().setAttribute(USER, user);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        request.setAttribute(METHOD, REDIRECT);
        return PageName.USER_PAGE;
    }

    private File getFileFromRequest(HttpServletRequest request){

        File file = null;

        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> fields = upload.parseRequest(request);
                FileItem fileItem = fields.get(0);
                file = new File(fileItem.getName());
                fileItem.write(file);
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
