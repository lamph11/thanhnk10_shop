package shoe.store.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shoe.store.entity.User;
import shoe.store.payload.Meta;
import shoe.store.payload.response.ListCustomersResponse;
import shoe.store.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/customers")
    public String getListCustomer(){
        return "admin/customers";
    }

//    @ResponseBody
//    @PostMapping(path = "/data")
//    public ListCustomersResponse getListCustomer(@RequestParam Map<String, String> request){
//        Meta meta = new Meta(request);
//        PageRequest pageRequest = PageRequest.of(meta.getPage() - 1, meta.getPerpage());
//        Page<User> users = userService.searchAll(pageRequest);
//
//        meta.setPages(users.getTotalPages());
//        meta.setTotal(users.getTotalElements());
//        ListCustomersResponse customersResponse = new ListCustomersResponse(meta, users.getContent());
//
//        return customersResponse;
//    }
}
