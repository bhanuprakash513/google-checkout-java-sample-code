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

package com.google.checkout.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.checkout.CheckoutRequest;
import com.google.checkout.CheckoutResponse;
import com.google.checkout.MerchantConstants;

/**
 * The default superclass for all the checkout request classes.
 * 
 * @author simonjsmith, ksim
 * @date March 6th, 2007
 * @version 1.1
 * @todo Not unit tested yet.
 */
public abstract class AbstractCheckoutRequest implements CheckoutRequest {

  protected MerchantConstants merchantConstants;

  /**
   * Constructor which takes an instance of MerchantConstants.
   * 
   * @param merchantConstants
   *          The MerchantConstants.
   * 
   * @see MerchantConstants
   */
  public AbstractCheckoutRequest(MerchantConstants merchantConstants) {
    this.merchantConstants = merchantConstants;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.checkout.CheckoutRequest#send()
   */
  public CheckoutResponse send() {
    try {
      URL url = new URL(getPostUrl());
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setDoInput(true);
      connection.setDoOutput(true);
      connection.setUseCaches(false);
      connection.setInstanceFollowRedirects(true);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Authorization", "Basic "
          + merchantConstants.getHttpAuth());
      connection.setRequestProperty("Host", connection.getURL().getHost());

      // Changed to allow i18n character sets to be processed properly
      connection.setRequestProperty("content-type",
          "application/xml; charset=UTF-8");
      connection.setRequestProperty("accept", "application/xml");

      PrintWriter output = new PrintWriter(new OutputStreamWriter(connection
          .getOutputStream()));
      output.print(getXml());
      output.flush();
      output.close();

      int responseCode = ((HttpURLConnection) connection).getResponseCode();
      InputStream inputStream;

      if (responseCode == HttpURLConnection.HTTP_OK) {
        inputStream = ((HttpURLConnection) connection).getInputStream();
      } else {
        inputStream = ((HttpURLConnection) connection).getErrorStream();
      }

      // Get the response
      BufferedReader reader = new BufferedReader(new InputStreamReader(
          inputStream));

      StringBuffer responseXml = new StringBuffer();
      String line;

      while ((line = reader.readLine()) != null) {
        responseXml.append(line + "\n");
      }

      reader.close();

      return new CheckoutResponseImpl(responseXml.toString());
    }

    catch (MalformedURLException murle) {
      System.err.println("MalformedURLException encountered.");
    } catch (IOException ioe) {
      System.err.println("IOException encountered.");
    }
    return null;
  }

}