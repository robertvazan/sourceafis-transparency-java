// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
import com.machinezoo.stagean.*;

@NoDocs
@DraftTests("Reach 100% coverage.")
@DraftApi
@DraftCode
module com.machinezoo.sourceafis.transparency {
	exports com.machinezoo.sourceafis.transparency;
	exports com.machinezoo.sourceafis.transparency.types;
	requires com.machinezoo.stagean;
	requires com.machinezoo.noexception;
	/*
	 * Transitive, because we provide FingerprintTransparency implementation in the API.
	 */
	requires transitive com.machinezoo.sourceafis;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.dataformat.cbor;
	requires org.apache.commons.lang3;
	requires org.apache.commons.io;
	requires it.unimi.dsi.fastutil;
	/*
	 * Records should not need this, but there's some bug in Jackson:
	 * https://github.com/FasterXML/jackson-databind/issues/3352
	 */
	opens com.machinezoo.sourceafis.transparency.types to com.fasterxml.jackson.databind;
}
