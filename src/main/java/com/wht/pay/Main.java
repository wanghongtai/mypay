package com.wht.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {
    private static Log log = LogFactory.getLog(Main.class);
    private static AlipayClient alipayClient;
    public static void main(String[] args) {
        String serverUrl = "https://openapi.alipaydev.com/gateway.do";
        String appId = "2016091500520624";
        //String pulicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgA1q3IwzAkaLNsF6nOFdCZEAoRKT5QLSj8hdcxezlGF0iNWlG6MhKiNxQM6gEEvhuDKoZzTO3CnnQz9sUbzk1/AYeVRRXny3kvoUtxDd7s2SmFh0MMDjiuAgwnNPxpN5k/liDl0op4S9LNpWptG9htsyx7t05dv5yVr4c4JdT+kBs3NIqdv2XQqF3lSo35SEdlm9Y6Ib6/oEiKkJY5AwWP5QOu8JjgPlOXArWEnAr0/77qRL3OdJ4UFay5ozr2pJHkcA41I4exOapb/Bt7Neg8r0JHRpaTElkydmyI/aAbr1bTj29ckY613A1RgD0+IPF7ZHc45WukCEv33121FP9QIDAQAB";
        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCEvwiKeBajODnQS1JSJdSoczEZaGr5QDyFAxie8/fpd/fWTQcV3KYqIZkPrBwR/mhT43ZREcLoXpiNhjtkhSOoacK+MYbkmnIqwdBJkRl99+u3vQPHgKTNJgB2MuCn8u/wKd5JmWwrpw/Ar6+7uRqcI6tnHr1NL6D8gIcckqqtyIqmXs78T26KZp/UL28nUktkAHlFi4TNjqJYoKw/sNs/0OFLGrWnXjhDCotspukRGh950yoQzGTu2D6ttahVghxa6jgIwYv1eqUohyist7TE0sgGt2SOy0JWTVfTDJnozN3oUQsm1Q0EltWOTxmkOzeBihYBloms4eOQVGlhpkVrAgMBAAECggEAKMea8xbjNW8fa5rIBk5y3vDMQ5btTfbq4J3L4YDnRwTA0UfGhnPLZCQAJm1kDi8Ok77L6SnAq6vrx4ZWFpJEGweqeM00OGQt8G6r8v2tTCJuenqJf69IcXFcMPN+Snn6NdhW3ltQfLSMxU/zd4Ls5syaakpqMx0PoqJFX4S7BDmmrCiAAQJe47n3xkzr2Ewblr5207R5VUd3Eo3zFgwI8/XtEtuSyxSNkaWrJ+qDacfwpTu2YgUFz0LN17rfMjUw/4IpthF03A1KknoT0cCLWO7H3kpN9Stv3iWI++4IT7pui9eHyHd1o2lt2xZphRLSNoUYRn8jaGr7d92Fy09aAQKBgQDHLCyh5umKJNCc5SKbW0gWEBj6WWm2L/uYlk3wcoHuQSwsQyUpgTu5mXAIgaqMyo/DCM6c4+SxyuWqm2FsOOC9NO3oojhHYJQz6JT3eevtGT2wWRuPAQY+cQUk8mg/5P2902X4O6SwkM31WW/4AdDe0tcPt7MMHG61eIrAnIMOawKBgQCqnv4HLCDjAnVNDHb8mR15QWpUly0mFpO7pdpfsN1OKonjzR3Y3Zn5OV/BfvJmazHRa90MqfGOcKbrko4hEKkC00uGSkfFhcEQUqrrGbDsdnFj2wArKSw2mO7yiA2lJ0nWrAcQu0/rZqLT6rY0kwM+96tY16xbh9ltJpav+BllAQKBgFwCISAtrucdOK2yADmqgnOGvjMwiPAb3GtRy/FuxG0O+Fv+LLWuuKt1oVacp+/gR2c+s0uwz13+r/gPTYMUB2YAhIL6JWt7xxrvr5lfXkiqZ2AG2f9G5t6DkW/O96/Elh9SAAIbc2oJistzpJD6LcYxuh3Nq83FeLMmW1q9MqG/AoGATdJfA155UZGZdx8sVmJwMxJHMXYSvcCmeXxYcjT1e+WjJygzaOsKg8ZLirIgVd6mxmJwXIpXByUSvXgHI8W5kPRXClVvDwTG3m8WTk1ViUUsyuLk3MrWw8BVnIG5145n+2ZTgqos9a3GrunrWWeEn3/Pw6H7l3QLE+H58SE0VAECgYBVWY8ZcOUlIVBEd9b4rQ6pnJ90iLCxTu73zjHhoLiDUQYHiurYEm1f30rSbVZYlvUvl56839xGQdOso5UCZ0Db2ojA15QVFqB6hl6bf0Fh0/M/szplPTkkRzn4FjPwZaSM1h6rxoGIPf+kEd2j4IW+NrDPXWjRUpuq+f9z1aDjJQ==";
        String format = "json";
        String charset = "utf-8";

        String alipayPulicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA55dtqIbEVg99zthWFcKK3O3jVgkVBGPpYRNy2b3Lf56gbr7sqt/shUkMgjuVfh4jI5YvGRjmBv161ZuS8T0vLiwuqvmoS4Y7pS6ULgcaMfTUk9zb59sheZYj5AHtMpZUxTz2EXzz5d0YUD9sxw3CYue7QjyI7E4DYnumZCznPX6cFMiNqGHFOXwcYmOma5CNfVl6p9fwFvbexQe92UjmHGwxfPNjCnYXABk9ayCQhysvQEVsT3Fx5+fiolC+Zma0rg5gMFJZYr9z475g46CGJnAN2ML07YBEpScaXcYmIWJizoNOUuEdu0SBMDAKmdZ3YC++5mJGuosVEjrZcoORIQIDAQAB";
        String signType = "RSA2";


        alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset,
                alipayPulicKey, signType);
        Main main = new Main();
        //条形码支付
//        main.tradePay();
        //查询账单
        main.downloadurlQuery();

    }

    /**
     * 条形码支付
     */
    private void tradePay(){
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradePayModel model = new AlipayTradePayModel();
        request.setBizModel(model);

        model.setOutTradeNo(System.currentTimeMillis()+"");
        model.setSubject("Iphone6 16G");
        model.setTotalAmount("0.01");
        model.setAuthCode("282702797643270227");//沙箱钱包中的付款码
        model.setScene("bar_code");
        AlipayTradePayResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.println(response.getBody());
            System.out.println(response.getTradeNo());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询账单
     */
    private void downloadurlQuery() {
        AlipayDataDataserviceBillDownloadurlQueryRequest request
                = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        //账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。
        request.setBizContent("{" +
                "\"bill_type\":\"trade\"," +
                "\"bill_date\":\"2016-04\"" +
                "  }");
        try {
            AlipayDataDataserviceBillDownloadurlQueryResponse response
                    = alipayClient.execute(request);
            System.out.println(request.getBizContent());
            System.out.println(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
