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

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonUsingRequestParam() {
        return new PersonV1("Eleri Doe");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonUsingRequestParam() {
        String firstName = "John";
        String lastName = "Doe";
        return new PersonV2(new Name(firstName, lastName));
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionUsingHeader() {
        return new PersonV1("Eleri Doe");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionUsingHeader() {
        String firstName = "John";
        String lastName = "Doe";
        return new PersonV2(new Name(firstName, lastName));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionUsingAcceptHeader() {
        return new PersonV1("Eleri Doe");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionUsingAcceptHeader() {
        String firstName = "John";
        String lastName = "Doe";
        return new PersonV2(new Name(firstName, lastName));
    }
}
