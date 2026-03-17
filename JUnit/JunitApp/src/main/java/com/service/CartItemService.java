package com.service;

import com.exception.InvalidNameException;
import com.exception.InvalidPriceException;
import com.model.CartItem;
import com.model.User;

import java.math.BigDecimal;
import java.util.List;

public class CartItemService {
    public BigDecimal computeCost(List<CartItem> items, User user){
        BigDecimal total = BigDecimal.ZERO;

        if(items==null){
            throw new NullPointerException("Item cannot be null");
        }
        if(user==null){
            throw new NullPointerException("User cannot be null");
        }

        for(CartItem item : items){
            if(item.getPrice().compareTo(BigDecimal.ZERO)<0){
                throw new InvalidPriceException("Price cannot be negative");
            }
            if(item.getName()==null || item.getName().isEmpty()){
                throw new InvalidNameException("Item name cannot be null or empty");
            }
            total = total.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

            if(total.compareTo(BigDecimal.valueOf(1000))>0){
                total = total.multiply(BigDecimal.valueOf(0.95));
            }
            else if(user.getStatus().equals("PREMIUM") && total.compareTo(BigDecimal.valueOf(500))>0){
                total = total.multiply(BigDecimal.valueOf(0.90));
            }
        }
        return total;
    }
}