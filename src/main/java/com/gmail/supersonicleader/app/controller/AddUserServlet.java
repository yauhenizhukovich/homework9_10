package com.gmail.supersonicleader.app.controller;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.supersonicleader.app.service.InitTableService;
import com.gmail.supersonicleader.app.service.UserService;
import com.gmail.supersonicleader.app.service.exception.AddUserException;
import com.gmail.supersonicleader.app.service.exception.CreateTableException;
import com.gmail.supersonicleader.app.service.exception.DeleteTableException;
import com.gmail.supersonicleader.app.service.impl.InitTableServiceImpl;
import com.gmail.supersonicleader.app.service.impl.UserServiceImpl;
import com.gmail.supersonicleader.app.service.model.UserDTO;
import com.gmail.supersonicleader.app.util.ValidationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddUserServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String PAGES = "/WEB-INF/pages";
    private UserService userService = UserServiceImpl.getInstance();
    private InitTableService initTableService = InitTableServiceImpl.getInstance();

    @Override
    public void init() {
        try {
            initTableService.deleteAndCreateTables();
        } catch (DeleteTableException | CreateTableException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(PAGES + "/add_user.jsp");
        dispatcher.include(req, resp);
        try {
            requestValidation(req);
            UserDTO userDTO = getUserDTO(req);
            userService.add(userDTO);
        } catch (IllegalArgumentException e) {
            logger.debug(e.getMessage(), e);
            req.setAttribute("validation", e.getMessage());
            dispatcher.forward(req, resp);
        } catch (AddUserException e) {
            logger.error(e.getMessage(), e);
        }
        resp.sendRedirect(req.getContextPath() + "/users");
    }

    private void requestValidation(HttpServletRequest req) throws IllegalArgumentException {
        String username = req.getParameter("username");
        ValidationUtil.checkUsername(username);
        String password = req.getParameter("password");
        ValidationUtil.checkPassword(password);
        String isActive = req.getParameter("is_active");
        ValidationUtil.checkActivity(isActive);
        String age = req.getParameter("age");
        ValidationUtil.checkAge(age);
        String address = req.getParameter("address");
        ValidationUtil.checkAddress(address);
        String telephone = req.getParameter("telephone");
        ValidationUtil.checkTelephone(telephone);
    }

    private UserDTO getUserDTO(HttpServletRequest req) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(req.getParameter("username"));
        userDTO.setPassword(req.getParameter("password"));
        String ageString = req.getParameter("age");
        userDTO.setAge(Integer.parseInt(ageString));
        String activeString = req.getParameter("is_active");
        userDTO.setActive(Boolean.parseBoolean(activeString));
        userDTO.setAddress(req.getParameter("address"));
        userDTO.setTelephone(req.getParameter("telephone"));
        return userDTO;
    }

}
