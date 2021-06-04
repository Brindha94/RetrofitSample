package com.esfita.retrofitsample;

public class ProductModel {
    private String etValue,rbValue,spValue;

    public ProductModel(String etValue, String rbValue, String spValue) {
        this.etValue = etValue;
        this.rbValue = rbValue;
        this.spValue = spValue;
    }

    public String getEtValue() {
        return etValue;
    }

    public void setEtValue(String etValue) {
        this.etValue = etValue;
    }

    public String getRbValue() {
        return rbValue;
    }

    public void setRbValue(String rbValue) {
        this.rbValue = rbValue;
    }

    public String getSpValue() {
        return spValue;
    }

    public void setSpValue(String spValue) {
        this.spValue = spValue;
    }
}
