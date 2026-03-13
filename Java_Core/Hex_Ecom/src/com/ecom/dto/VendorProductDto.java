package com.ecom.dto;

public record VendorProductDto(
    String vendorName,
    int numberOfProducts,
    double avgSellingPrice) {
}