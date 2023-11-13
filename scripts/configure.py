# This script generates and updates project configuration files.

# Run this script with rvscaffold in PYTHONPATH
import rvscaffold as scaffold

class Project(scaffold.Java):
    def script_path_text(self): return __file__
    def repository_name(self): return 'sourceafis-transparency-java'
    def pretty_name(self): return 'SourceAFIS Transparency for Java'
    def pom_name(self): return 'SourceAFIS Transparency'
    def pom_description(self): return 'Parsers for SourceAFIS algorithm transparency data.'
    def inception_year(self): return 2018
    def homepage(self): return self.website() + 'transparency/'
    def jdk_version(self): return 17
    def stagean_annotations(self): return True
    def has_website(self): return False
    def has_javadoc(self): return False
    def md_description(self): return '''\
        SourceAFIS Transparency API for Java provides convenient and strongly typed interface
        to [algorithm transparency](https://sourceafis.machinezoo.com/transparency/) data
        produced by [SourceAFIS](https://sourceafis.machinezoo.com/) fingerprint recognition engine.
        This is a Java library, but it can process transparency data from any of the language ports of SourceAFIS.
    '''
    
    def documentation_links(self):
        yield 'SourceAFIS algorithm transparency', self.homepage()
    
    def dependencies(self):
        yield from super().dependencies()
        yield self.use('com.machinezoo.sourceafis:sourceafis:3.18.1')
        yield self.use_commons_lang()
        yield self.use_commons_io()
        yield self.use_guava('test')
        yield from self.use_jackson_cbor()
        yield self.use_junit()
        yield self.use_hamcrest()
        yield self.use_slf4j_test()
    
    def javadoc_links(self):
        yield from super().javadoc_links()
        yield 'https://sourceafis.machinezoo.com/javadoc/'

Project().generate()
