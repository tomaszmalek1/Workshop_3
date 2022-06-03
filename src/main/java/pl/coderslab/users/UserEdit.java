package pl.coderslab.users;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserEdit", value = "/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        UserDao userDao = new UserDao();
        User read = userDao.read(Integer.parseInt(id));
        request.setAttribute("user", read);
        getServletContext().getRequestDispatcher("/WEB-INF/users/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        UserDao userDao = new UserDao();
        try {
            user.setId(Integer.parseInt(request.getParameter("id")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        user.setUserName(request.getParameter("userName"));
        user.setEmail(request.getParameter("userEmail"));
        user.setPassword(request.getParameter("userPassword"));

        userDao.update(user);

        response.sendRedirect("/user/list");
    }
}
