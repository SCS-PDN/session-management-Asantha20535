import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String courseId = request.getParameter("courseId");

       
        HttpSession session = request.getSession(false);
        if (session == null || courseId == null) {
            response.sendRedirect("login.html");
            return;
        }

        List<Course> enrolled = (List<Course>) session.getAttribute("enrolled");
        if (enrolled == null) {
            enrolled = new ArrayList<>();
            session.setAttribute("enrolled", enrolled);
        }

        for (Course course : allCourses) {
            if (course.getCourseId().equals(courseId)) {
                if (!enrolled.contains(course)) {
                    enrolled.add(course);
                }
                break;
            }
        }

        response.sendRedirect("DashboardServlet");
    }
}
