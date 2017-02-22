package com.efx.pet.service.temp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberCenterProductCatalog {

    private String catalogName;
    private Map<String, MemberCenterProduct> products;

    public MemberCenterProductCatalog() {
        this(new HashMap<>());
    }

    public MemberCenterProductCatalog(Map<String, MemberCenterProduct> products) {
        this.products = products;
    }

    public void addProduct(MemberCenterProduct product) {
        if (product == null) {
            return;
        }
        products.put(product.getKey(), product);
    }

    public void addProducts(List<MemberCenterProduct> newProducts) {
        if (newProducts == null) {
            return;
        }
        for (MemberCenterProduct product : newProducts) {
            addProduct(product);
        }
    }


}
