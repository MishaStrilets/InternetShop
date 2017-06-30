package strilets.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strilets.model.Goods;
import strilets.model.Order;
import strilets.services.GoodsService;
import strilets.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

	private GoodsService goodsService;

	@Autowired
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	private OrderService orderService;

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping("/")
	String index() {
		return "index";
	}

	@RequestMapping(value = "/goods", method = RequestMethod.GET)
	public String listGoods(Model model) {
		model.addAttribute("goods", goodsService.listAllGoods());
		return "goods";
	}

	@RequestMapping("/goods/{id}")
	public String showGoods(@PathVariable Integer id, Model model) {
		model.addAttribute("goods", goodsService.getGoodsById(id));
		return "goods_show";
	}

	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String admin() {
		return "admin_goods";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null)
			new SecurityContextLogoutHandler().logout(request, response, auth);
		return "index";
	}

	@RequestMapping(value = "/admin/goods", method = RequestMethod.GET)
	public String adminGoods(Model model) {
		model.addAttribute("goods", goodsService.listAllGoods());
		return "admin_goods";
	}

	@RequestMapping("/admin/goods/{id}")
	public String adminShowGoods(@PathVariable Integer id, Model model) {
		model.addAttribute("goods", goodsService.getGoodsById(id));
		return "admin_goods_show";
	}

	@RequestMapping("/admin/goods/edit/{id}")
	public String editGoods(@PathVariable Integer id, Model model) {
		model.addAttribute("goods", goodsService.getGoodsById(id));
		return "admin_goods_form";
	}

	@RequestMapping("/admin/goods/new")
	public String newGoods(Model model) {
		model.addAttribute("goods", new Goods());
		return "admin_goods_form";
	}

	@RequestMapping(value = "/admin/goods", method = RequestMethod.POST)
	public String saveGoods(Goods goods) {
		goodsService.saveGoods(goods);
		return "redirect:/admin/goods/";
	}

	@RequestMapping("/admin/goods/delete/{id}")
	public String deleteGoods(@PathVariable Integer id) {
		goodsService.deleteGoods(id);
		return "redirect:/admin/goods";
	}

	@RequestMapping("/order/{id}")
	public String newOrder(@PathVariable Integer id, Model model) {
		Order order = new Order();
		model.addAttribute("order", order);
		int code = goodsService.getGoodsById(id).getCode();
		order.setCode(code);
		String date = new java.util.Date().toString();
		order.setDate(date);
		return "order_form";
	}

	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String saveOrder(Order order) {
		orderService.saveOrder(order);
		return "redirect:/goods";
	}

	@RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
	public String adminOrders(Model model) {
		model.addAttribute("order", orderService.getAllOrders());
		return "admin_orders";
	}

	@RequestMapping("/admin/orders/delete/{id}")
	public String deleteOrder(@PathVariable Integer id) {
		orderService.deleteOrder(id);
		return "redirect:/admin/orders";
	}
}
