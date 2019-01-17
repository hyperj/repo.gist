#!/usr/bin/python3

'''
Bag Of Words
'''

# Load library
import numpy as np
from sklearn.feature_extraction.text import CountVectorizer
import pandas as pd

# Create text
text_data = np.array(['I love Brazil. Brazil!',
                      'Sweden is best',
                      'Germany beats both'])

# Create the bag of words feature matrix
count = CountVectorizer()
bag_of_words = count.fit_transform(text_data)

# Show feature matrix
bag_of_words.toarray()

# Get feature names
feature_names = count.get_feature_names()

# View feature names
print(feature_names)

# Create data frame
df = pd.DataFrame(bag_of_words.toarray(), columns=feature_names)
print(df)