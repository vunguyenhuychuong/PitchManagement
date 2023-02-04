package com.example.pitchmanagement.controller;

import com.example.pitchmanagement.entity.User;
import com.example.pitchmanagement.entity.Ward;
import com.example.pitchmanagement.service.DistrictService;
import com.example.pitchmanagement.service.RoleService;
import com.example.pitchmanagement.service.UserService;
import com.example.pitchmanagement.service.WardService;
import com.example.pitchmanagement.utils.GooglePojo;
import com.example.pitchmanagement.utils.GoogleUtils;
import com.example.pitchmanagement.utils.IdGeneration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final DistrictService districtService;

    private final WardService wardService;

    private final UserService userService;

    private final RoleService roleService;

    private final IdGeneration idGeneration;
    @Autowired
    private GoogleUtils googleUtils;

    @GetMapping(value = {"/register"})
    public String registerPage(Model model) {
        model.addAttribute("listDistricts", districtService.getAllDistricts());
        return "account/register.html";

    }

    @PostMapping(value = {"/register"})
    public RedirectView register(Model model, final HttpServletRequest request, final HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            String username = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String districtID = request.getParameter("districtID");
            String wardID = request.getParameter("ward");

            User user = User.builder()
                    .userName(username)
                    .districtId(districtID)
                    .email(email)
                    .fullName(fullName)
                    .imgLink("../img/user.jpg")
                    .memberAddress(address)
                    .memberId(idGeneration.raiseUserId(userService.getUserList()))
                    .memberStatus(true)
                    .ownerStatus(false)
                    .password(password)
                    .phone(phone)
                    .role(roleService.findRoleById("US"))
                    .ward(wardService.getWardById(wardID))
                    .build();

            userService.createAccount(user);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return new RedirectView("/home");
    }

    @GetMapping(value = {"/district/{id}"})
    public void getWardList(final HttpServletResponse response,
                            @PathVariable(name = "id") String id) {
        try {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            List<Ward> wardList = wardService.getWardByDistrictId(id);
            PrintWriter out = response.getWriter();

            for (Ward ward : wardList) {
                out.println("<input type=\"hidden\" name=\"districtID\" value=\"" + ward.getDistrict().getDistrictId() + "\"/>\n"
                        + "                            <option data-tokens=\"ketchup mustard\" value=\"" + ward.getWardId() + "\">" + ward.getWardName() + "</option>");
            }

        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
    }

    @GetMapping(value = {"/email"})
    public void checkRepeatedPassword(final HttpServletResponse response, final HttpServletRequest request,
                                      @RequestParam(name = "email", required = false) String email) {
        try {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            if (userService.findUserByEmail(email) != null) {
                String textBlock = """
                        <div class="alert alert-danger alert-dismissible fade show mb-5" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                            <strong>Lỗi!</strong> Email đã tồn tại.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>""";
                out.println(textBlock);
            }
        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
    }

    @RequestMapping("/login-google")
    public String loginGoogle(final HttpServletRequest request) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            return "redirect:/login?message=google_error";
        }
        String accessToken = googleUtils.getToken(code);

        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
        UserDetails userDetail = googleUtils.buildUser(googlePojo);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
                userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        User user = User.builder()
                .email(googlePojo.getEmail())
                .password("")
                .userName(googlePojo.getEmail())
                .districtId("1")
                .ward(wardService.getWardById("1"))
                .phone("")
                .fullName("")
                .memberAddress("")
                .imgLink(googlePojo.getPicture())
                .memberId(idGeneration.raiseUserId(userService.getUserList()))
                .memberStatus(true)
                .ownerStatus(false)
                .role(roleService.findRoleById("US"))
                .build();

        if (userService.findUserByEmail(googlePojo.getEmail()) == null) {
            userService.createAccount(user);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        request.getSession().setAttribute("user", user);
        return "redirect:/home";
    }

    @GetMapping(value = "/login")

    public String loginPage(final HttpServletRequest request, final HttpServletResponse response) {
        request.getSession().setAttribute("message", null);
        return "account/login.html";
    }

    @PostMapping("/login")
    public String loginPage(Model model, final HttpServletRequest request, final HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            User user = userService.findUserByName(request.getParameter("username"));
            User password = userService.findUserByPassword(request.getParameter("password"));

            if (user != null && password != null) {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("message", null);

                return "redirect:/home";
            } else {
                request.getSession().setAttribute("message", "Invalid username or password");
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

        return "account/login";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/home";
    }
}
