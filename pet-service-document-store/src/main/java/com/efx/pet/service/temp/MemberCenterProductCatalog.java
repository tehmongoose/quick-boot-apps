package com.efx.pet.service.temp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberCenterProductCatalog {

    private String displayName;
    private String key;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberCenterProductCatalog that = (MemberCenterProductCatalog) o;

        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, MemberCenterProduct> getProducts() {
        return products;
    }

    public void setProducts(Map<String, MemberCenterProduct> products) {
        this.products = products;
    }
}
