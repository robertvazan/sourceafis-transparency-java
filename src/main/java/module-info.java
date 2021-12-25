// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
module com.machinezoo.sourceafis.transparency {
	exports com.machinezoo.sourceafis.transparency;
	requires com.machinezoo.noexception;
	requires transitive com.machinezoo.sourceafis;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.dataformat.cbor;
	/*
	 * Serialization needs reflection access.
	 */
	opens com.machinezoo.sourceafis.transparency to com.fasterxml.jackson.databind;
}
