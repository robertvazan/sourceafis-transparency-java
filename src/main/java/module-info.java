// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
module com.machinezoo.sourceafis.transparency {
	exports com.machinezoo.sourceafis.transparency;
	requires com.machinezoo.noexception;
	/*
	 * Transitive, because we provide FingerprintTransparency implementation in the API.
	 */
	requires transitive com.machinezoo.sourceafis;
	/*
	 * Needed for setVisibility(PropertyAccessor.FIELD, Visibility.ANY).
	 */
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.dataformat.cbor;
	requires org.apache.commons.io;
	requires it.unimi.dsi.fastutil;
	/*
	 * Serialization needs reflection access.
	 */
	opens com.machinezoo.sourceafis.transparency to com.fasterxml.jackson.databind;
}
