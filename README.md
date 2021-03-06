Amazon Customer Review Analysis
===============================
This is part of a Web Data Mining assignment. The Amazon Customer Review Analysis project is aimed to extract and analyze customer reviews of products off of the web-based shopping service Amazon.

This project has several parts.

Web Crawler
-----------
First there's the Web Crawler. This fetches customer reviews from an Amazon Product URL. The command-line arguments take any number of URLs and crawls through the review pages and pulls all the reviews and relevant information pertaining to those reviews into JSONObjects which are then stored in a text document.

PreAnalysis
-----------
Secondly there's a small code that extracts all the JSONObjects from the text documents and puts all the text reviews into one giant review. This is later analyzed by the Stanford NLP Core's SPIED which extracts terms in a category based on seed terms.

Analysis
--------
In our implementation we used a few seed words we thought were relevant to terms in the reviews that only describe the product. We did this manually. A better implementation would be to use the gold-standard reviews to extract these seeds which would then be used to more accurately extract those terms in the reviews that only describe the product.

The results are in the directory:

\ACRA\stanford-corenlp-full-2014-10-31\SPIEDPatternsout\useNERRestriction

where each products cumulative reviews' extract terms are in a folder labelled accordingly to their product number. These were run from the command-line which was based on the example as we had difficulty implementing the classes to extract those terms.

We used the Stanford NLP Core which can be found at:
http://nlp.stanford.edu/software/stanford-corenlp-full-2014-10-31.zip

PostAnalysis
------------
Finally the last part of this project is a simple counter which counts how many sentences are in each review for each product. Then it counts how many of those sentences contains a term from the list of terms extracted during analysis.