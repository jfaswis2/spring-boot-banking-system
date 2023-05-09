package com.example.springbootbankingsystem.dto;

public record PrimaryAddressDTO(
        Long idAccountHolder,
        String street,
        String city,
        String province,
        String country,
        String postalCode,
        String apartmentNumber
) {
}
