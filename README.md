# FunnyBot
### Introduction
A twitter bot that tweets in a given time intervals.
## Description
A twitter that tweets in a given time intervals. Tweeting a starting message and an ending message. Also tweeting in a given time interval, example tweeting after every 1 hour. I made this twitter bot as a hobby and to see how to use a bot in twitter. I will not be responsible for how you use this software. Please follow [twitter policy](https://developer.twitter.com/en/developer-terms/policy.html) for using this software, more information can be found in [twitter developer page](https://developer.twitter.com/).
***
## Prerequisites
### Netbeans
Latest [stable](https://github.com/deadlykam/FunnyBot/releases) Netbeans version is _NetBeans IDE 8.2 (Build 201609300101) NetBeans IDE is updated to version NetBeans 8.2 Patch 2_. Install the stable's version [Netbeans from their older version listing](https://netbeans.org/community/releases/82/). Or you could download the [latest version of the Netbeans](https://netbeans.apache.org/download/index.html) but that may cause problems.
### Java
Latest [stable](https://github.com/deadlykam/FunnyBot/releases) Java version is _1.8.0_144; Java HotSpot(TM) 64-Bit Server VM 25.144-b01_ and Runtime version is _Java(TM) SE Runtime Environment 1.8.0_144-b01_. [Download latest version of Java](https://www.java.com/en/download/).
### Twitter4j
Latest [stable](https://github.com/deadlykam/FunnyBot/releases) Twitter4j version is _twitter4j-4.0.7_. [Dowload twitter4j from here](http://twitter4j.org/en/index.html).
***
## Installation
After following the [Prerequisites](#prerequisites), download the latest stable in Netbeans. If you want to just use the software then just Go to _Funnybot/Funnybot/dist_ and open the _FunnyBot.jar_. If you want to make modifications then open the project in Netbeans and code away.
***
## Usage
Start the _FunnyBot.jar_ in _Funnybot/Funnybot/dist_ folder.
_image here_

#### Set Credentials
After starting the software you will need to provide your twitter developer's consumer key, consumer secret, access token and access token secret. You can find your twitter credentials in your [twitter developer's page](https://developer.twitter.com/). In the FunnyBot software open the credentials settings from _Edit/Credentials_ menu. Here write down the consumer key, consumer secret, access token and access token secret. After giving the credentials press the _Set_ button and close the _Credential Setting_. In the main menu the credential red light will turn green indicating that credentials have been given.

![Opening Credential Menu](https://i.imgur.com/IHg1tUd.jpg)

*Figure1: Opening Credential Menu*

![Credential Menu](https://i.imgur.com/lLIZqnS.jpg)

*Figure2: Credential Menu*

![Credentials Successfully Setup](https://i.imgur.com/oSHgVw4.jpg)

*Figure3: Credentials Successfully Setup*

#### Set Tweets
Next you need to set the tweets that you want the bot to send. Open the tweet menu from _Edit/Tweets_. In the tweet menu give the _Start Message_ and _End Message_ tweets. Then type the tweet in _Enter Tweet_ box. once you are satisfied with the tweet then press the _Add Tweet_ button to add the tweet to the bot tweet listing. If you want you can add as much tweet as you want. All the tweets add will be shown on the table below. Once you are done add the tweets press the _Set_ button and close the menu. In the main menu the tweets red light will turn green indicating that tweets have been given.
_image here_
_image here_
_image here_

#### Set Timer
Now you need to set the time cycle for your tweets. The time cycle will determine after how long will the bot send a tweet. You must make sure you have enough tweets that can be sent in a day, if not then the bot won't send any more tweets and will be waiting for the next day for refreshing the tweets.
_tw = tweet, tc = time cycle_

##### Best example:
###### Bot Setting -> _tw > tc_
If you set more than 24 tweets and give time cycle to 1 hour then the tweet posting rate will be 1 tweet per hour which means some of the tweets will be tweeted with in 24 hours in random order. After 24 hours the tweets will refresh meaning the bot will have all the tweets back again. This ensures a little bit of uniqueness to the bot because it have lot of tweets to choose from in a day and reduces repetition of the tweets.

##### Good example:
###### Bot Setting -> _1 : 1_
If you set 24 tweets and give time cycle to 1 hour then the tweet posting rate will be 1 tweet per hour which means all tweets will be tweeted with in 24 hours in random order. After 24 hours the tweets will refresh meaning the bot will have tweets to tweet again. This will not make the bot look unique because it will be repeating the tweets again.

##### Bad example:
###### Bot Setting -> _tw < tc_
If you set less than 24 tweets and give time cycle to 1 hour then the tweeting posting rate will be 1 tweet per hour which means the bot will not have enough tweets to tweet with in 24 hours. When this happens the bot will wait for the tweets to be refreshed after 24 hours so that it can use the tweets again. This will not make the bot look unique because it will be repeating the tweets again.

After understanding how the _tweet/time cycle_ works now you need to set the time cycle. Open timer menu from _Edit/Set Timer_. Give the the time cycle here and press the _Set_ button. In the main menu the timer red light will turn green indicating that timer have been given.
_image here_
_image here_
_image here_

#### Set Log Path _(Optional)_
This part is optional. The default path on the log files is in the System's document location and the files are called _Fail.txt_ and _Success.txt_. _Fail.txt_ contains all the events that have been failed and _Success.txt_ contains all the events that were successful. You can also open the file location from the software from _File/Open Log_, this will open a folder where these files are located. 

If you want you can change the Log path, to do so you need to open _Edit/Log Path_ and choose the folder destination for the Log path and press the _Open_ button.
_image here_
_image here_
_image here_

#### Saving/Loading Your Setting
Once you are happy with your settings you can save them and use them again in the future without following all the above steps again. To save your settings go to _File/Save_, choose a destination and a name and then press the _Save_ button. This will save your settings.
_image here_
_image here_

To load your settings go to _File/Open_, go to the destination you have saved your file, select the _.funnybot_ file and press the _Open_ button. This will load all of the settings/information that you gave previously.
_image here_
_image here_

#### Starting The Bot
Once you have followed all of the above steps then now finally is the time to start the bot. To start the bot go to _Bot/Start_. This will turn the Start light from red to green, indicating that the bot has started. All the tweets that have been tweeted can be seen in the table in the main menu. This table will also show if there are any errors for example if the credentials given are wrong. The bot will now tweet in the given intervals and will only stop when you stop the bot.
_image here_
_image here_

To stop the bot go to _Bot/Stop_. This will stop the bot from tweeting. The Start light will turn red from green, indicating that the bot has been turned off.
_image here_
_image here_

## Versioning
The project uses [Semantic Versioning](https://semver.org/). Available versions can be seen in [tags on this repository]((https://github.com/deadlykam/FunnyBot/releases).

## Credits
- [Twitter4j](https://github.com/Twitter4J/Twitter4J) - Made it easy to use the twitter API.

## Authors
- Syed Shaiyan Kamran Waliullah - [DeadlyKam](https://github.com/deadlykam)

## License
Will be given
