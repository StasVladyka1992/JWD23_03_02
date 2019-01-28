package by.vladyka.epam.entity;

public enum Currency {
    BYN, RYB, EUR, USD;

    public static Currency getEnumCurrency (String value){
        switch (value){
            case "руб.":{
                return BYN;
            }
            case "рос. руб.":{
                return RYB;
            }
            case "евро":{
                return EUR;
            }
            case "дол. США":{
                return USD;
            }
        }
    return null;}
}
