// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
import com.machinezoo.stagean.*;

@NoDocs
@DraftTests("Reach 100% coverage.")
@DraftApi
/*
 * Aside from just dismissing all compatibility completely, there's the option of versioned classes:
 * 
 * - modified types and keys get version suffix (e.g. 3v14), old types and keys are immediately deprecated
 * - deprecated types and keys are regularly removed when major version of this library is incremented
 * - when unversioned symbol is removed, latest versioned symbol is copied to it and the versioned symbol is deprecated
 * - key parsing requires version as parameter, key objects know which versions they apply to
 * - keys with the same name are tolerated as long as they apply to different versions
 * - TransparencyBuffer gets SourceAFIS version from VersionKey
 * - visualizations are not versioned, they instead support several key versions as dependencies
 * 
 * Unstable 0.x releases do not need to bother with compatibility. This can be delayed until release 1.0.
 */
@ApiIssue("Figure out how to provide compatibility over a range of recent SourceAFIS versions.")
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
