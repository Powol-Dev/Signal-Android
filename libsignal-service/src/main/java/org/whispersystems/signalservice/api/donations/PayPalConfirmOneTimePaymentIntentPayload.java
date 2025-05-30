/*
 * Copyright 2025 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.signalservice.api.donations;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request JSON for confirming a PayPal one-time payment intent
 */
class PayPalConfirmOneTimePaymentIntentPayload {
  @JsonProperty
  private String amount;

  @JsonProperty
  private String currency;

  @JsonProperty
  private long level;

  @JsonProperty
  private String payerId;

  @JsonProperty
  private String paymentId;

  @JsonProperty
  private String paymentToken;

  public PayPalConfirmOneTimePaymentIntentPayload(String amount, String currency, long level, String payerId, String paymentId, String paymentToken) {
    this.amount       = amount;
    this.currency     = currency;
    this.level        = level;
    this.payerId      = payerId;
    this.paymentId    = paymentId;
    this.paymentToken = paymentToken;
  }
}
