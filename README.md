# Identification/disambiguation of mathematical definitions by semantic similarity
This repository contains the data, code sources, and experimental results of our two publications:
- A preliminary study on : [Towards Disambiguation of Mathematical Terms based on Semantic Representations](https://hal.science/hal-05028993v1) (V1).
- A full paper on: [MathD2: Towards Disambiguation of Mathematical Terms](.) (V2)


## Problem formulation

Given two definitions, the system shall tell whether these definitions are equivalent/ different

## Research questions

- How well can contextualized word embeddings help the disambiguation of mathematical terms? (V1 & V2)
- Which architecture/ pretraining strategy helps to capture the similarity of mathematical statements? (V1 & V2)
- How well do models trained in the preceding learning paradigm of pre-train + fine-tune compare with state-of-the-art Instruct Large Language Models? (V2)
  

## Step 0. Making Proofwiki disambiguation pages as a ground truth

[00.ExtractProofWiki.ipynb](00.ExtractProofWiki.ipynb) collects and parses [disambiguitaion pages](https://proofwiki.org/wiki/Category:Disambiguation_Pages) in ProofWiki.

We store disambiguation page titles, ambiguous terms, definitions in the LaTeX source, definition page titles, the categories of each definition, and definitions in plain text
in [parsed_disambiguation_list_without===.csv](parsed_disambiguation_list_without===.csv).

[01.Proofwiki_vs_ArXiv_Def.ipynb](01.Proofwiki_vs_ArXiv_Def.ipynb) shows the overlap of ambiguous terms in Proofwiki and in arXiv papers.
TODO: extract more definitions from arXiv papers.

## Step 1. Syntactic analysis

Unsupervised: How different are these proofwiki definitions?

[10.ExtractHypernyms.ipynb](10.ExtractHypernyms.ipynb) shows that the Word Class Lattice classifier can extract very few hypernyms of mathematical definienda. Most WCL-identifiable definitions match the" is a " pattern.


## Step 2. Linking different definitions to different entities
[20.SentencePairClassifier.ipynb](20.SentencePairClassifier.ipynb) shows our study about how pre-trained language models can help to differentiate mathematical definitions.

### Approach 1. Supervised NSP-like classifier
Inspired by [GLADIS](https://github.com/tigerchen52/GLADIS) we build a supervised NSP-like sentence pair classifier to link definitions to their page titles in Proofwiki. Every pair of a definition and a title (term,domain) with the matching ambiguous term in proofwiki constitutes an input to the Next Sentence Prediction (NSP) task. 
The language model produces a score for each candidate, and we select the one with the highest score as the final predicted output.


### Approach 2. Prediction based on cosine similarity
Motivated to make a faster solution, we also explore the sentence embeddings of the definitions and titles. We calculate sentence embeddings for the definition and each candidate title with the matching ambiguous term in ProofWiki, and we select the title with the highest cosine similarity to the embedding of the definition as the final predicted output.

### Approach 3. Zero-shot LLM (7B models)
[23. LLM test.ipynb](23.%20LLM%20tests.ipynb)

[24. LLM train.ipynb](24.%20LLM%20tests%20Mistralv3.ipynb)

[25. LLM tests Llama.ipynb](25.%20LLM%20tests%20Llama.ipynb)  
### Evaluation:


#### V1: 
Train set: [df_flattened_train_disam_list.csv](data/SP_CLS_old/df_flattened_train_disam_list.csv) ~ 275 ambiguous terms and 1436 (definition, title) pairs

Test set: [df_flattened_test_disam_list.csv](data/SP_CLS_old/df_flattened_test_disam_list.csv) ~ 68 ambiguous terms and 433 (definition, title) pairs

To make this task difficult, we split the Train: Test sets based on the ambiguous terms. 

We evaluate both approaches on the training set and the test set. 

Results are in [data/res-V1/](data/res-V1), the notebook, and [NSP_logs.txt](NSP_logs.txt). 

We evaluate both approaches on the training set and the test set. 

Further question: Does a better NSP model make more similar embeddings for (definition, title) pairs? No.

#### V2:
Test over (1) unseen ambiguous terms and (2) new candidate titles for seen ambiguous terms.

Added LLM-based approaches.

Pulled newly added items in Proofwiki.

Train set: [df_flattened_train_disam_list.csv](data/SP_CLS/df_flattened_train_disam_list.csv) ~ 297 ambiguous terms and 1158 (definition, title) pairs

Test set (1): [df_flattened_test_new_term_disam_list.csv](ddata/SP_CLS/df_flattened_test_new_term_disam_list.csv) ~ 68 ambiguous terms and 364 (definition, title) pairs

Test set (2) : [df_flattened_test_new_candi_disam_list.csv](ddata/SP_CLS/df_flattened_test_new_candi_disam_list.csv) ~ 68 ambiguous terms and 462 (definition, title) pairs

Preliminary results : [data/res](data/res)

#### V2.1 :
Adding 5-fold cross-validation.

Data: [data/SP_CLS-5fold](data/SP_CLS-5fold)

Results (presented in the full paper): [data/res-5fold](data/res-5fold)

#### V3 : Future works

TODO: Evaluate the generalizability of the different approaches to definitions extracted from papers. If the results are also cool, make a demo.

## Citation

If you find our work useful and would like to cite it, please use the following BibTeX entry:

V1:
```bibtex
@inproceedings{mathd2v1,
  TITLE = {{Towards Disambiguation of Mathematical Terms based on Semantic Representations}},
  AUTHOR = {Jiang, Shufan and Tan, Mary Ann and Sack, Harald},
  URL = {https://hal.science/hal-05028993},
  BOOKTITLE = {{SCOLIA 2025 - First International Workshop on Scholarly Information Access}},
  ADDRESS = {Lucca, Italy},
  YEAR = {2025},
  MONTH = Apr,
}
```

V2 (in preparation): 
```bibtex
@inproceedings{mathd2v2,
  TITLE = {{MathD2: Towards Disambiguation of Mathematical Terms}},
  AUTHOR = {Jiang, Shufan and Tan, Mary Ann and Sack, Harald},
  BOOKTITLE = {{Fifth Workshop on Scholarly Document Processing at ACL 2025}},
  ADDRESS = {Vienna, Austria},
  YEAR = {2025},
  MONTH = Aug,
}
```
