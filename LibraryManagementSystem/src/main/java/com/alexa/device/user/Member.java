package org.example.user;

public class Member extends Person{
    private final String barCode;

    public Member(String name, String email, String barCode) {
        super(name, email);
        this.barCode = barCode;
    }
}
