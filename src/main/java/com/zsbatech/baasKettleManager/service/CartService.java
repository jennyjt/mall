package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.Cart;
import com.zsbatech.baasKettleManager.model.OrderProduct;
import com.zsbatech.baasKettleManager.model.WriteCart;

public interface CartService {
    int add(Cart cart);
    OrderProduct selectBuy(Cart cart);
}
