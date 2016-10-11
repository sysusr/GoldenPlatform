package com.tikt;

/**
 * Created by tikt on 15-11-11.
 */
public class Constants {
	/**
	 * 微信
	 */
	//请同时修改  androidmanifest.xml里面，.PayActivity里的属性<data android:scheme="wxb4ba3c02aa476ea1"/>为新设置的appid
//	public final static String wxAppId = "wxfb17f8cd209ffeea";
//  public final static String wxAppSecret = "d4624c36b6795d1d99dcf0547af5443d";
//	public final static String wxAPI_KEY = "ZBRsu6LBtXyhk7ZdwEYBuGu2f0gT6v8N";
//	public static final String wxMCH_ID = "1266019801";
	//优畅
	public final static String wxAppId = "wx96bbe1298e266a55";
	public final static String wxAppSecret = "ab1709cbe668435ac8f518926df327c8";
	//API密钥，在商户平台设置
	public final static String wxAPI_KEY = "ZBRsu6LBtXyhk7ZdwEYBuGu2f0gT6v8N";
	//商户号
	public static final String wxMCH_ID = "1266019801";
	/**
	 * QQ
	 */
	public final static String qqAppId = "1105384161";
	public final static String qqAppKey = "YXiX58bWwsL4OCYo";

	/**
	 * SINA
	 */
	public final static String sinaAppKey = "2917625743";
	public final static String sinaAppSecret = "145c1eec878741cb197156f960a7062e";
	public final static String sinaREDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
//	public final static String sinaAppKey = "3921700954";
//	public final static String sinaAppSecret = "04b48b094faeb16683c32669824ebdad";


	/**
	 * 蒲公英
	 */
	public final static String PGYAppID = "55b7d3400603633fe21643a59410f744";

	/**
	 * 支付宝
	 */
	// 公司PID
//	public static final String PARTNER = "2088911291986600";
	// 公司商户收款账号
//	public static final String SELLER = "xmpay@nbxuanma.com";
	// 公司商户私钥，pkcs8格式
//	public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKyQExn8FvJmADCWu0rzL1XKPBvMSmBo/g7K0RtdbMp0RlvvzhFYscikyF5Ln8F/3zO6obKfO+t1C3MQTYXODy10tmX9LyraNCDCOf2IJfEYFbb9kX7e2tlGVayh4avs9KInq8v0WYBdLyR0yY3/cZzBgbNZi1CfG3uSTnU7CWiRAgMBAAECgYEAilqlhgLxXv3bDxn7JpeeDxmrYdpEk9H6SF/ikh8wt43MT7AQNyY2vJkpsTjO6LSef3oWZQF4aEsal/GtRie1UDqiBgm9oIIXNBg2OFOQghNFflfhy1kDGI951HKatRrett2g0L3eGZ8NeSoCDEoeAvxzR3Cc5FJj4pYgcaxByrUCQQDUkCMQFkNOfH352G1K9UTofbxUMdXmTNpuCsXqu6uRZOx9l4UrUQ75q+L6Nwo6Pc2aaoGIcHzxWrUavbgD0QKDAkEAz9NlGr/ruPNU7kiqMXvFhr3Sa/xRjGYOiiw8tZB00gK6vAY+SQCIg/M7rIOp0yeEP4NBpELbDHgGxdCNwkUsWwJBAIKPXdn9sq28rawOkWLfL8oVoN2eFqH0Rp4co+BAEWumr9Ocg8FNcoNMXogN0oxfdbAJBzME1NlyO5cHPMWrVX0CQGL7vlXlPvJSAwuS1GzJFKU+V7BS9sw194rb0vZvHRTBdAsFtjiy5lJiUPOzqfkGAzj03RjNNuNTb3ZcBIiYxa0CQQCEqAS0ZdmB0ev4nqf/RiaOcx2fdb3+3sYuRntFPFensQjzv0gFNjf8twMo6qoLeul/VWi7BTPkQaZT0VIQnsDK";
	//优畅
	public static final String PARTNER = "2088121345789370";
	public static final String SELLER = "yckj@wybbm.cc";
	public static final String RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMijgfan72Ne+mGmyXq+pRzTmdlDyHckSfG4070YQNPLtN2bLqqSnX4TlyLxFkwHWcXBdVTC+MdgkwJK0h2VNKjEEPzPmB+NNQuONvg376T7ZN0SxjWBA2tg9xG5wOsdLkj6DY/BVCLE06u8R/4/oh4wanmoOTPQBWtL/NSgCiulAgMBAAECgYAzoN9weQZAq9ACEMkHrMcQA+My5R5QiiX87XKO1g7YcTK5U5VOjyu3pZLeiusR5VJUcNOgXAtoQpGRpW8r7Zj+3ylumOtlqxexpVE0EqnTbrypTv8a23cCaW6omOCSZPQwza7SedJsiXzz517kH6hovrzF0A1ikbEMqqQ2WTIqgQJBAPwEB1oRfTGy+RIFCbxxH3Tk67EGqXWd7wfvVzO6BKRb3wf7gcaW2RUowLDX0jC8e0JwRNR+gi3zXnKU1SBHzvcCQQDLz4sKQyJCScXHcidCNJecmc8zePYGRHxXUFH4CxnhKYWbH6dZ4CqouQuGib5L6eF8OUmFOqdyKtlsVn7PS8dDAkBV8NXE/EPSR/MyFu+8CGOuzEYnx1rQljwmW5NEO4JLvlyvbzBRVa3Pzmbvp5mxLwVlob2HLWEdZGnn7yNC79n/AkBK8PutQFY+jaXd2om4/GGrQZtAz/+W+mPGL1CUpnBTrNLl4VPF5nmJJNGqHMgOXed9qArypEih4EOmZH+odbLFAkBocVNeLdKnHGWDMX888i/4J9QGF41yzaIMsnPjWzdPDIgt+QmGUlMLZu2Tf695Z+2x7c8ix0p1iJgGFCm8jrO5";
	// 支付宝公钥
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADC" +
			"BiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM" +
			"6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML" +
			"9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

}
