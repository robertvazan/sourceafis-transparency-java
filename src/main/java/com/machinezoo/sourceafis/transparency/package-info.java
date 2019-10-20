// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
/*
 * API design principles:
 * - easy-to-use view of transparency data
 * - lossless representation of transparency data
 * - mutable to allow construction of fallback and test data
 * - public fields for simplicity of implementation
 * - compatibility only with the latest SourceAFIS (could be extended later)
 * 
 * API scope:
 * - read transparency data
 * - create fallbacks and test data
 * - parsing of individual files and whole archives
 * - methods supporting visualization
 * - features supporting basic analysis of the data
 * - feature-rich core classes (IntPoint, ...) for universal use
 */
package com.machinezoo.sourceafis.transparency;
