package org.kodluyoruz.mybank.Accounts;

//@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AccountTypeEnum {

    vadesiz,
    birikim;


/*
    private static Map<String, AccountTypeEnum> FORMAT_MAP = Stream
            .of(AccountTypeEnum.values())
            .collect(Collectors.toMap(s -> s.formatted, Function.identity()));
    private final String formatted;


    AccountTypeEnum(String formatted) {
        this.formatted = formatted;
    }

    @JsonCreator // This is the factory method and must be static
    public static AccountTypeEnum fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }

 */

}
