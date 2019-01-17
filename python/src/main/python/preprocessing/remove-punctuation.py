#!/usr/bin/python3

'''
Remove Punctuation
'''

# Load libraries
import string

# Create text
text_data = ['Hi!!!! I. Love. This. Song....',
             '10000% Agree!!!! #LoveIT',
             'Right?!?!']


# Create function using string.punctuation to remove all punctuation
def remove_punctuation(sentence: str) -> str:
    return sentence.translate(str.maketrans('', '', string.punctuation))


# Apply function
print([remove_punctuation(sentence) for sentence in text_data])
