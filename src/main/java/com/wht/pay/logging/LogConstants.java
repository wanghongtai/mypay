package com.wht.pay.logging;

public final class LogConstants {

  private LogConstants() {

  }

  //common
  public static final String TRANSACTION_ID = "TransactionId";
  public static final String HOST_IP = "HostIp";
  public static final String SERVICE_NAME = "ServiceName";
  public static final String MESSAGE_TYPE = "MessageType";
  public static final String MESSAGE_NAME = "MessageName";
  public static final String HTTP_METHOD = "HttpMethod";
  public static final String SERVICE_VERSION = "ServiceVersion";

  //transaction log
  public static final String REMOTE_IP = "RemoteIp";
  public static final String SERVICE_ID = "ServiceId";
  public static final String MESSAGE_ID = "MessageId";

  //request response log
  public static final String URL = "Url";

  //event log
  public static final String CLIENT_ID = "ClientId";
  public static final String PARTNER_TRANSACTION_ID = "PartnerTransactionId";

}