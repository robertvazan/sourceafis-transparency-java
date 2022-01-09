// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
/*
 * Principles guiding design of this package:
 * - Compatibility is offered only for latest SourceAFIS in order to simplify implementation.
 * - All classes are immutable, because transparency data is usually read-only.
 * - Non-trivial decoding of transparency data is avoided, because it is application-specific.
 * - Convenience access methods are provided only where they aid in correct interpretation of the data.
 * - Constructors validate the data, so that getters can provide stronger guarantees.
 * - Constructors don't try to "fix" the data, so that round-trip serialization preserves all details.
 */
package com.machinezoo.sourceafis.transparency.types;
