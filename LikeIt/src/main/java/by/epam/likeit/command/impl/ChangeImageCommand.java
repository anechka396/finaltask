package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.UserDAOFactory;
import by.epam.likeit.entity.User;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChangeImageCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "dgubd42xn",
                    "api_key", "672941474238658",
                    "api_secret", "IU8hDylaNIoTIARGBivQaRIqS4c"

            ));

            try {
                List<FileItem> fields = upload.parseRequest(request);
                Iterator<FileItem> it = fields.iterator();
                while (it.hasNext()) {
                    FileItem fileItem = it.next();
                    if (!fileItem.isFormField()) {
                        File toUpload = new File(fileItem.getName());
                        fileItem.write(toUpload);
                        Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());
                        Files.delete(toUpload.toPath());
                        UserDAO userDAO = UserDAOFactory.getInstance();
                        User user = (User) request.getSession().getAttribute("user");
                        userDAO.updateImage(user.getLogin(), (String) uploadResult.get("url"));
                        user.setImageURL((String) uploadResult.get("url"));
                        request.getSession(true).setAttribute("user", user);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("method", "redirect");

        return PageName.USER_PAGE;
    }
}
