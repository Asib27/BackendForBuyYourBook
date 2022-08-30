package com.asib27.authentication.CartItem;

import com.asib27.authentication.Book.BookService;
import com.asib27.authentication.Coupon.Coupon;
import com.asib27.authentication.Coupon.CouponService;
import com.asib27.authentication.UserCloned.UserCloned;
import com.asib27.authentication.UserCloned.UserClonedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
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


    public CartItem addNewCartItem(String bookId, UserCloned user, Integer quantity) {
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
    public void remove(String bookId, UserCloned user) {
        cartItemRepository.removeBook(bookId, user.getId());
    }

    @Transactional
    public void removeAll(UserCloned user){
        cartItemRepository.removeAll(user.getId());
    }

    @Transactional
    public void updateQuantity(String bookId, UserCloned user, Integer quantity) {
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
    public void updateBuyItems(Long user_id) {
        List<Object[]> getdata = cartItemRepository.getData(user_id);
        for(Object[] x: getdata) {
            cartItemRepository.updateBuyItems(user_id, (String)x[1], (Integer)x[2]);
            cartItemRepository.removeBook((String)x[1], user_id);
        }
    }

    @Transactional
    public void updateNotification(UserCloned user) {
        cartItemRepository.updateNotification(user.getId());
    }

    @Transactional
    public void updateTransactionTable(UserCloned user, Double totalPrice) {
        cartItemRepository.updateTransaction(user.getId(), totalPrice);
    }

    public List<CartItemHelper> getItemList(Long id) {
        List<Object[]> cartIdList = cartItemRepository.getAllCartId(id);
        List<BigInteger> idList = new ArrayList<>();
        for(Object[] x: cartIdList){
            for(Object y: x){
                idList.add((BigInteger) y);
            }
        }

        List<CartItemHelper> itemList = new ArrayList<>();

        for(BigInteger x: idList){
            CartItemHelper cartItemHelper = new CartItemHelper();
            List<Object[]> partial = cartItemRepository.getPartial(x);

            String author = cartItemRepository.getAuthor(x);
            cartItemHelper.setIsbn((String) partial.get(0)[0]);
            cartItemHelper.setName((String) partial.get(0)[1]);
            cartItemHelper.setPrice((int) partial.get(0)[2]);
            cartItemHelper.setLink((String) partial.get(0)[3]);
            cartItemHelper.setQuantity((int) partial.get(0)[4]);
            cartItemHelper.setAuthor_name(author);
            itemList.add(cartItemHelper);
        }

        return itemList;
    }


}
