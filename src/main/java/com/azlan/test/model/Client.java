package com.azlan.test.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@Setter
@Getter
@NoArgsConstructor
public class Client implements Serializable {

    private static final long serialVersionUID = 7220990656743174443L;

    private Long id;
    private String firstName;
    private String lastName;

}
