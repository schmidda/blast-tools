#!/bin/bash
mkdir -p blasttools
cp lib/json-simple-1.1.1.jar blasttools/
cp dist/BlastTools.jar blasttools/
cd blasttools
rm -rf org
mkdir -p json
mv json-simple-1.1.1.jar json
cd json
jar xf json-simple-1.1.1.jar
mv org ..
cd ..
jar xf BlastTools.jar
rm BlastTools.jar
jar cf BlastTools.jar blast blasttools org
jar ufe BlastTools.jar blasttools.BlastTools blasttools/BlastTools.class
cp BlastTools.jar ..
cd ..
rm -rf blasttools
