package bianquezi.com.br.cotamoeda.constant;

public class CoinConstants {

    public static class URL{
        public static String GET_URL = "https://api.hgbrasil.com/finance?format=json&key=";
    }

    public static class KEY{
        public static String KEY = "13093126";
    }

    public static class STATUS_CODE{
        public static int SUCCESS = 200;
        public static int FORBEDDEN = 403;
        public static int NOT_FOUND = 404;
        public static int INTERNAL_SERVER_ERROR = 500;
        public static int INTERNET_NOT_AVAILABLE = 901; // valor definido para esse projeto
    }

    public static class NAME{
        public static String DOLAR = "USD";
        public static String EURO = "EUR";
        public static String LIBRA = "GBP";
        public static String PESO_ARGENTINO = "ARS";
        public static String BITCOIN = "BTC";

    }

    public static class SIGN{
        public static String REAL_SIGN = "R$";
    }
}
