// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
@com.machinezoo.stagean.ApiIssue("Add representation for input DPI and perhaps other options.")
/*
 * Workaround for JDK bug: https://bugs.openjdk.java.net/browse/JDK-8274470
 */
@com.machinezoo.stagean.DraftCode("Record hashCode ignores record type, which makes key hashing very inefficient. Supply custom hashCode() in all keys.")
package com.machinezoo.sourceafis.transparency.keys;
