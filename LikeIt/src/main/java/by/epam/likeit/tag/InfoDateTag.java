package by.epam.likeit.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InfoDateTag extends TagSupport {

    private static final String DATA_FORMAT = "yyyy";
    private static final String OPEN_SPAN = "<span>";
    private static final String CLOSE_SPAN = "</span>";
    private static final String LIKEIT = "LikeIt";
    private static final String COPY = "&copy;";
    private static final String SPACE = " ";

    @Override
    public int doStartTag() throws JspException {
        DateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT);
        Calendar cal = Calendar.getInstance();
        String nameAndDate = OPEN_SPAN + LIKEIT + SPACE + COPY + SPACE + dateFormat.format(cal.getTime()) + CLOSE_SPAN;
        try {
            JspWriter out = pageContext.getOut();
            out.write(nameAndDate);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
