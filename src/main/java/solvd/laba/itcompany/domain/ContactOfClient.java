package solvd.laba.itcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactOfClient {
    private Long id;
    private Client client;
    private String phoneNumber;
    private String address;
}
