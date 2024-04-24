package com.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Eleri Doe");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        String firstName = "John";
        String lastName = "Doe";
        return new PersonV2(new Name(firstName, lastName));
    }
}
