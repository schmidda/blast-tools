## Generating output files
Searches should be conducted with blastn or blastp, or via diamond.

### BlastN
Example of blastN similarity command line:
> blastn -query query.fasta -db ~/blastDB/nt -evalue 0.00001 -out \
> blastn_out.bls --outfmt '6 qseqid sgi sacc length pident mismatch gapopen qstart qend qlen sstart send slen sstrand evalue bitscore qcovhsp stitle staxids qseq sseq sseqid qcovs qframe sframe'

### Blastp
Example of protein similarity screening command line using diamond 
(similar results can be obtained using blastP program).

Note: diamond is reported to be faster than blastP

> diamond blastp -d ~/diamondDB/nr -q query.fasta -o blastp_out.bls \
> --outfmt 6 qseqid sseqid pident nident length mismatch gapopen gaps \
> qstart qend qlen qframe sstart send slen evalue bitscore qcovhsp sallseqid

## Running BlastTools
BlastTools can be used to summarise the output file from the above searches. 
Basic usage is:
> java -jar BlastTools.jar -t blastn blastn_out.bls
> java -jar BlastTools.jar -t blastp blastp_out.bls

## Configuring the order of columns
If a different ordering of the search result columsn is desired from that expected by BlastTools, it is possible to configure the program as follows. First extract the config.json file inside BlastTools.jar:
    jar xf BlastTools.jar blasttools/config.json
This produces a folder blasttools with a file config.json. An example is:
>{
>    "blastn": { "qseqid":0, "sacc": 2, "sstart":10, "send":11,"stitle":17,"pident":4,"sstrand":13,"slen":12},
>    "blastp": { "qseqid":1, "sacc": 0, "sstart":12, "send":13,"stitle":19,"pident":2,"slen":14}
>}

The keys "blastn" and "blastp" cannot be changed, nore can the names of the 
columns (since these are set by the blastp and blastn programs). However the 
numbers refer to the column indices in the output of blastn or blastp/diamond. 
Change them to whatever you want and then update the config file back in 
BlastTools.jar file:
> jar uf BlastTools.jar blasttools/config.json

## Building
A NetBeans project is provided for loading the source files. Once built the 
script build-jar.sh should be run to rebuild the BlastTools.jar file in the 
top-level directory. This file contains both blasttools and json-simple which is 
a dependency.
