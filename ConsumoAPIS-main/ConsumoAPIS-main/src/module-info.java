/**
 *
 */
/**
 *
 */
module ConsumoAPIS {
	requires java.management;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;

	requires java.desktop;

	exports consumoAPIS;

	requires transitive java.xml;
	requires com.fasterxml.jackson.annotation;
	requires java.sql;
	requires org.json;
	requires jasperreports;
}
