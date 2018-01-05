LoL Profile
==============

LoL Profile is a simple android application that shows you information
about different champions matchup between them.

You have just to click or search in your main champion, choose your 
ELO - default is SILVER - ELO HELL :_(, and see the 6 best and worst matchup 
champions. 
 
Also, for those champions that have various positions, you can choose whatever
you play.

Check this app out on:
- [Google play](https://play.google.com/store/apps/details?id=oob.lolprofile)
- [Youtube](https://youtu.be/bMbBYzfjqn8)

Any comment would be welcome.

Enjoy!
 
# Usage

Clone the repo, rename res/values/keys_mock.xml to res/values/keys.xml 
and update your API keys from Riot and Champion.gg API.

Note: current version does not need Riot Backend API key, you can 
just leave it blank.

# Developer notes

I have tried to update myself to last Android developing version and 
use different famous Google and third party libraries, applying concepts 
[Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html). 

Some technologies/tools used are:
- [Android Studio](https://developer.android.com/studio/index.html) 3.0.1
- Java language
- [Dagger2](https://github.com/google/dagger) for Dependency Injection
- [Butter Knife](https://github.com/JakeWharton/butterknife) for binding UI elements
- [Retrofit2](https://github.com/square/retrofit) with [okHttp3](https://github.com/square/okhttp) as Http client and requests logging
- [Picasso](https://github.com/square/picasso) for loading Image resources
- [Timber](https://github.com/JakeWharton/timber) for logging
- [Realm](https://github.com/realm/realm-java) as local DB management
- [RoundedImageView](https://github.com/vinc3m1/RoundedImageView) from [Vince Mi](https://github.com/vinc3m1) (Nice work dude!)

![image](https://user-images.githubusercontent.com/11597234/33989292-3f18e720-e0c7-11e7-959f-1ce5b1e4a63c.png)

# References

[1]<a href="https://google.github.io/dagger/" target="_blank"> 
    Dagger 2 documentation from Google
    </a>
    <br/>
[2]<a href="https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882" target="_blank"> 
    Clean Code at Amazon 
    </a>
    <a href="https://en.wikipedia.org/wiki/Robert_Cecil_Martin" target="_blank">
    Author: Robert Cecil Martin
    </a>
    <br/>
[3]<a href="https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164" target="_blank"> 
    Clean Architecture at Amazon 
    </a>
    <a href="https://en.wikipedia.org/wiki/Robert_Cecil_Martin" target="_blank">
    Author: Robert Cecil Martin
    </a>
    <br/>
[4]<a href="https://developer.android.com" target="_blank"> 
    Android documentation from Google
    </a>
    <br/>