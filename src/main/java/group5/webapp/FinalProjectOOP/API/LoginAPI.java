package group5.webapp.FinalProjectOOP.API;

import group5.webapp.FinalProjectOOP.models.CustomerInfo;
import group5.webapp.FinalProjectOOP.models.User;
import group5.webapp.FinalProjectOOP.services.CustomerInfoService;
import group5.webapp.FinalProjectOOP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class LoginAPI {
    @Autowired
    UserService userService;
    @Autowired
    CustomerInfoService customerInfoService;


    @PostMapping("/register")
    public ResponseEntity<String> RegisterPage( @RequestBody User userV1,CustomerInfo cus){
        User user = userService.getUserByUserName(userV1.getUserName());
        if(user != null){
//            redirectAttributes.addFlashAttribute("message2", "Tài khoản đăng nhập đã tồn tại!!!");
//            return "redirect:/login";
            return ResponseEntity.badRequest().body("Tài khoản đăng nhập đã tồn tại");

        }else {
            userService.register(userV1);
            customerInfoService.registerV2(cus,userV1);
//            redirectAttributes.addFlashAttribute("message2", "Đăng kí tài khoản thành công!!!");
            return ResponseEntity.badRequest().body("Đăng kí tài khoản thành công");
//            return "redirect:/login";

        }
    }
}
