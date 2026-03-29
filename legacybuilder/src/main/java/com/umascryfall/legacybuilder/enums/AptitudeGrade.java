package com.umascryfall.legacybuilder.enums;

public enum AptitudeGrade {
    G(0),
    F(1),
    E(2),
    D(3),
    C(4),
    B(5),
    A(6),
    S(7);

    private final int rank;

    AptitudeGrade(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public static AptitudeGrade fromRank(int rank) {
        for (AptitudeGrade grade : values()) {
            if (grade.getRank() == rank) {
                return grade;
            }
        }
        return rank > 7 ? S : G; // Fallback mapping although code shouldn't exceed limits
    }
}
