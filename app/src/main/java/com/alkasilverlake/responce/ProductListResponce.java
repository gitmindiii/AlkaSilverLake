package com.alkasilverlake.responce;

import java.util.List;

/**
 * Created by mindiii on 17/11/18.
 */

public class ProductListResponce {
    /**
     * status : success
     * message : SUCCESS
     * data : [{"waterId":"1","water_name":"purified","products":[{"productId":"2","product_name":"ghg","product_price":"545","product_image":"VsrJjoc0bvtMuQGk.jpg","waterId":"1","water_name":"purified","bottleId":"4","bottle_type":"Plastic Bottle","quantity":"1"},{"productId":"5","product_name":"fd","product_price":"34343","product_image":"","waterId":"1","water_name":"purified","bottleId":"1","bottle_type":"Glass Bottle","quantity":"1"}]},{"waterId":"2","water_name":"alkaline","products":[{"productId":"3","product_name":"ss","product_price":"222","product_image":"","waterId":"2","water_name":"alkaline","bottleId":"2","bottle_type":"Glass Bottle","quantity":"1"},{"productId":"4","product_name":"gg","product_price":"142","product_image":"","waterId":"2","water_name":"alkaline","bottleId":"2","bottle_type":"Glass Bottle","quantity":"1"},{"productId":"6","product_name":"dfd","product_price":"15000","product_image":"","waterId":"2","water_name":"alkaline","bottleId":"2","bottle_type":"Glass Bottle","quantity":"1"}]}]
     */

    private String status;
    private String message;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * waterId : 1
         * water_name : purified
         * products : [{"productId":"2","product_name":"ghg","product_price":"545","product_image":"VsrJjoc0bvtMuQGk.jpg","waterId":"1","water_name":"purified","bottleId":"4","bottle_type":"Plastic Bottle","quantity":"1"},{"productId":"5","product_name":"fd","product_price":"34343","product_image":"","waterId":"1","water_name":"purified","bottleId":"1","bottle_type":"Glass Bottle","quantity":"1"}]
         */

        private String waterId;
        private String water_name;
        private List<ProductsBean> products;

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

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            /**
             * productId : 2
             * product_name : ghg
             * product_price : 545
             * product_image : VsrJjoc0bvtMuQGk.jpg
             * waterId : 1
             * water_name : purified
             * bottleId : 4
             * bottle_type : Plastic Bottle
             * quantity : 1
             */

            private String productId;
            private String product_name;
            private String product_price;
            private String product_image;
            private String waterId;
            private String water_name;
            private String bottleId;
            private String bottle_type;
            private String quantity;

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
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
        }
    }
}
