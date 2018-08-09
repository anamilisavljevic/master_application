/*********************************************************************
 * The Initial Developer of the content of this file is NETCONOMY.
 * All portions of the code written by NETCONOMY are property of
 * NETCONOMY. All Rights Reserved.
 *
 * NETCONOMY Software & Consulting GmbH
 * Hilmgasse 4, A-8010 Graz (Austria)
 * FN 204360 f, Landesgericht fuer ZRS Graz
 * Tel: +43 (316) 815 544
 * Fax: +43 (316) 815544-99
 * www.netconomy.net
 *
 * (c) 2018 by NETCONOMY Software & Consulting GmbH
 *********************************************************************/

package master.project.master_application.controller;

import master.project.master_application.constants.ControllerConstants;
import master.project.master_application.model.UserData;
import master.project.master_application.model.UserEntity;
import master.project.master_application.service.UserService;
import master.project.master_application.session_config.Autologin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    private Autologin autologin;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = { "/","/login" })
    public String login(final Model model) {
        List<UserEntity> list = userService.findAllUsers();
        model.addAttribute("list", list);
        
        return ControllerConstants.Pages.LOGIN_PAGE;
    }

    @GetMapping("/registration")
    public String showRegistration(UserData userBean) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(HttpServletResponse httpServletResponse, Model model, @Valid UserData userBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userBean.setProvider("REGISTRATION");
        // Save the details in DB
        if (StringUtils.isNotEmpty(userBean.getPassword())) {
            userBean.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));
        }
        UserEntity userEntity = populateUserEntity(userBean);
        userService.saveUser(userEntity);

        autologin.setSecurityontext(userEntity);

        model.addAttribute("loggedInUser", userBean);
        return "secure/user";
    }

    private UserEntity populateUserEntity(UserData userBean) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userBean.getEmail());
        userEntity.setName(userBean.getFirstName());
        userEntity.setSurname(userBean.getLastName());
        userEntity.setImage(userBean.getImage());
        userEntity.setCountry(userBean.getCountry());
        return userEntity;
    }

}
