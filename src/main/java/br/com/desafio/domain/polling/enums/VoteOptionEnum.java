package br.com.desafio.domain.polling.enums;

public enum VoteOptionEnum {
    NAO(0, "Não"),
    SIM(1, "Sim");

    private final int value;
    private final String description;

    VoteOptionEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static VoteOptionEnum fromValue(int value) {
        for (VoteOptionEnum option : VoteOptionEnum.values()) {
            if (option.value == value) {
                return option;
            }
        }
        throw new IllegalArgumentException("Opção de voto inválida!: " + value);
    }
}

