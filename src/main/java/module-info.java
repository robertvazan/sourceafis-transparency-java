// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
import com.machinezoo.stagean.*;

@NoDocs("Add javadoc and website.")
@DraftTests("Reach 100% coverage.")
@DraftApi
@ApiIssue("Allow keys with the same name that differ in supported MIME types.")
@ApiIssue("""
	Allow keys with the same name that differ in SourceAFIS versions they apply to.
	
	This is required to support multiple SourceAFIS versions:
	
	- Key parsing requires version as parameter.
	- TransparencyBuffer gets SourceAFIS version from VersionKey.
	- Modified types and keys get version suffix (e.g. 3v14). Old types and keys are immediately deprecated.
	- Deprecated types and keys are regularly removed when major version of this library is incremented.
	- When unversioned symbol is removed, latest versioned symbol is copied to it and the versioned symbol is deprecated.
	
	Unstable 0.x releases do not need to bother with compatibility. This can be delayed until release 1.0.
	""")
module com.machinezoo.sourceafis.transparency {
	exports com.machinezoo.sourceafis.transparency;
	exports com.machinezoo.sourceafis.transparency.types;
	exports com.machinezoo.sourceafis.transparency.keys;
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
	/*
	 * Records should not need this, but there's some bug in Jackson:
	 * https://github.com/FasterXML/jackson-databind/issues/3352
	 */
	opens com.machinezoo.sourceafis.transparency.types to com.fasterxml.jackson.databind;
}
