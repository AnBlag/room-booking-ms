package ru.roombooking.front.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {



    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, ModelMap modelMap) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            long statusCode = Long.parseLong(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                modelMap.addAttribute("errorStatus", "404 \uD83D\uDE22 Страница не найдена!");
            }
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                modelMap.addAttribute("errorStatus", "500 \uD83D\uDE22 Ошибка сервера!");
            }
            else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                modelMap.addAttribute("errorStatus", "403 \uD83D\uDE22 Ошибка доступа!");
            }
            else modelMap.addAttribute("errorStatus", statusCode + " \uD83D\uDE22 Что-то пошло не так!");
        }
        return "error";
    }
}


