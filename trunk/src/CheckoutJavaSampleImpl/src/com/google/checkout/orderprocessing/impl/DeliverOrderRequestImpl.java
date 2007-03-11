package com.google.checkout.orderprocessing.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.checkout.MerchantConstants;
import com.google.checkout.impl.util.Constants;
import com.google.checkout.impl.util.Utils;
import com.google.checkout.orderprocessing.AbstractDeliverOrderRequest;

/**
 * @author 		ksim
 * @date   		March 10th, 2007
 * @version		1.0 - ksim - March 10th, 2007 - Initial Version
 *
 */

public final class DeliverOrderRequestImpl extends AbstractDeliverOrderRequest {
	
	Document document;
	Element root;
	
	public DeliverOrderRequestImpl(MerchantConstants merchantConstants, String googleOrderNo) {
		super(merchantConstants, googleOrderNo);

	      document = Utils.newEmptyDocument();
	      root =  (Element) document.createElementNS(Constants.checkoutNamespace, "deliver-order"); 
	      root.setAttributeNS("http://www.w3.org/2000/xmlns/","xmlns", Constants.checkoutNamespace);
	      root.setAttribute("google-order-number", googleOrderNo);
	      document.appendChild(root);
	}
	
	public DeliverOrderRequestImpl(MerchantConstants merchantConstants, String googleOrderNo, String carrier,
			String trackingNo, boolean sendEmail) {
		this(merchantConstants, googleOrderNo);
		
		Element trackingDataTag = Utils.findContainerElseCreate(document, root, "tracking-data");	
        Utils.createNewElementAndSet(document, trackingDataTag, "carrier", carrier);
        Utils.createNewElementAndSet(document, trackingDataTag, "tracking-number", trackingNo);
        
        Utils.createNewElementAndSet(document, root, "send-email", sendEmail);	
	}
	
	public void addTrackingData(String carrier, String trackingNo)
	{
		Element trackingDataTag = Utils.findContainerElseCreate(document, root, "tracking-data");	
        Utils.findElementElseCreateAndSet(document, trackingDataTag, "carrier", carrier);
        Utils.findElementElseCreateAndSet(document, trackingDataTag, "tracking-number", trackingNo);
	}
	
	public void addSendEmail(boolean sendEmail)
	{
		Utils.findElementElseCreateAndSet(document, root, "send-email", sendEmail);	
	}
	
	public String getXml() {
		return Utils.documentToString(document);
	}
	
	public String getXmlPretty() {
		return Utils.documentToString(document);

	}
}