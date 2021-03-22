package cn.cst.config;

import cn.cst.exception.LoginException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> errorHandler(Exception ex) {
        Map map = new HashMap();
//        map.put("message", ex.getMessage());
        //判断异常的类型,返回不一样的返回值
        if(ex instanceof MissingServletRequestParameterException){
            map.put("msg","缺少必需参数："+((MissingServletRequestParameterException) ex).getParameterName());
        }
        else if(ex instanceof LoginException){
            map.put("msg","登录密码错误");
        }else {
            map.put("msg","异常发生");
        }
        return ResponseEntity.badRequest().body(map);
    }
}
