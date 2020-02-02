package com.gmail.supersonicleader.app.controller;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Enumeration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.supersonicleader.app.service.InitTableService;
import com.gmail.supersonicleader.app.service.UserService;
import com.gmail.supersonicleader.app.service.exception.CreateTableException;
import com.gmail.supersonicleader.app.service.exception.DeleteTableException;
import com.gmail.supersonicleader.app.service.exception.DeleteUserException;
import com.gmail.supersonicleader.app.service.impl.InitTableServiceImpl;
import com.gmail.supersonicleader.app.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteUserServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String id = req.getParameter(parameterName);
            Integer idInt = Integer.parseInt(id);
            try {
                userService.delete(idInt);
            } catch (DeleteUserException e) {
                logger.error(e.getMessage(), e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/users");
    }

}
