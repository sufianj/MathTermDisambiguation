# Identification/disambiguation of mathematical statements (definition) by semantic similarity

**Problem formulation**: given two statements, the system shall tell whether these statements are equivalent/ different

**Research question**: 

- what is needed for inter-document disambiguation of mathematical statements (let me starts with definitions cuz we can start grouping them by common definienda)?
- which architecture/ pretraining strategy help to capture similarity of mathematical statements ?
  

## Step 0. 

[00.ExtractProofWiki.ipynb](00.ExtractProofWiki.ipynb) collects and parses [disambiguitaion pages](https://proofwiki.org/wiki/Category:Disambiguation_Pages) in ProofWiki.

We store disambiguation page titles, ambiguous terms, definitions in latex source, definition page titles, the categories of each definition, and definitions in plain text
in [parsed_disambiguation_list_without===.csv](parsed_disambiguation_list_without===.csv).

[01.Proofwiki_vs_ArXiv_Def.ipynb](01.Proofwiki_vs_ArXiv_Def.ipynb) shows the overlap of ambiguous terms in Proofwiki and in arXiv papers.
TODO: extract more definitions from arXiv papers.

## Step 1.

Unsupervised: how different are these proofwiki definitions ?

[10.ExtractHypernyms.ipynb](10.ExtractHypernyms.ipynb) shows that Word Class Lattice classifier can extract very few hypernyms of mathematical definienda. Most WCL-identifiable definitions match " is a " pattern.


## Step 2. 
[20.SentencePairClassifier.ipynb](20.SentencePairClassifier.ipynb) shows our study about how pre-trained language models can help to differentiate mathematical definitions.

### Approach 1. Supervised NSP-like classifer
Inspired by [GLADIS](https://github.com/tigerchen52/GLADIS) we build a supervised NSP-like sentence pair classifier to link definitions to their page titles in Proofwiki. Every pair of a definition and a title (term,domain) with the matching ambiguous term in proofwiki constitutes an input to the Next Sentence Prediction (NSP) task. 
The language model produces a score for each candidate and we select the one with the highest score as the final predicted output.


### Approach 2. Prediction based on cosine similarity
Motivated to make a faster solution, we also explore the sentence embeddings of the definitions and titles. we calculate sentence embeddings for the definition and each candidate title with the matching ambiguous term in proofwiki, and we select the title with the highest cosine similarity to the embedding of the definition as the final predicted output.

TODO: why MiniLM works well for this task??? how was it made ??

TODO: fine-tune sentence transformers for text similarity.

### Evaluation:

Train set: [df_flattened_train_disam_list.csv](data/SP_CLS/df_flattened_train_disam_list.csv) ~ 275 ambiguous terms and 1436 (definition, title) pairs

Test set: [df_flattened_test_disam_list.csv](data/SP_CLS/df_flattened_test_disam_list.csv) ~ 68 ambiguous terms and 433 (definition, title) pairs

To make this task difficult, we split the Train:Test sets based on the ambiguous terms. 

We evalute both approaches on train set and test set. 

(updating...)Results are in [data/res/](SentenceDisambiguation/data/res), the notebook, and [NSP_logs.txt](NSP_logs.txt). 

(updating...)Prediction ouputs: 

https://docs.google.com/spreadsheets/d/1seEHjZzorcxomuc7S35_1CDiEMsODZv4yRCN3CLPH5c/edit?usp=sharing
We evalute both approaches on train set and test set. 

TODO: evalute the generalizability of the different approaches on definitions extracted from papers, if the results are also cool, make a demo...
