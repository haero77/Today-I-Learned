package org.example.ch08;

public class SummerDiscountManager {

    DiscountManager discountManager;

    /**
     * 상품 추가하기
     *
     * @param product 상품
     * @return 추가에 성공하면 true
     */
    boolean add(Product product) {
        if (product.id < 0) {
            throw new IllegalArgumentException();
        }
        if (product.name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int tmp;
        if (product.canDiscount) {
            tmp = discountManager.totalPrice + DiscountManager.getDiscountPrice(product.price);
        } else {
            tmp = discountManager.totalPrice + product.price;
        }

        if (tmp <= 300_000) {
            discountManager.totalPrice = tmp;
            discountManager.discountProducts.add(product);
            return true;
        } else {
            return false;
        }
    }

}
