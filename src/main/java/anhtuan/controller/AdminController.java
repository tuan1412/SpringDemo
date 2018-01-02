package anhtuan.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import anhtuan.model.User;
import anhtuan.service.UserService;
import anhtuan.validate.ConfirmPasswordValidator;
import anhtuan.validate.UsernameValidator;

@Controller
public class AdminController {
	@Autowired
	private UserService userService;

	@Autowired
	private UsernameValidator usernameValidator;

	@Autowired
	private ConfirmPasswordValidator confirmPasswordValidator;

	@GetMapping("/admin/user/add")
	public String getUserForm(@ModelAttribute("user") User user) {
		return "user_form";
	}

	@PostMapping("/admin/user/add")
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirect,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,
			@RequestParam(value = "role") String role) {

		usernameValidator.validate(user, result);
		confirmPasswordValidator.validate(user, result);

		if (result.hasErrors()) {
			return "user_form";
		}

		uploadAvatar(file, request, user);

		if ("MEMBER".equals(role)) {
			userService.createMember(user);
		} else {
			userService.createAdmin(user);
		}

		redirect.addFlashAttribute("success", "User added!");
		return "redirect:/admin/user/";
	}

	@GetMapping("/admin/user/{id}")
	public String getDetailUser(@PathVariable("id") Long id, Model model) {
		User user = userService.findOne(id);
		if (user == null) {
			return "404";
		}
		model.addAttribute("user", user);
		return "user_detail";
	}

	@PostMapping("/admin/user/{id}")
	public String editDetailUser(@Valid @ModelAttribute("user") User user, BindingResult result, MultipartFile file,
			HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile fileUpdate, 	
			@RequestParam(value = "role") String role) {
		
		if (result.hasErrors()) {
			return "user_detail";
		}
		uploadAvatar(fileUpdate, request, user);
		userService.update(user, role);
		return "redirect:/admin/user/";

	}

	@GetMapping("/admin/user/{id}/delete")
	public String removeUser(@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		userService.remove(user);

		return "redirect:/admin/user";
	}

	private void uploadAvatar(MultipartFile file, HttpServletRequest request, User user) {
		if (file != null) {
			try {
				InputStream inputStream = file.getInputStream();
				if (inputStream == null) {
					System.out.println("File inputstream is null");
				}
				String path = request.getServletContext().getRealPath("/") + "upload/";
				FileUtils.forceMkdir(new File(path));
				File upload = new File(path + file.getOriginalFilename());
				file.transferTo(upload);
				user.setAvatar(file.getOriginalFilename());

				IOUtils.closeQuietly(inputStream);
			} catch (IOException ex) {
				System.out.println("Error saving uploaded file");
			}
		}
	}
}
