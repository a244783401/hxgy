package com.hxgy.wechat.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * web错误全局错误
 * @author zy
 * @create 2018-01-18 14:35
 **/
@Controller
public class AppErrorControllor implements ErrorController {
    private static final String ERROR_PATH = "/error";
    private ErrorAttributes errorAttributes;
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
    @Autowired
    public AppErrorControllor(ErrorAttributes errorAttributes){
        this.errorAttributes = errorAttributes;
    }

    /**
     * 页面错误访问
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = ERROR_PATH,produces = "text/html")
    public String errorPageHandler(HttpServletRequest request, HttpServletResponse response){
        System.out.print("error");
        int status = response.getStatus();
        System.out.print("status"+status);
        switch (status){
            case 403:
                return "403";
            case 404:
                return "404";
            case 500:
                return "500";
        }
        return "psychologyIndex";
    }

    /**
     * 接口错误
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ServerResponse errorApiHandler(HttpServletRequest request){
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String,Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes,false);
        int status = getStatus(request);
        return ServerResponse.createErrorCodeMessage(status,String.valueOf(attr.getOrDefault("message","error")));
    }

    public int getStatus(HttpServletRequest request){
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status != null){
            return status;
        }else{
            return 500;
        }
    }
}
