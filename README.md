# Identification/disambiguation of mathematical statements (definition) by semantic similarity

**problem formulation**: given two statements, the system shall tell whether these statements are equivalent/ different

**Research question**: 

- what is needed for inter-document disambiguation of mathematical statements (let me starts with definitions cuz I can start grouping them by common definienda)?
- which architecture/ pretraining strategy help to capture similarity of mathematical statements ?
  

## Step 0. 

[00.ExtractProofWiki.ipynb](00.ExtractProofWiki.ipynb) collects and parses [disambiguitaion pages](https://proofwiki.org/wiki/Category:Disambiguation_Pages) in ProofWiki.

I store disambiguation page titles, ambiguous terms, definitions in latex source, definition page titles, the categories of each definition, and definitions in plain text
in [parsed_disambiguation_list_without===.csv](parsed_disambiguation_list_without===.csv).

## Step 1.

A. Unsupervised: how different are these proofwiki definitions ?

B. Supervised NSP-like or NLI-like sentence pair classifier
Related work: [GLADIS](https://github.com/tigerchen52/GLADIS)
