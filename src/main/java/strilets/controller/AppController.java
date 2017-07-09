package strilets.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import strilets.model.Goods;
import strilets.model.Order;
import strilets.services.GoodsService;
import strilets.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private OrderService orderService;

	@Autowired
	MessageSource messageSource;

	/*
	 * This method will return home page.
	 */
	@RequestMapping("/")
	String index() {
		return "index";
	}

	/*
	 * This method will list all goods.
	 */
	@RequestMapping(value = "/goods", method = RequestMethod.GET)
	public String listGoods(Model model) {
		List<Goods> goods = goodsService.getAllGoods();
		model.addAttribute("goods", goods);
		return "goods";
	}

	/*
	 * This method will list all goods by search.
	 */
	@RequestMapping(value = "/goods-search", method = RequestMethod.GET)
	public String searchGoods(String search, Model model) {
		List<Goods> goods = goodsService.getGoodsByName(search);
		model.addAttribute("goods", goods);
		return "goods";
	}

	/*
	 * This method will list all goods by sort.
	 */
	@RequestMapping(value = "/goods-sort", method = RequestMethod.GET)
	public String sortGoods(String sort, Model model) {
		List<Goods> goods = goodsService.sortGoods(sort);
		model.addAttribute("goods", goods);
		return "goods";
	}

	/*
	 * This method will return information about goods.
	 */
	@RequestMapping("/goods-{id}")
	public String viewGoods(@PathVariable Integer id, Model model) {
		model.addAttribute("goods", goodsService.getGoodsById(id));
		return "goods_view";
	}

	/*
	 * This method will return the statement for order.
	 */
	@RequestMapping("/order-{id}")
	public String newOrder(@PathVariable Integer id, Model model) {
		Order order = new Order();
		model.addAttribute("order", order);
		String date = new java.util.Date().toString();
		order.setDate(date);
		order.setBuy(id);
		return "order_form";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving order in database.
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String saveOrder(@Valid Order order, BindingResult result) {
		if (result.hasErrors()) {
			return "order_form";
		}
		orderService.saveOrder(order);
		return "redirect:/goods";
	}

	/*
	 * This method will return home page for admin.
	 */
	@RequestMapping("/admin")
	public String admin() {
		return "admin_index";
	}

	/*
	 * This method will logout admin.
	 */
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null)
			new SecurityContextLogoutHandler().logout(request, response, auth);
		return "index";
	}

	/*
	 * This method will list all goods for admin.
	 */
	@RequestMapping(value = "/admin-goods", method = RequestMethod.GET)
	public String adminGoods(Model model) {
		model.addAttribute("goods", goodsService.getAllGoods());
		return "admin_goods";
	}

	/*
	 * This method will list all goods by search for admin.
	 */
	@RequestMapping(value = "/admin-goods-search", method = RequestMethod.GET)
	public String adminSearchGoods(String search, Model model) {
		List<Goods> goods = goodsService.getGoodsByName(search);
		model.addAttribute("goods", goods);
		return "admin_goods";
	}

	/*
	 * This method will list all goods by sort for admin.
	 */
	@RequestMapping(value = "/admin-goods-sort", method = RequestMethod.GET)
	public String adminSortGoods(String sort, Model model) {
		List<Goods> goods = goodsService.sortGoods(sort);
		model.addAttribute("goods", goods);
		return "admin_goods";
	}

	/*
	 * This method will return information about goods for admin.
	 */
	@RequestMapping("/admin-goods-{id}")
	public String adminViewGoods(@PathVariable Integer id, Model model) {
		model.addAttribute("goods", goodsService.getGoodsById(id));
		return "admin_goods_view";
	}

	/*
	 * This method will return information about buying goods.
	 */
	@RequestMapping("/admin-goods-buy-{id}")
	public String adminBuyGoods(@PathVariable Integer id, Model model) {
		model.addAttribute("goods", goodsService.getGoodsById(id));
		return "admin_goods_buy";
	}

	/*
	 * This method will return the statement for add new goods.
	 */
	@RequestMapping("/admin-goods-new")
	public String newGoods(Model model) {
		Goods goods = new Goods();
		model.addAttribute("goods", goods);
		return "admin_goods_form";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving goods in database.
	 */
	@RequestMapping(value = "/admin-goods-new", method = RequestMethod.POST)
	public String saveGoods(@Valid Goods goods, BindingResult result) {
		if (result.hasErrors()) {
			return "admin_goods_form";
		}
		goodsService.saveGoods(goods);
		return "redirect:/admin-goods";
	}

	/*
	 * This method will return the statement for update an existing goods.
	 */
	@RequestMapping("/admin-goods-edit-{id}")
	public String editGoods(@PathVariable Integer id, Model model) {
		Goods goods = goodsService.getGoodsById(id);
		model.addAttribute("goods", goods);
		return "admin_goods_form_edit";
	}

	/*
	 * This method will be called on form submission and updating goods in
	 * database.
	 */
	@RequestMapping(value = "/admin-goods-edit", method = RequestMethod.POST)
	public String updateGoods(@Valid Goods goods, BindingResult result) {
		if (result.hasErrors()) {
			return "admin_goods_form_edit";
		}
		goodsService.updateGoods(goods);
		return "redirect:/admin-goods/";
	}

	/*
	 * This method will delete goods by id.
	 */
	@RequestMapping("/admin-goods-delete-{id}")
	public String deleteGoods(@PathVariable Integer id, Model model) {
		List<Order> orders = null;
		orders = orderService.getAllOrders(id);
		if (orders.isEmpty()) {
			goodsService.deleteGoods(id);
			return "redirect:/admin-goods";
		} else {
			model.addAttribute("order", orders);
			return "admin_orders";
		}

	}

	/*
	 * This method will list all orders for admin.
	 */
	@RequestMapping(value = "/admin-orders", method = RequestMethod.GET)
	public String listOrders(Model model) {
		List<Order> orders = orderService.getAllOrders();
		model.addAttribute("order", orders);
		return "admin_orders";
	}

	/*
	 * This method will delete order by id.
	 */
	@RequestMapping("/admin-orders-delete-{id}")
	public String deleteOrder(@PathVariable Integer id) {
		orderService.deleteOrder(id);
		return "redirect:/admin-orders";
	}

	/*
	 * This method will return the statement for order for admin.
	 */
	@RequestMapping("/admin-order-{id}")
	public String adminNewOrder(@PathVariable Integer id, Model model) {
		Order order = new Order();
		model.addAttribute("order", order);
		String date = new java.util.Date().toString();
		order.setDate(date);
		order.setBuy(id);
		return "admin_order_form";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving order in database for admin.
	 */
	@RequestMapping(value = "/admin-order", method = RequestMethod.POST)
	public String adminSaveOrder(@Valid Order order, BindingResult result) {
		if (result.hasErrors()) {
			return "admin_order_form";
		}
		orderService.saveOrder(order);
		return "redirect:/admin-goods";
	}
}
