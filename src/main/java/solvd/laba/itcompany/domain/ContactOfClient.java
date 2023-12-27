package solvd.laba.itcompany.domain;

import lombok.Data;

@Data
public class ContactOfClient {
    private Long id;
    private Client client;
    private String phoneNumber;
    private String address;
}
