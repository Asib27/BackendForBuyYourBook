package com.asib27.authentication.CartItem;

import com.asib27.authentication.Book.BookService;
import com.asib27.authentication.Coupon.Coupon;
import com.asib27.authentication.Coupon.CouponService;
import com.asib27.authentication.UserCloned.UserCloned;
import com.asib27.authentication.UserCloned.UserClonedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    BookService bookService;
    @Autowired
    CouponService couponService;
    @Autowired
    UserClonedService userClonedService;


    public CartItem addNewCartItem(Long bookId, UserCloned user, Integer quantity) {
        CartItem cartItem = new CartItem();

        cartItem.setUser(user);
        cartItem.setBook(bookService.getBook(bookId));
        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    public List<Object[]> getAll(UserCloned user) {

        return cartItemRepository.getAll(user.getId());
    }

    @Transactional
    public void remove(Long bookId, UserCloned user) {
        cartItemRepository.removeBook(bookId, user.getId());
    }

    @Transactional
    public void removeAll(UserCloned user){
        cartItemRepository.removeAll(user.getId());
    }

    @Transactional
    public void updateQuantity(Long bookId, UserCloned user, Integer quantity) {
        cartItemRepository.updateQuantity(bookId, user.getId(), quantity);
    }

    public Double getTotalPrice(UserCloned user) {
        return   cartItemRepository.getTotalPrice(user.getId());
    }

    public Double getDiscountedTotal(UserCloned user, Long couponId) throws IllegalAccessException {
        Coupon coupon = couponService.getCoupon(couponId);
        if(coupon.getStatus() == "invalid" ){
            throw new IllegalAccessException("the coupon is invalid !!");
        }else {
            return cartItemRepository.getDisountedTotal(user.getId(), coupon.getDiscount());

        }

    }

    @Transactional
    public void reduce_the_count_of_cartBooks(UserCloned user) {
        cartItemRepository.reduce_the_count_of_cartBooks(user.getId());
    }

    @Transactional
    public void delete_cart_items(UserCloned user) {
        cartItemRepository.delete_cart_items(user.getId());
    }

    @Transactional
    public void updateNotification(UserCloned user) {
        cartItemRepository.updateNotification(user.getId());
    }

    @Transactional
    public void updateTransactionTable(UserCloned user, Double totalPrice) {
        cartItemRepository.updateTransaction(user.getId(), totalPrice);
    }
}
