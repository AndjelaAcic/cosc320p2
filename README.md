
# Keyword replacement in a corpus

**Note**: Our dataset was around 270MB which was too large to post on GitHub. Please see the Datasets section and download the dataset with tweets and put it in the each project folder before running it.

In this project we developed 2 algorithms for replacement of abbreviations in a string (tweet) with their meaning.

This repository contains 4 folders:

* cosc320project
    -  Effiecient solution for the problem using a HashMap with overall time complexity of O(n) where n is the length of dataset with the tweets. It takes input dataset in csv format and writes a new csv file with modified tweets.
* cosc320project2
    - Brute force solution for the problem with overall time complexity of O(kn) where k is the length of dataset of abbreviations and n is the length of dataset of tweets. It takes input dataset in csv format and writes a new csv file with modified tweets.

* cosc320projecttest1
    - This folder is used to test solution from cosc320project folder with input from command line.
* cosc320projecttest2
     - This folder is used to test solution from cosc320project2 folder with input from command line.

## Datasets
The dataset utilized in this project is used from Kaggle, this dataset contains a random set of tweets from various topics and users collected at a sample size of 1.6 million tweets. As the dataset consists of various topics and users the algorithm can be utilized to the best of its potential and have reliable data to work with. Due to the dataset being large to this amount we could also push the limits of the algorithm and the code to test the efficiency and have a large enough dataset to plot the graph using the Big O notation the link for the dataset is https://www.kaggle.com/datasets/kazanova/sentiment140?resource=download. 
In addition, we utilized another dataset which consisted of a list of abbreviations and their meanings which. The dataset is about 300 different meanings and abbreviations and can be easily updated. The link to the dataset is https://www.kaggle.com/datasets/ckapre51/common-abbreviations-and-short-forms-with-meanings.

## Implementation

### Effiecient solution
To change all abbreviations to their meaning equivalent, we first loaded 2 datasets into the program. For each dataset, we have also implemented a class to hold the data and obey the principles of object-oriented programming. Class "Tweet" has a field of type string that holds the text of the tweet, and class "Abbreviation" has 2 String fields that hold the word and its meaning. Both classes have appropriate constructors and getters and setters. Since data sets are CSV files, we used OpenCSV java library to read from them and write to a new CSV file later. Then we build a hashmap of abbreviations where the key is the abbreviation and the value is the meaning of it. After that, we loop through all the tweets that we loaded and called the function "swapWordsForMeaning" which splits a tweet into separate words, loops through the array of words, and for each one checks if the word is in the hashmap of abbreviations. If it is, we swap it with the meaning from the hashmap, if not, the loop continues going. New tweets need to be made out of strings and not arrays of strings, so we call a function "makeStringOutOfWords" to make that conversion using StringBuilder. After that, we printed out a sample of 10 tweets to test the program and wrote all new tweets in the CSV file.
To test the time it takes to run our program for different input sizes, we used a system function to get time in milliseconds before and after running our algorithm and subtracted start time from finish time. We then manually changed how many tweets is "readTwFromCSV" function reading from the dataset and reported values for input sizes varying from 10 to 1.6 million and plotted the graph using an Excel sheet. 

### Brute force
To change all abbreviations to their meaning equivalent, we first loaded 2 datasets into the program. For each dataset, we have also implemented a class to hold the data and obey the principles of object-oriented programming. Class "Tweet" has a field of type string that holds the text of the tweet, and class "Abbreviation" has 2 String fields that hold the word and its meaning. Both classes have appropriate constructors and getters and setters. Since data sets are CSV files, we used OpenCSV java library to read from them and write to a new CSV file later. From there, we looped through all the tweets, separating each one into a string of words. We used a delimiter to split the string and account for spaces and possible punctuation signs like commas. For each the splitted tweet, we called the function "swapWordsForMeaning" to change the abbreviation into meanings. This function loops through all the words in the tweet and for each one, it loops through the list of abbreviations and checks if any of them are equal. If they are, it swaps the word for its meaning. At the end, it returns an array of words where abbreviations are swapped. We then call the function "makeStringOutOfWords" on this returned array to get the array back into the shape of a string using StringBuilder. After that, we printed out a sample of 10 tweets to test the program and wrote all new tweets in the CSV file.
To test the time it takes to run our program for different input sizes, we used a system function to get time in milliseconds before and after running our algorithm and subtracted start time from finish time. We then manually changed how many tweets is "readTwFromCSV" function reading from the dataset and reported values for input sizes varying from 10 to 1.6 million and plotted the graph using an Excel sheet. 

##Results
Our algorithm was determined to run in O(kn) time, where k is the length of the abbreviations list and n is the number of tweets analyzed. Since we used a brute force method, the result of our algorithm is approximately what we expected. The algorithm is designed to look through the entire length of the abbreviation list and the entire length of each tweet (up to 280 characters) therefore, this algorithm takes much longer to analyze the samples given. For example, at n = 500000, the time to execute this code takes nearly 8x longer than our original algorithm. 
![alt text](http://url/to/img.png)

