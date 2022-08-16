package com.example.nobelprizesearch.model.domain;

import androidx.annotation.NonNull;

import java.util.Locale;

public enum Field {
    CHE {
        @Override
        public String getFullName() {
            return "Chemistry";
        }
    },  // Chemistry
    ECO {
        @Override
        public String getFullName() {
            return "Economy";
        }
    },
    LIT {
        @Override
        public String getFullName() {
            return "Literature";
        }
    },
    PEA {
        @Override
        public String getFullName() {
            return "Peace";
        }
    },
    PHY {
        @Override
        public String getFullName() {
            return "Physics";
        }
    },
    MED {
        @Override
        public String getFullName() {
            return "Medicine";
        }
    };

    public abstract String getFullName();


    @NonNull
    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static Field getFieldFromName(String name){
        for (Field f : Field.values()) {
            if (name.equals(f.getFullName())) {
                return f;
            }
        }

        throw new IllegalStateException("Filed with name = '" + name + "' not found");
    }
}
