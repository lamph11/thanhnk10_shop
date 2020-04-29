package shoe.store.controller.shopper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shoe.store.security.CurrentUser;
import shoe.store.security.UserPrincipal;
import shoe.store.service.ProductService;

@Controller
public class IndexController {

	@Autowired
	ProductService productService;

	@GetMapping("/*")
	public String redirect() {

		return "redirect:/index";
	}

	@GetMapping("/index")
	public String showIndex(ModelMap model, @RequestParam(value = "page", required = false) String pageRequest) {

		model.addAttribute("products", productService.searchAll(PageRequest.of(0, 8)));
		model.addAttribute("newProducts", productService.searchAll(PageRequest.of(3, 3)));
		model.addAttribute("specialProducts", productService.searchAll(PageRequest.of(4, 3)));
		model.addAttribute("featuredProducts", productService.searchAll(PageRequest.of(5, 3)));
		return "shopper/index";
	}

	@GetMapping("about")
	public String showAbout() {

		return "shopper/about";
	}

	@GetMapping("contact")
	public String showContact() {

		return "shopper/contact";
	}

	@ResponseBody
	@PostMapping("/signed-in")
	public String isSignIn(@CurrentUser UserPrincipal userPrincipal) {
		return userPrincipal != null ? userPrincipal.getCurrentUser().getName() : "";
	}
}
