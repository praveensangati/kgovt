package com.kgovt.datatable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.kgovt.datatable.paging.Direction;
import com.kgovt.models.ApplicationDetailes;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

public final class ApplicationDetailesComparators {

    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    static class Key {
        String name;
        Direction dir;
    }

    static Map<Key, Comparator<ApplicationDetailes>> map = new HashMap<>();

    static {
        map.put(new Key("name", Direction.asc), Comparator.comparing(ApplicationDetailes::getApplicantNumber));
        map.put(new Key("name", Direction.desc), Comparator.comparing(ApplicationDetailes::getApplicantNumber)
                                                           .reversed());
    }

    public static Comparator<ApplicationDetailes> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }

    private ApplicationDetailesComparators() {
    }
}
