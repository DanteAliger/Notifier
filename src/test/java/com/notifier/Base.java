package com.notifier;

import java.util.Objects;

public class Base {
    int a;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return a == base.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a) ;
    }

    Base(int a){
        this.a=a;

    }
}
