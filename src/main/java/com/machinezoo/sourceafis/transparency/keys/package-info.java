// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
@com.machinezoo.stagean.ApiIssue("Add representation for input DPI and perhaps other options.")
/*
 * Workaround for JDK bug: https://bugs.openjdk.java.net/browse/JDK-8274470
 */
@com.machinezoo.stagean.DraftCode("Record hashCode ignores record type, which makes key hashing very inefficient. Supply custom hashCode() in all keys.")
/*
 * Keys are records, so that they are easily serializable and so that they get automatic equals(), hashCode(), and toString().
 * Mixin interfaces (e.g. SerializedObjectKey) define common serialization methods (e.g. CBOR). Keys can implement one of these mixins.
 * Marker interfaces (e.g. ContextKey) are provided as a convenient way to identify interesting groups of keys.
 * Besides keys produced by SourceAFIS algorithm, this package includes context keys that applications can use
 * to bundle algorithm inputs and outputs (e.g. input image, output template) in the same archive.
 */
package com.machinezoo.sourceafis.transparency.keys;
