package shoe.store.controller.shopper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import shoe.store.entity.Product;
import shoe.store.service.ProductService;
import shoe.store.util.Validator;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/product-listing")
	public String showProductListing(ModelMap model, @RequestParam(value = "page", required = false) String pageRequest) {

		model.addAttribute("products", productService.searchAll(PageRequest.of(0, 12)));
		return "shopper/product-listing";
	}

	@GetMapping("/product/{id}")
	public String showProductDetail(ModelMap model, @PathVariable String id) {

		if(Validator.checkId(id)) {
			Product product = productService.findById(Long.valueOf(id)).orElse(null);

			if (product != null) {
				model.addAttribute("product", product);
				model.addAttribute("relatedProducts", productService.searchAll(PageRequest.of(0, 5)));
				return "shopper/product";
			}
		}
		return "redirect:/index";
	}

	@GetMapping("/product/ts")
	public String showProductDetail2(ModelMap model) {

//		if(Validator.checkId(id)) {
			Product product = productService.findById(Long.valueOf(2)).orElse(null);

			if (product != null) {
				model.addAttribute("product", product);
				model.addAttribute("relatedProducts", productService.searchAll(PageRequest.of(0, 5)));
				return "shopper/product-03";
			}
//		}
		return "redirect:/index";
	}

}
