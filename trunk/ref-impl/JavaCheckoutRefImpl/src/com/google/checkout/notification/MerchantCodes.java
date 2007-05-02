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

package com.google.checkout.notification;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.checkout.util.Utils;

/**
 * TODO
 * 
 * @author simonjsmith
 * 
 */
public abstract class MerchantCodes {

	protected Document document;

	protected Element element;

	/**
	 * TODO
	 * 
	 * @return
	 */
	public float getAppliedAmount() {
		return Utils.getElementFloatValue(document, element, "applied-amount");
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public float getCalculatedAmount() {
		return Utils.getElementFloatValue(document, element,
				"calculated-amount");
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public String getCode() {
		return Utils.getElementStringValue(document, element, "code");
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public String getMessage() {
		return Utils.getElementStringValue(document, element, "message");
	}
}