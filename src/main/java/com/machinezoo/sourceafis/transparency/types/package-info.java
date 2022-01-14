// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
/*
 * All deserialized objects are implemented as serializable records, because it is easy to implement,
 * it enables safe serialization with any serialization library, and we get equals(), hashCode(), and toString() for free.
 * Immutability of records is not an issue, because application code rarely writes transparency data
 * and when it does, record constructors are usually easier to use than mutable objects.
 * 
 * Non-trivial decoding of transparency data is avoided, because it is application-specific.
 * Convenience access methods are provided only where they aid in correct interpretation of the data.
 * Constructors validate record fields, so that getters can provide stronger guarantees.
 * Constructors don't try to "fix" the data, so that round-trip serialization preserves all details.
 */
package com.machinezoo.sourceafis.transparency.types;
