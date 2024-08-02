module org.example {
    requires transitive javafx.controls;
	requires static lombok;
	requires org.apache.logging.log4j;
    requires java.sql;
    exports ds.project;
}