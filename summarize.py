# summarize.py
import openai
from transformers import pipeline
import sys

# Initialize the summarization model
summarizer = pipeline("summarization", model="sshleifer/distilbart-cnn-12-6")

# Get the blog content passed from Java (via command line argument)
blog_content = sys.argv[1]

# Print the input length for debugging
input_length = len(blog_content.split())
print(f"Input length (in words): {input_length}")

# Adjust max_length and min_length based on the input length
max_length = min(50, input_length // 2)  # Set max_length as half of input length or 50 (whichever is smaller)
min_length = min(20, max_length - 1)   # min_length must be smaller than max_length

# Generate summary
summary = summarizer(blog_content, max_length=max_length, min_length=min_length)

# Print the summary to be captured by Java
print(summary[0]['summary_text'])



