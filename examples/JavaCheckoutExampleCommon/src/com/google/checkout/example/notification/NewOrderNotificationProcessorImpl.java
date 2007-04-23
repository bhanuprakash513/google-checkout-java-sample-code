/*******************************************************************************
 * Copyright (C) 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package com.google.checkout.example.notification;

import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.checkout.CheckoutException;
import com.google.checkout.MerchantConstants;
import com.google.checkout.example.GoogleOrder;
import com.google.checkout.notification.NewOrderNotificationProcessor;
import com.google.checkout.util.Utils;

public class NewOrderNotificationProcessorImpl extends AbstractNotificationProcessor implements
		NewOrderNotificationProcessor {

	MerchantConstants merchantConstants;
	
	public NewOrderNotificationProcessorImpl(MerchantConstants merchantConstants) {
		this.merchantConstants = merchantConstants;
	}
	
	public String process(String callbackXML) throws CheckoutException {

		String ack = "";
		try {
			Document document = Utils.newDocumentFromString(callbackXML);
			
			String orderNumber = Utils.getElementStringValue(document, document.getDocumentElement(), "google-order-number");
			Date timestamp = Utils.getElementDateValue(document, document.getDocumentElement(), "timestamp");
			String lastFulStatus = Utils.getElementStringValue(document, document.getDocumentElement(), "fulfillment-order-state");
			String lastFinStatus = Utils.getElementStringValue(document, document.getDocumentElement(), "financial-order-state");
			Element billing = Utils.findElementOrContainer(document, document.getDocumentElement(), "buyer-billing-address");
			String buyerEmail = Utils.getElementStringValue(document, billing, "email");
			String orderAmount = Utils.getElementStringValue(document, document.getDocumentElement(), "order-total");
			
			GoogleOrder order = GoogleOrder.findOrCreate(merchantConstants.getMerchantId(), orderNumber);			
			order.setLastFulStatus(lastFulStatus);
			order.setLastFinStatus(lastFinStatus);
			order.setBuyerEmail(buyerEmail);
			order.setOrderAmount(orderAmount);			
			ack = getAckString();
			
			order.addIncomingMessage(timestamp, document.getDocumentElement().getNodeName(), Utils.documentToStringPretty(document), ack);
		} catch (Exception e) {
      throw new CheckoutException(e);
		}
		return ack;
	}

}