# Identification/disambiguation of mathematical statements (definition) by semantic similarity

**Problem formulation**: given two statements, the system shall tell whether these statements are equivalent/ different

**Research question**: 

- what is needed for inter-document disambiguation of mathematical statements (let me starts with definitions cuz I can start grouping them by common definienda)?
- which architecture/ pretraining strategy help to capture similarity of mathematical statements ?
  

## Step 0. 

[00.ExtractProofWiki.ipynb](00.ExtractProofWiki.ipynb) collects and parses [disambiguitaion pages](https://proofwiki.org/wiki/Category:Disambiguation_Pages) in ProofWiki.

I store disambiguation page titles, ambiguous terms, definitions in latex source, definition page titles, the categories of each definition, and definitions in plain text
in [parsed_disambiguation_list_without===.csv](parsed_disambiguation_list_without===.csv).

[01.Proofwiki_vs_ArXiv_Def.ipynb](01.Proofwiki_vs_ArXiv_Def.ipynb) shows the overlap of ambiguous terms in Proofwiki and in arXiv papers.
TODO: extract more definitions from arXiv papers.

## Step 1.

Unsupervised: how different are these proofwiki definitions ?

[10.ExtractHypernyms.ipynb](10.ExtractHypernyms.ipynb) shows that Word Class Lattice classifier can extract very few hypernyms of mathematical definienda. Most WCL-identifiable definitions match " is a " pattern.


## Step 2. 
Supervised NSP-like or NLI-like sentence pair classifier
Related work: [GLADIS](https://github.com/tigerchen52/GLADIS)
