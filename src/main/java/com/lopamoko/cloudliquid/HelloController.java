package com.lopamoko.cloudliquid;

import DTO.Order;
import DTO.ProductInShop.Product;
import DTO.ShopWithProduct.Customer.Customer;
import DTO.ShopWithProduct.Rating;
import DTO.ShopWithProduct.Shop;
import Managers.CustomerManager;
import Managers.OrderManager;
import Managers.ProductManager;
import Managers.ShopManager;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "It's a CloudLiquid";
    }


    @RequestMapping(value = "/addRatingToShop")
    @ResponseBody
    public Rating addRatingToShop(@RequestHeader(name = "id") HashMap<String,String> map){
        return new ShopManager().addRating(Long.parseLong(map.get("shop_id")),map.get("type"),Long.parseLong(map.get("customer_id")));
    }
    @RequestMapping(value = "/addCommentsToShop")
    @ResponseBody
    public List addCommentToShop(@RequestHeader(name = "id")HashMap<String,String> map){
        return new ShopManager().addComment(Long.parseLong(map.get("shop_id")),map.get("message"),Long.parseLong(map.get("customer_id")));
    }

    @RequestMapping(value = "/addRating")
    @ResponseBody
    public Rating addRating(@RequestHeader(name = "id")HashMap<String,String> map){
        return new ProductManager().addRating(Long.parseLong(map.get("product_id")),map.get("type"),Long.parseLong(map.get("customer_id")));
    }
    @RequestMapping(value = "/addComments")
    @ResponseBody
    public List addComment(@RequestHeader(name = "id")HashMap<String,String> map) throws UnsupportedEncodingException {
        String comment = new String(map.get("message").getBytes("ISO-8859-1"), "UTF-8");
        return new ProductManager().addComment(Long.parseLong(map.get("product_id")),comment,Long.parseLong(map.get("customer_id")));
    }
    @RequestMapping(value = "/getComments")
    @ResponseBody
    public List getComment(@RequestHeader(name = "id")HashMap<String,String> map){
        return new ProductManager().getComment(Long.parseLong(map.get("product_id")));
    }

    @RequestMapping(value = "/getCommentsFromShop")
    @ResponseBody
    public List getCommentFromShop(@RequestHeader(name = "id")HashMap<String,String> map){
        return new ShopManager().getComment(Long.parseLong(map.get("shop_id")));
    }

    @RequestMapping(value = "/getTopProduct")
    @ResponseBody
    public List getTopProduct(){
        return new ProductManager().getTopProduct();
    }

    @RequestMapping(value = "/getCurrentOrder")
    @ResponseBody
    public DTO.Order getCurrentOrder(@RequestHeader(name = "id") HashMap<String,String> map){
        if (map.get("customer_id") == null){
            map.put("customer_id","1");
        }
        return new OrderManager().getCurrentOrderByCustomerId(Long.parseLong(map.get("customer_id")));
    }

    @RequestMapping(value = "/updateCustomerInfo", produces ="application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public void updateCustomerInfo(@RequestHeader(name = "id") HashMap<String,String> map){
        System.out.println(map.get("first_name"));
        new CustomerManager().updateCustomerInfo(map.get("uuid"),
                map.get("first_name"),
                map.get("middle_name"),
                map.get("last_name"),
                map.get("phone"),
                map.get("email"),
                Boolean.parseBoolean(map.get("email_notification")),
                map.get("adress"));
    }
    @RequestMapping("/getCustomerInfo")
    @ResponseBody
    public DTO.CustomerInfo.Customer getCustomerInfo(@RequestHeader(name = "id")HashMap<String,String> map){
        return new CustomerManager().getCustomerInfo(map.get("uuid"));
    }

    @RequestMapping("/getAllShop")
    @ResponseBody
    public List getShop(){
        return new ShopManager().getAllShop();
    }

    @RequestMapping("/getAllCategories")
    @ResponseBody
    public List getCategories(){
        return new ProductManager().getCategories();
    }

    @RequestMapping("/getAllOrderByCustomerId")
    @ResponseBody
    public List getAllOrderByCustomerId(@RequestHeader(name = "id")HashMap<String,String> map) {
        if (map.get("id") != null) {
            return new OrderManager().getAllOrderByCustomerId(Long.parseLong(map.get("id")));
        } else {
            return new OrderManager().getAllOrderByCustomerId(1);
        }
    }

    @RequestMapping("/getAllProduct")
    @ResponseBody
    public List getProduct(){
        return new ProductManager().getAllProduct();
    }

    @RequestMapping("/getShopById")
    @ResponseBody
    public Shop getShopById(@RequestHeader(name = "shop_id")HashMap<String,String> map){
        System.out.println(map.get("shop_id"));
        return new ShopManager().getShopById(map.get("shop_id"));
    }

    @RequestMapping("/getCustomer")
    @ResponseBody
    public DTO.ShopWithProduct.Customer.Customer getCustomer(@RequestHeader(name = "login")HashMap<String,String> map){
        return new CustomerManager().getCustomer(map.get("customer_login"),map.get("customer_password"),map.get("customer_uuid"));
    }
    @RequestMapping("/getCustomerByLoginAndPassword")
    @ResponseBody
    public DTO.ShopWithProduct.Customer.Customer getCustomerByLoginAndPassword(@RequestHeader(name = "login")HashMap<String,String> map){
        return new CustomerManager().getCustomerByLoginAndPassword(map.get("customer_login"),map.get("customer_password"),map.get("customer_uuid"));
    }

    @RequestMapping("/addCustomer")
    @ResponseBody
    public Customer addCustomer(@RequestHeader(name = "login")HashMap<String,String> map){
        return new CustomerManager().addCustomer(map.get("customer_login"),map.get("customer_password"),map.get("customer_uuid"));
    }
    @RequestMapping("/getProductById")
    @ResponseBody
    public Product getProductById(@RequestHeader(name = "id")HashMap<String,String> map){
        return new ProductManager().getProductById(Long.parseLong(map.get("id")));
    }
    @RequestMapping("/createNewOrder")
    @ResponseBody
    public Order createNewOrder(@RequestHeader(name = "id")HashMap<String,String> map){
        return new OrderManager().createNewOrder(Long.parseLong(map.get("id")));
    }
    @RequestMapping("/addToOrder")
    @ResponseBody
    public Order addToOrder(@RequestHeader(name = "id")HashMap<String,String> map){
        return new OrderManager().addToOrder(Long.parseLong(map.get("shop_product_id")),Long.parseLong(map.get("order_id")));
    }
    @RequestMapping("/removeFromOrder")
    @ResponseBody
    public Order removeFromOrder(@RequestHeader(name = "id") HashMap<String,String> map){
        return new OrderManager().removeFromOrder(Long.parseLong(map.get("shop_product_id")), Long.parseLong(map.get("order_id")));
    }
    @RequestMapping("/finishOrder")
    @ResponseBody
    public Order finishOrder(@RequestHeader(name = "id")HashMap<String,String> map){
        return new OrderManager().finishOrder(Long.parseLong(map.get("order_id")));
    }
}
