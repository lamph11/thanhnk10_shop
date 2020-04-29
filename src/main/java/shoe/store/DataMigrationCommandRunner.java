package shoe.store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import shoe.store.entity.Brand;
import shoe.store.entity.Category;
import shoe.store.entity.OrderApp;
import shoe.store.entity.OrderAppDetail;
import shoe.store.entity.OrderWeb;
import shoe.store.entity.OrderWebDetail;
import shoe.store.entity.Product;
import shoe.store.entity.ProductSize;
import shoe.store.entity.User;
import shoe.store.repository.BrandRepository;
import shoe.store.repository.CartItemRepository;
import shoe.store.repository.CategoryRepository;
import shoe.store.repository.OrderAppDetailRepository;
import shoe.store.repository.OrderAppRepository;
import shoe.store.repository.OrderWebDetailRepository;
import shoe.store.repository.OrderWebRepository;
import shoe.store.repository.ProductRepository;
import shoe.store.repository.ProductSizeRepository;
import shoe.store.repository.UserRepository;
import shoe.store.security.Role;
import shoe.store.service.UtilService;
import shoe.store.service.OrderWebService;
import shoe.store.service.impl.ProductServiceImpl;
import shoe.store.util.DeliveryStatus;
import shoe.store.util.PaymentMethod;
import shoe.store.util.PaymentStatus;

@Component
public class DataMigrationCommandRunner implements CommandLineRunner {
//
//	@Autowired
//    private JavaMailSender javaMailSender;

	private int total = 30;
	private boolean test = true;
	private boolean migratedData = false;
	private boolean showCreatedDataToConsole = false;
	private final Log logger = LogFactory.getLog(getClass());

	private void test() {

//		List<Product> products = productRepository.findAll(PageRequest.of(0, 1000)).getContent();
//		List<ProductSize> productSizes = productSizeRepository.findAll();
//		List<User> userApps = userRepository.findAppUser();
//		List<User> customers = userRepository.findUserByRole(Role.CUSTOMER);

//		Faker faker = new Faker(new Locale("vi"));

//		for (int i = 0; i < 150; i++) {
//			OrderWeb orderWeb = new OrderWeb();
//			User customer = customers.get(rand(0, customers.size()));
//			Long totalAmount = 0L;
//			orderWeb.setTotalAmount(totalAmount);
//			orderWeb.setUser(customer);
//			orderWeb.setConsignee(customer.getName());
//			orderWeb.setConsigneePhone(customer.getPhone());
//			orderWeb.setDeliveryAddress(customer.getAddress());
//
//			String paymentMethod = PaymentMethod.COD;
//			String paymentStatus = PaymentStatus.UNPAID;
//			String deliveryStatus = DeliveryStatus.NOT_APPROVED;
//			String randDS[] = {DeliveryStatus.WAITING_FOR_DELIVERY, DeliveryStatus.WAITING_FOR_DELIVERY_2, DeliveryStatus.DELIVERING, DeliveryStatus.DELIVERING_2, DeliveryStatus.SUCCESSFUL}; 
//
//			boolean isATM = ThreadLocalRandom.current().nextBoolean();
//			if (isATM) {
//				paymentMethod = PaymentMethod.ATM;
//				paymentStatus = PaymentStatus.PAID;
//				deliveryStatus = randDS[rand(5)];
//				if (randB()) deliveryStatus = DeliveryStatus.SUCCESSFUL;
//				if (rand(1, 11) > 9) {deliveryStatus = DeliveryStatus.CANCELLED; paymentStatus = PaymentStatus.REFUNDED;}
//
//			} else {
//				deliveryStatus = randDS[rand(5)];
//				if (rand(1, 11) > 7) deliveryStatus = DeliveryStatus.NOT_APPROVED;
//				if (rand(1, 11) > 9) deliveryStatus = DeliveryStatus.CANCELLED;
//				if (deliveryStatus.equals(DeliveryStatus.SUCCESSFUL)) paymentStatus = PaymentStatus.PAID;
//				if (rand(1, 11) > 8) {deliveryStatus = DeliveryStatus.SUCCESSFUL; paymentStatus = PaymentStatus.PAID;}
//			}
//
//			orderWeb.setPaymentMethod(paymentMethod);
//			orderWeb.setPaymentStatus(paymentStatus);
//			orderWeb.setDeliveryStatus(deliveryStatus);
//			orderWebRepository.save(orderWeb);
//
//			for (int j = 0; j < rand(1, 4); j++) {
//				OrderWebDetail orderWebDetail = new OrderWebDetail();
//				orderWebDetail.setOrderWeb(orderWeb);
//				
//				ProductSize productSize = productSizes.get(rand(0, productSizes.size()));
//				orderWebDetail.setProductSize(productSize);
//				Long price = productSize.getProduct().getPrice();
//				orderWebDetail.setPrice(price);
//				int quantity = rand(1, 4);
//				orderWebDetail.setQuantity(quantity);
//				orderWebDetail.setTotalAmount(price*quantity);
//				totalAmount += price*quantity;
//				orderWebDetailRepository.save(orderWebDetail);
//			}
//			orderWeb.setTotalAmount(totalAmount);
//			orderWebRepository.save(orderWeb);
//		}	

//		for (int i = 0; i < 50; i++) {
//			OrderApp orderApp = new OrderApp();
//			User employeee = userApps.get(rand(0, userApps.size()));
//			Long totalAmount = 0L;
//			orderApp.setTotalAmount(totalAmount);
//			orderApp.setUser(employeee);
//			orderAppRepository.save(orderApp);
//	
//			for (int j = 0; j < rand(1, 4); j++) {
//				OrderAppDetail orderAppDetail = new OrderAppDetail();
//				orderAppDetail.setOrderApp(orderApp);
//				
//				ProductSize productSize = productSizes.get(rand(0, productSizes.size()));
//				orderAppDetail.setProductSize(productSize);
//				Long price = productSize.getProduct().getPrice();
//				orderAppDetail.setPrice(price);
//				int quantity = rand(1, 4);
//				orderAppDetail.setQuantity(quantity);
//				orderAppDetail.setTotalAmount(price*quantity);
//				totalAmount += price*quantity;
//				orderAppDetailRepository.save(orderAppDetail);
//			}
//			orderApp.setTotalAmount(totalAmount);
//			orderAppRepository.save(orderApp);
//		}	

//		for (int i = 0; i < 250; i++) {
//			String[] role = {Role.ADMIN ,Role.WEB_MANAGER, Role.WEB_STAFF, Role.APP_MANAGER, Role.APP_STAFF, Role.APP_STAFF};
//			String address = faker.address().fullAddress();
//
//			User user = new User();
//			user.setName(faker.name().fullName());
//			user.setEmail(faker.internet().emailAddress().replace(".", "_").replace("_com", ".com"));
//			user.setPassword(bCryptPasswordEncoder.encode("123"));
//			user.setRole(i<50 ? role[rand(0, 5)] : Role.CUSTOMER);
//			user.setAddress(address.substring(0 ,address.lastIndexOf(",")));
//			user.setDateOfBirth(new java.sql.Date(faker.date().birthday(18, 30).getTime()));
//			user.setGender(randB());
//			user.setPhone(faker.phoneNumber().cellPhone());
//			try {
//				userRepository.save(user);
//			} catch (Exception e) {}
//		}

//		products.forEach(product -> {
//			for (int size = 35; size <= 47; size++) {
//				if (rand(1, 12) > 4) {
//					productSizeRepository.save(new ProductSize(product, size, rand(20, 150)));
//				}
//			}
//		});
	}

	protected void migratedData() {

//
//		cartItemRepository.deleteAll();
//		productSizeRepository.deleteAll();
//		productRepository.deleteAll();
//		brandRepository.deleteAll();
//		categoryRepository.deleteAll();
//		userRepository.deleteAll();
//
//		brandRepository.save(new Brand("Adidas"));
//		brandRepository.save(new Brand("Puma"));
//		brandRepository.save(new Brand("Under Armour"));
//		brandRepository.save(new Brand("Vans"));
//		brandRepository.save(new Brand("Asic"));
//		brandRepository.save(new Brand("New Balance"));
//		brandRepository.save(new Brand("Nike"));
//		brandRepository.save(new Brand("ON"));
//		brandRepository.save(new Brand("Reebok"));
//		brandRepository.save(new Brand("Air Jordan"));
//
//		categoryRepository.save(new Category("Cổ thấp / Low-top"));
//		categoryRepository.save(new Category("Cổ trung / Mid-top"));
//		categoryRepository.save(new Category("Cổ cao / Low-top"));
//		categoryRepository.save(new Category("Slide"));
//		categoryRepository.save(new Category("Phụ kiện"));
//
//		Brand brand = brandRepository.findAll(PageRequest.of(0, 1)).getContent().get(0);
//		Category category = categoryRepository.findAll(PageRequest.of(0, 1)).getContent().get(0);
//
//		try {
//			for (int i = 0; i < total; i++) {
//				Product product = new Product();
//				product.setBrand(brand);
//				product.setCategory(category);
//				product.setName("Sản phẩm " + (i+1));
//				product.setPrice(ThreadLocalRandom.current().nextLong(600, 5000)*1000);
////				product.setImage("/product/"+ ThreadLocalRandom.current().nextInt(1, 11) +".jpg");
//				product.setVersionName("");
//				productRepository.save(product);
//				productSizeRepository.save(new ProductSize(product, rand(35, 45), 100));
//				
//			}
//
//		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//		}

//		User user = new User();
//		user.setName("Thanh");
//		user.setEmail("thanhnkph06759@fpt.edu.vn");
//		user.setPassword(bCryptPasswordEncoder.encode("123"));
//		user.setRole("ROLE_ADMIN");
//		userRepository.save(user);
//
//		user = new User();
//		user.setName("Trung");
//		user.setEmail("trungvdph06755@fpt.edu.vn");
//		user.setPassword(bCryptPasswordEncoder.encode("123"));
//		user.setRole("ROLE_ADMIN");
//		userRepository.save(user);
//
//		user = new User();
//		user.setName("Huy");
//		user.setEmail("huyhdph06603@fpt.edu.vn");
//		user.setPassword(bCryptPasswordEncoder.encode("123"));
//		user.setRole("ROLE_ADMIN");
//		userRepository.save(user);
//
//		user = new User();
//		user.setName("Hoàng");
//		user.setEmail("hoangnqph06760@fpt.edu.vn");
//		user.setPassword(bCryptPasswordEncoder.encode("123"));
//		user.setRole("ROLE_ADMIN");
//		userRepository.save(user);

	}

	private void printf(String format, Object... args) {
		if (showCreatedDataToConsole) System.out.printf(format, args);
	}

	private void println(String text) {
		if (showCreatedDataToConsole) System.out.println(text);
	}

	private void println() {
		if (showCreatedDataToConsole) System.out.println();
	}

	private void finish() {
		println("-CREATE FINISHED-\n");
		if (!showCreatedDataToConsole) System.out.println("DONE");
	}

	private int rand(int number) {
		return ThreadLocalRandom.current().nextInt(number);
	}

	private int rand(int number1, int number2) {
		return ThreadLocalRandom.current().nextInt(number1, number2);
	}

	private boolean randB() {
		return ThreadLocalRandom.current().nextBoolean();
	}

	@Override
	public void run(String... args) throws Exception {
		String strArgs = Arrays.stream(args).collect(Collectors.joining("|"));
		logger.info("Application started with arguments:" + strArgs);
		Object[] arrArgs = Arrays.stream(args).toArray();

		if (arrArgs.length > 0 && arrArgs[0].toString().equals("migrate")) {
			if (arrArgs.length > 1 && arrArgs[1].toString().matches("\\d+")) {
				total = Integer.valueOf(arrArgs[1].toString());
				if (total > 100000) total = 100000;
				if (arrArgs.length > 2 && (arrArgs[2].toString().equals("1") || arrArgs[2].toString().equalsIgnoreCase("true") || arrArgs[2].toString().equalsIgnoreCase("t"))) {
					showCreatedDataToConsole = true;
				}
			}
			migratedData();

		} else if (this.getClass().getResource("").getProtocol().equals("file")) {
			if (System.getProperty("isMigratedData") == null) {
				if (migratedData) migratedData();
				System.setProperty("isMigratedData", "true");

			} else if (test) {
				test();
			}
		}
	}

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductSizeRepository productSizeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private OrderWebRepository orderWebRepository;

	@Autowired
	private OrderWebDetailRepository orderWebDetailRepository;

	@Autowired
	private OrderWebService orderWebService;

	@Autowired
	private OrderAppRepository orderAppRepository;

	@Autowired
	private OrderAppDetailRepository orderAppDetailRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

}
