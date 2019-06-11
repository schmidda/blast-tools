#!/bin/bash
read -p 'version: ' VERSION
mkdir -p blasttools-$VERSION
rm -rf blasttools-$VERSION/*
cp -r BlastTools.jar build-jar.sh lib README.md build.xml examples nbproject src blasttools-$VERSION/
tar czf blasttools-$VERSION.tar.gz blasttools-$VERSION
