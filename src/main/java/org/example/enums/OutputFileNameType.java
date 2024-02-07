package org.example.enums;

public enum OutputFileNameType {
    INTEGER {
        @Override
        public String toString() {
            return "integers.txt";
        }
    },
    FLOAT {
        @Override
        public String toString() {
            return "floats.txt";
        }
    },
    STRING {
        @Override
        public String toString() {
            return "strings.txt";
        }
    }

}
