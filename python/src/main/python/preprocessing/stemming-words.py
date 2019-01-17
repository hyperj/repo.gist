#!/usr/bin/python3

'''
Stemming Words
'''

# Load library
from nltk.stem.porter import PorterStemmer

# Create word tokens
tokenized_words = ['i', 'am', 'humbled', 'by', 'this', 'traditional', 'meeting']

# Create stemmer
porter = PorterStemmer()

# Apply stemmer
res = [porter.stem(word) for word in tokenized_words]
print(res)
