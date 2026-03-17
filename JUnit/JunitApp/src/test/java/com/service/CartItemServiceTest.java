package com.service;

import com.exception.InvalidNameException;
import com.exception.InvalidPriceException;
import com.model.CartItem;
import com.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartItemServiceTest {
    CartItemService cartItemService;

    @BeforeEach
    void setUp(){
        cartItemService = new CartItemService();
        System.out.println("Initialized...");
    }

    @AfterEach
    void finish(){
        cartItemService=null;
        System.out.println("De-structuring");
    }

    @Test
    public void itemNullTest(){
        User user = new User(1,"Darren","NORMAL");

        NullPointerException e = Assertions.assertThrows(NullPointerException.class,
                ()->cartItemService.computeCost(null,user));

        Assertions.assertEquals("Item cannot be null",e.getMessage());
    }

    @Test
    public void itemNegativePriceTest(){
        User user = new User(1,"Darren","NORMAL");
        List<CartItem> item = new ArrayList<>();
        CartItem cartItem = new CartItem(1,"Dell Vostro", BigDecimal.valueOf(-10),1);
        item.add(cartItem);

        InvalidPriceException e = Assertions.assertThrows(InvalidPriceException.class,
                ()->cartItemService.computeCost(item,user));

        Assertions.assertEquals("Price cannot be negative",e.getMessage());
    }

    @Test
    public void itemNameEmptyTest(){
        User user = new User(1,"Darren","NORMAL");
        List<CartItem> item = new ArrayList<>();
        CartItem cartItem = new CartItem(1,"", BigDecimal.valueOf(100),1);
        item.add(cartItem);

        InvalidNameException e = Assertions.assertThrows(InvalidNameException.class,
                ()->cartItemService.computeCost(item,user));

        Assertions.assertEquals("Item name cannot be null or empty",e.getMessage());
    }

    @Test
    public void itemNameNullTest(){
        User user = new User(1,"Darren","NORMAL");
        List<CartItem> item = new ArrayList<>();
        CartItem cartItem = new CartItem(1,null, BigDecimal.valueOf(100),1);
        item.add(cartItem);

        InvalidNameException e = Assertions.assertThrows(InvalidNameException.class,
                ()->cartItemService.computeCost(item,user));

        Assertions.assertEquals("Item name cannot be null or empty",e.getMessage());
    }

    @Test
    public void userNullTest(){
        List<CartItem> item = new ArrayList<>();
        CartItem cartItem = new CartItem(1,"Wire", BigDecimal.valueOf(100),1);
        item.add(cartItem);

        NullPointerException e = Assertions.assertThrows(NullPointerException.class,
                ()->cartItemService.computeCost(item,null));

        Assertions.assertEquals("User cannot be null",e.getMessage());
    }

    @Test
    public void noDiscountTest(){
        User user = new User(1,"Darren","NORMAL");
        List<CartItem> item = new ArrayList<>();
        CartItem cartItem = new CartItem(1,"Mouse", BigDecimal.valueOf(300),1);
        item.add(cartItem);

        Assertions.assertEquals(BigDecimal.valueOf(300),cartItemService.computeCost(item,user));
    }

    @Test
    public void amountGreater1000Test(){
        User user = new User(1,"Darren","NORMAL");
        List<CartItem> item = new ArrayList<>();
        CartItem cartItem = new CartItem(1,"Mouse", BigDecimal.valueOf(1000),2);
        item.add(cartItem);

        Assertions.assertEquals(0,cartItemService.computeCost(item,user).compareTo(BigDecimal.valueOf(1900)));
    }

    @Test
    public void premiumTest(){
        User user = new User(1,"Darren","PREMIUM");
        List<CartItem> item = new ArrayList<>();
        CartItem cartItem = new CartItem(1,"Mouse", BigDecimal.valueOf(1000),1);
        item.add(cartItem);

        Assertions.assertEquals(0,cartItemService.computeCost(item,user).compareTo(BigDecimal.valueOf(900)));
    }

    @Test
    public void premiumLess500Test(){
        User user = new User(1,"Darren","PREMIUM");
        List<CartItem> item = new ArrayList<>();
        CartItem cartItem = new CartItem(1,"Mouse", BigDecimal.valueOf(200),2);
        item.add(cartItem);

        Assertions.assertEquals(0,cartItemService.computeCost(item,user).compareTo(BigDecimal.valueOf(400)));
    }
}