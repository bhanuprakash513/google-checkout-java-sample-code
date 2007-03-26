package com.google.checkout.orderprocessing.test;

import com.google.checkout.CheckoutResponse;
import com.google.checkout.EnvironmentType;
import com.google.checkout.MerchantConstants;
import com.google.checkout.checkout.impl.MerchantConstantsImpl;
import com.google.checkout.orderprocessing.CancelOrderRequest;
import com.google.checkout.orderprocessing.impl.CancelOrderRequestImpl;

/**
 * @author 		ksim
 * @version		1.0 - ksim - March 10th, 2007 - Initial Version
 *
 */

public final class CancelOrderRequestTest {
	public static final void testGeneralCancelOrderRequest() throws Exception
	{
//  TODO:  fix this and port to JUnit.
		MerchantConstants myMConstants = new MerchantConstantsImpl("248088419036992", "Q2x_yWSVUHokvFxw1MqVjg", EnvironmentType.Sandbox, "USD", "", "", "");
		CheckoutResponse cResponse;
		String cResponseStr;
		
		CancelOrderRequest cor = 
			new CancelOrderRequestImpl(myMConstants, "375053533130049", "User Decided to Cancel the Order", "Not too much to comment on");
		
		System.out.println("XML To Send: "+cor.getXml());
		
		cResponse = cor.send();
		
		cResponseStr = cResponse.getXml();
		
		if (cResponseStr != null)
			System.out.println(new StringBuffer("XML Received: ").append(cResponseStr));
		else
			System.out.println("No Response XML was sent.");
		
		System.out.println("Success!");
		return;
	}
	
	public static final void testAddCancelOrderRequest() throws Exception
	{
		MerchantConstants myMConstants = new MerchantConstantsImpl("248088419036992", "Q2x_yWSVUHokvFxw1MqVjg", EnvironmentType.Sandbox, "USD", "", "", "");
		CheckoutResponse cResponse;
		String cResponseStr;
		
		CancelOrderRequest cor = 
			new CancelOrderRequestImpl(myMConstants, "375053533130049", "User Decided to Cancel the Order");
		
		cor.setComment("Not too much to comment on");
		
		System.out.println("XML To Send: "+cor.getXml());
		
		cResponse = cor.send();
		
		cResponseStr = cResponse.getXml();
		
		if (cResponseStr != null)
			System.out.println(new StringBuffer("XML Received: ").append(cResponseStr));
		else
			System.out.println("No Response XML was sent.");
		
		System.out.println("Success!");
		return;
	}
	
	public static final void testAddExceedStrLimitCancelOrderRequest() throws Exception
	{
		MerchantConstants myMConstants = new MerchantConstantsImpl("248088419036992", "Q2x_yWSVUHokvFxw1MqVjg", EnvironmentType.Sandbox, "USD", "", "", "");
		CheckoutResponse cResponse;
		String cResponseStr;
		
		CancelOrderRequest cor = 
			new CancelOrderRequestImpl(myMConstants, "375053533130049", "User Decided to Cancel the Order, but here are extra characters: hfaoehfoahefiahfoiaheoifhaewoihaoiwehfoiawhfeoiahfeoihefoihfoihaefoihaefoihaefoihaoifehfoieahfoahefoiahofhaefhoaehffhaiehfao");

		cor.setComment("Not too much to comment on, but here are extra characters: hfaoehfoahefiahfoiaheoifhaewoihaoiwehfoiawhfeoiahfeoihefoihfoihaefoihaefoihaefoihaoife");
		
		System.out.println("XML To Send: "+cor.getXml());
		
		cResponse = cor.send();
		
		cResponseStr = cResponse.getXml();
		
		if (cResponseStr != null)
			System.out.println(new StringBuffer("XML Received: ").append(cResponseStr));
		else
			System.out.println("No Response XML was sent.");
		
		System.out.println("Success!");
		return;
	}
	
	
	public static final void testAddMultCancelOrderRequest() throws Exception
	{
		MerchantConstants myMConstants = new MerchantConstantsImpl("248088419036992", "Q2x_yWSVUHokvFxw1MqVjg", EnvironmentType.Sandbox, "USD", "", "", "");
		CheckoutResponse cResponse;
		String cResponseStr;
		
		CancelOrderRequest cor = 
			new CancelOrderRequestImpl(myMConstants, "375053533130049", "User Decided to Cancel the Order");
		
		cor.setComment("Not too much to comment on");
		cor.setComment("Not too much to comment on");
		
		System.out.println("XML To Send: "+cor.getXml());
		
		cResponse = cor.send();
		
		cResponseStr = cResponse.getXml();
		
		if (cResponseStr != null)
			System.out.println(new StringBuffer("XML Received: ").append(cResponseStr));
		else
			System.out.println("No Response XML was sent.");
		
		System.out.println("Success!");
		return;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//testGeneralCancelOrderRequest();
		//testAddCancelOrderRequest();
		//testAddExceedStrLimitCancelOrderRequest();
		//testAddMultCancelOrderRequest();
	}
}
