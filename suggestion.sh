#!/bin/bash
# erzeugt JAR (target/suggestion.jar) für zusammengesetzte Komponente
mkdir -p target/META-INF/resources
cp -R src/main/webapp/resources/jsfpraxis/ target/META-INF/resources
jar cf target/suggestion.jar -C target META-INF
