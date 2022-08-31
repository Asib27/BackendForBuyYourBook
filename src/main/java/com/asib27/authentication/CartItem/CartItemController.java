package com.asib27.authentication.CartItem;


import com.asib27.authentication.UserCloned.UserCloned;
import com.asib27.authentication.UserCloned.UserClonedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    @Autowired
    CartItemService cartItemService;
    @Autowired
    UserClonedService userClonedService;

    Double totalPrice = 0.0;

    @PostMapping("/add")
    public CartItem addNewCartItem(@RequestParam String bookId, @RequestParam(defaultValue = "1") Integer quantity){
        if(bookId == null )return null;
        UserCloned user = userClonedService.getCurrentUser();
        return cartItemService.addNewCartItem(bookId, user, quantity);
    }

    @GetMapping("/get")
    public List<Object[]> getAllBooksInCart(){
        UserCloned user = userClonedService.getCurrentUser();
        return cartItemService.getAll(user);
    }


    @GetMapping("/get/data")
    public List<CartItemHelper> getItemList(){
        return cartItemService.getItemList(userClonedService.getCurrentUser().getId());
    }

    @DeleteMapping("/remove")
    public void removeBook(@RequestParam String isbn){
        if(isbn == null )return;
        UserCloned user = userClonedService.getCurrentUser();
        cartItemService.remove(isbn, user);
    }

    @DeleteMapping("/removeAll")
    public void removeAllBooks(){
        UserCloned user = userClonedService.getCurrentUser();
        cartItemService.removeAll(user);
    }

    @PostMapping("/updateQuantity")
    public void updateQuantity(@RequestParam String bookId, @RequestParam(defaultValue = "1") Integer quantity){
        if(bookId == null) return ;
        UserCloned user = userClonedService.getCurrentUser();
        cartItemService.updateQuantity(bookId,user, quantity);

    }

    @GetMapping("/totalPrice")
    public Double getTotalPrice(){
        UserCloned user = userClonedService.getCurrentUser();
        return cartItemService.getTotalPrice(user);
    }

    @GetMapping("/get/price/discounted")
    public Double getDiscountedPrice(){
        return totalPrice;
    }

    @GetMapping("/applyCoupon")
    public Double getPriceWithDiscount(@RequestParam Long couponId)  {

        UserCloned user = userClonedService.getCurrentUser();
        try {
            totalPrice = cartItemService.getDiscountedTotal(user, couponId);
            return totalPrice;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return getTotalPrice();
    }

    @PostMapping("/buy")
    public void buyCartItems(){
        UserCloned user = userClonedService.getCurrentUser();
        if(totalPrice == 0.0) totalPrice = getTotalPrice();
        cartItemService.reduce_the_count_of_cartBooks(user);
        cartItemService.updateBuyItems(user.getId());
        cartItemService.updateNotification(user);
        cartItemService.updateTransactionTable(user,totalPrice);
    }



}
