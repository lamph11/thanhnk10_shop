package shoe.store.service.impl;

import org.springframework.stereotype.Service;

import shoe.store.entity.Product;
import shoe.store.service.ProductService;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {

}
