package anhtuan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import anhtuan.model.User;
import anhtuan.service.PaginationService;

@Controller
public class SecurityController {
	
	@Autowired
	private PaginationService paginationService;

	@GetMapping("/admin/login")
	public String getLoginAdmin() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails)principal;

			for (GrantedAuthority role : userDetails.getAuthorities()) {
				if (role.getAuthority().equals("ROLE_ADMIN")) {
					return "redirect:/admin/user";
				}
			}
		}
		
		return "login_admin";
	}

	@GetMapping("/login")
	public String getLoginUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return "redirect:/welcome";
		}
		return "login_user";
	}

	@GetMapping("/")
	public String getIndex() {
		return "redirect:login";
	}

	@GetMapping("/welcome")
	public String getSuccessUserPage() {
		return "welcome";
	}

	@GetMapping("/admin/user")
	public String getSuccessAdminPage(Model model, @RequestParam(value="page", required=false, defaultValue="1") int page) {
		List<User> users = paginationService.getContentPage(page);
		long pageNums = paginationService.getPageNums();
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("users", users);
		return "user";
	}
	
	@GetMapping("/403")
	public String get403() {
		return "403";
	}
}
