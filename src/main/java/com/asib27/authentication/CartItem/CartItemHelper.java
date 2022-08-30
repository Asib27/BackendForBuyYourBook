package com.asib27.authentication.CartItem;

import com.asib27.authentication.Book.Book;
import com.asib27.authentication.Writer.Writer;

public class CartItemHelper {

    private String name;
    private int price;
    private String link;
    private String author_name;
    private int quantity;

    public CartItemHelper(String name, int price, String link,
                          String author_name, int quantity) {
        this.name = name;
        this.price = price;
        this.link = link;
        this.author_name = author_name;
        this.quantity = quantity;
    }

    public CartItemHelper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
