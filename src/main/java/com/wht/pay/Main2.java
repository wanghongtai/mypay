package com.wht.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.ExtendParams;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.wht.pay.utils.ZxingUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.tools.Shell.SUCCESS;

public class Main2 {
    private static Log log = LogFactory.getLog(Main2.class);
    private static AlipayClient alipayClient;

    public static void main(String[] args) {
        String serverUrl = "https://openapi.alipaydev.com/gateway.do";
        String appId = "2016091500520624";
        //String pulicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgA1q3IwzAkaLNsF6nOFdCZEAoRKT5QLSj8hdcxezlGF0iNWlG6MhKiNxQM6gEEvhuDKoZzTO3CnnQz9sUbzk1/AYeVRRXny3kvoUtxDd7s2SmFh0MMDjiuAgwnNPxpN5k/liDl0op4S9LNpWptG9htsyx7t05dv5yVr4c4JdT+kBs3NIqdv2XQqF3lSo35SEdlm9Y6Ib6/oEiKkJY5AwWP5QOu8JjgPlOXArWEnAr0/77qRL3OdJ4UFay5ozr2pJHkcA41I4exOapb/Bt7Neg8r0JHRpaTElkydmyI/aAbr1bTj29ckY613A1RgD0+IPF7ZHc45WukCEv33121FP9QIDAQAB";
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCADWrcjDMCRos2wXqc4V0JkQChEpPlAtKPyF1zF7OUYXSI1aUboyEqI3FAzqAQS+G4MqhnNM7cKedDP2xRvOTX8Bh5VFFefLeS+hS3EN3uzZKYWHQwwOOK4CDCc0/Gk3mT+WIOXSinhL0s2lam0b2G2zLHu3Tl2/nJWvhzgl1P6QGzc0ip2/ZdCoXeVKjflIR2Wb1johvr+gSIqQljkDBY/lA67wmOA+U5cCtYScCvT/vupEvc50nhQVrLmjOvakkeRwDjUjh7E5qlv8G3s16DyvQkdGlpMSWTJ2bIj9oBuvVtOPb1yRjrXcDVGAPT4g8Xtkdzjla6QIS/ffXbUU/1AgMBAAECggEAfvPOdCWTFLh3oKulMg/EO43Ev+w5MBziQKZ273oaEguki1y0une6k5pb19KComV4vTOL/fY+98ubefJRI6BQiT2CJhfQLAsdvNuu2egg/3ZT1jIrz4IMlUyrbd0AxHDFYk0mR/NYMOzK2MMGkZDzg9WhvJ8+v/yc4mIMXAhtFqEWgUHK9Vr30xd1FYhi4YZChgHs98L3amGtd83FpxHlMkSeckNEpRUaAjF+t+0VaPYLow0Yg0A2FmXC2b96Eqm6HPFVp74PY/czMm7ce3HEeBpr35bHRtCBKlF/9Lsj9ZiEEViaZhlXKP9uxmL7cc1P1KRGGlQLqWFBcfC8HmhFqQKBgQC3q9s2wghKWDYfoKGbbyBPDRnCLg8TaGgmFQ/BPqCQ2ijfkv0rki16dJoSTqeJR03PCiKWGT2bygpcgLuGLJONpVCtnHKQDsdh13twsV4q14zQE/EIHsP2VTW3via2aYluAnClbwlls8v29HeKhHIsX7UI6POr4RxaTBp+CV6k0wKBgQCyeowhet6MuA6TyLC14lUQ/wpeifJShrOmw95CHNpUe/9mDcm69psvpcBaY64YJVZbGNffWp5Z4b3A4o1XIRBJCxWpeQYJi3wYvj8WodZVPhFh1BgW439xWDy9igIo1PRxpyQnY4qrimD8+PosZc48NoTgxic/egBjFIMA/fDbFwKBgF/bHhYO37rFHMIW2CDBt0/qW2YfGZkcZTZyyX0mDTBr2ucL1HsX2ApPrlUdnYwknXQPKwvA8olKwWuTd8rHmt5Sx69Dtfp+7AmhJvgiaS3cvs2diq6dN9JomBdj8tpCbAnw1g9Qysu8MReacITnKJsF+/pBcWJjoqNxkmXyx+UnAoGBALEMiD4eGO8XFr7bcxC6+Q/nMG4gAdaD8WsZGj4TSTJN30lpnBhv0fouPuZrw6QIReYQevUCe9tF6GqTUwp/rhwHdvGrMKX/TJdvAj59RJVDARM+xrbN7vqNQZP0d7fxqJul5mN8h28r0pKgmeu5AiERJDel2gFKEWA4VMyDHI75AoGACPBu+gm4SQ5te7nwfE79AXWQv18m8kP0kxL3rb6KoNRp8KLsoDGH0xMBvsGgn4EBHioWvEyZ/2ao0GeMVPrhXBLIIbV2xUnhmV3PzFNLpQvtnjpMC1utgOT4mKzfWSdIo4tM3dua4SHN6ZT+uRNQwu7sz2h6rnohb66gZJ0M90g=";
        String format = "json";
        String charset = "utf-8";
        String alipayPulicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA55LAJF+ftcGo01NN0dWgE1aF5S4S52F9C6X1VcaOb9Xg3t/1XoeRjOAHEZI4IaZKml49p/yacCepABGr2kR0Ox3OIDQIGg9UnGR6HM1g9SwW/+CQKC7K4OdjVR7k1tLVU5XGN2YbBzUSoA/A9EnGOnUfRxwGRUkZX7wDOOTzRzS8F8JeyQvflD+n4DrjBmv5P3JamLXWYSJIJ6ptNIitE3tO06SVtPxZEdG1x8RbiHyoKkh8vSX0smKOxtBV7r7sOtZeFHIw4w/EPCjoff0nFCC6knaIXB2HN2PAqAxymwy62d0fyPeNDlLG/MRBzrEjQC3tKl/bbJHlegvS/4gRHwIDAQAB";
        String signType = "RSA2";

        alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset,
                alipayPulicKey, signType);

        Main2 main2 = new Main2();
        // 测试当面付2.0生成支付二维码
        main2.test_trade_precreate(alipayClient);


    }


    // 测试当面付2.0生成支付二维码
    public void test_trade_precreate(AlipayClient alipayClient) {
        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = "tradeprecreate" + System.currentTimeMillis()
                + (long) (Math.random() * 10000000L);

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = "xxx品牌xxx门店当面付扫码消费";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = "0.01";

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequest precreateRequest = new AlipayTradePrecreateRequest();
        precreateRequest.setBizContent("{\"out_trade_no\":\"tradeprecreate15249394193513699279\"," +
                "\"seller_id\":\"\"," +
                "\"total_amount\":\"0.01\"," +
                "\"undiscountable_amount\":\"0\"," +
                "\"subject\":\"xxx品牌xxx门店当面付扫码消费\"," +
                "\"body\":\"购买商品3件共20.00元\"," +
                "\"goods_detail\":[{\"goods_id\":\"goods_id001\",\"goods_name\":\"xxx小面包\",\"quantity\":1,\"price\":\"10\"}," +
                "{\"goods_id\":\"goods_id002\",\"goods_name\":\"xxx牙刷\",\"quantity\":2,\"price\":\"5\"}]," +
                "\"operator_id\":\"test_operator_id\"," +
                "\"store_id\":\"test_store_id\"," +
                "\"extend_params\":{\"sys_service_provider_id\":\"2088100200300400500\"}," +
                "\"timeout_express\":\"120m\"}");

        AlipayTradePrecreateResponse response;

        try {
            response = alipayClient.execute(precreateRequest);
            if(response.isSuccess()){
                log.info("支付宝预下单成功: )");

                // 需要修改为运行机器上的路径
                String filePath = String.format("C:\\Users\\Hunter\\Desktop\\qr-%s.png",
                        response.getOutTradeNo());
                log.info("filePath:" + filePath);
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);

            } else {
                System.out.println("调用失败");
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }

}
