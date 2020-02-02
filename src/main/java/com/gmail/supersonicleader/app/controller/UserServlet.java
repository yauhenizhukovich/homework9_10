package com.gmail.supersonicleader.app.controller;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.supersonicleader.app.service.InitTableService;
import com.gmail.supersonicleader.app.service.UserService;
import com.gmail.supersonicleader.app.service.exception.CreateTableException;
import com.gmail.supersonicleader.app.service.exception.DeleteTableException;
import com.gmail.supersonicleader.app.service.exception.FindAllUsersException;
import com.gmail.supersonicleader.app.service.impl.InitTableServiceImpl;
import com.gmail.supersonicleader.app.service.impl.UserServiceImpl;
import com.gmail.supersonicleader.app.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String PAGES = "/WEB-INF/pages";
    private static final String TRUE_VALUE = "I am a superman";
    private static final String FALSE_VALUE = "Staying at shadow";
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<UserDTO> users = userService.findAll();
            req.setAttribute("users", users);
            req.setAttribute("trueValue", TRUE_VALUE);
            req.setAttribute("falseValue", FALSE_VALUE);
        } catch (FindAllUsersException e) {
            logger.error(e.getMessage(), e);
        }
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(PAGES + "/users.jsp");
        dispatcher.forward(req, resp);
    }

}
