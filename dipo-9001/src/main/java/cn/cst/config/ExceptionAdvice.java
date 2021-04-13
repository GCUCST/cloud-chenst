package cn.cst.config;

import cn.cst.exception.CustomException;
import cn.cst.exception.LoginException;
import cn.cst.exception.UserExistException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {



    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> errorHandler(Exception ex) {

        Map map = new HashMap();
        map.put("msg",ex.getMessage());
        //判断异常的类型,返回不一样的返回值
        if(ex instanceof MissingServletRequestParameterException){
            map.put("msg","缺少必需参数："+((MissingServletRequestParameterException) ex).getParameterName());
        }
        if(ex instanceof CustomException){
            map.put("自定义异常","错误");
        }
        if(ex instanceof LoginException){
            map.put("msg","登录密码错误");
        }
        if(ex instanceof MethodArgumentNotValidException){
            List<String> errors = new ArrayList<>();
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
            for (FieldError error : fieldErrors) {
//                map.put(error.getField(),error.getDefaultMessage());
                errors.add(error.getDefaultMessage());
            }
            map.put("msg",errors);
        }

        return ResponseEntity.badRequest().body(map);
    }
}
