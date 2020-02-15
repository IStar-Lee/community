package life.mastar.community.community.advice;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpResponse;
import life.mastar.community.community.dto.ResultDTO;
import life.mastar.community.community.exception.CustomizeErrorCode;
import life.mastar.community.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常处理
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        if ("application/json".equals(request.getContentType())) {
            //返回json
            ResultDTO resultDTO;
            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.DEFAULT_ERROR);
            }
            try {
                response.setCharacterEncoding("UTF-8");
                response.setStatus(200);
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {

            }
            return null;
        } else {
            //返回的是一个错误页面
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.DEFAULT_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }

    }
}
