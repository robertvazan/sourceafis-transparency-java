# This script generates and updates project configuration files.

# We are assuming that project-config is available in sibling directory.
# Checkout from https://github.com/robertvazan/project-config
import pathlib
project_directory = lambda: pathlib.Path(__file__).parent.parent
config_directory = lambda: project_directory().parent/'project-config'
exec((config_directory()/'src'/'java.py').read_text())

project_script_path = __file__
repository_name = lambda: 'sourceafis-transparency-java'
pretty_name = lambda: 'SourceAFIS Transparency for Java'
pom_subgroup = lambda: 'sourceafis'
pom_artifact = lambda: 'sourceafis-transparency'
pom_name = lambda: 'SourceAFIS Transparency'
pom_description = lambda: 'Parsers for SourceAFIS algorithm transparency data.'
inception_year = lambda: 2018
homepage = lambda: website() + 'transparency/'
jdk_version = lambda: 17
stagean_annotations = lambda: True
has_website = lambda: False
has_javadoc = lambda: False
project_status = lambda: experimental_status()
md_description = lambda: '''\
    SourceAFIS Transparency API for Java provides convenient and strongly typed interface
    to [algorithm transparency](https://sourceafis.machinezoo.com/transparency/) data
    produced by [SourceAFIS](https://sourceafis.machinezoo.com/) fingerprint recognition engine.
    This is a Java library, but it can process transparency data from any of the language ports of SourceAFIS.
'''

def documentation_links():
    yield 'SourceAFIS algorithm transparency', homepage()

def dependencies():
    use('com.machinezoo.sourceafis:sourceafis:3.17.1')
    use_commons_lang()
    use_commons_io()
    use_jackson_cbor()
    use_junit()
    use_hamcrest()
    use_slf4j_test()

javadoc_links = lambda: [
    'https://sourceafis.machinezoo.com/javadoc/'
]

generate()
