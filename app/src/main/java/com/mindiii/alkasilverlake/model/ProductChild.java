package com.mindiii.alkasilverlake.model;

import java.io.Serializable;

/**
 * Created by mindiii on 17/11/18.
 */

public class ProductChild implements Serializable {
    private String productId;
    private String product_name;
    private String product_price;
    private String product_image;
    private String waterId;
    private String water_name;
    private int count=1;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    private boolean isClicked=false;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductChild(String productId, String product_name, String product_price, String product_image, String waterId, String water_name, String bottleId, String bottle_type, String quantity) {
        this.productId = productId;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
        this.waterId = waterId;
        this.water_name = water_name;
        this.bottleId = bottleId;
        this.bottle_type = bottle_type;
        this.quantity = quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getWaterId() {
        return waterId;
    }

    public void setWaterId(String waterId) {
        this.waterId = waterId;
    }

    public String getWater_name() {
        return water_name;
    }

    public void setWater_name(String water_name) {
        this.water_name = water_name;
    }

    public String getBottleId() {
        return bottleId;
    }

    public void setBottleId(String bottleId) {
        this.bottleId = bottleId;
    }

    public String getBottle_type() {
        return bottle_type;
    }

    public void setBottle_type(String bottle_type) {
        this.bottle_type = bottle_type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    private String bottleId;
    private String bottle_type;
    private String quantity;


}
