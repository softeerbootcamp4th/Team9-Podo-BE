package com.softeer.podo.event.util;

import lombok.Getter;

@Getter
public enum Result {
    SAFETY(1L),
    ADVENTURE(2L),
    SENS(3L),
    EARLYADOPTER(4L),
    ;

    private long id;
    Result(long id) {
        this.id = id;
    }
}
