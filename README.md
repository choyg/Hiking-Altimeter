# Hiking Altimeter
<a href='https://play.google.com/store/apps/details?id=org.testb.java.altimeter&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height="80px"/></a>



#### Android Altimeter featuring a clean interface and easy calibration

## Project Details
Hiking Altimeter is a sandbox for applying what I am currently learning about in Android, specifically certain best practices. Most recently, I have been iterating over different app architectures going from purely activty and fragments to having a singleton source of truth calculator, and finally exploring a more reactive architecture using RxJava.

This is accomplished by subscribing to multiple observables in the presenter and updating the view as necessary to accomodate changes in preferences and air pressure. One major advantage is a clear delegation of each task so that the presenter does not have too much responsibility.

Heavily inspired by Android Clean Architecture as demonstrated [here](https://github.com/android10/Android-CleanArchitecture) and MVP on UI layer.

## Coming soon...
+ Altitude tracking and fancy graphs
+ UI Refresh
+ Additional UI customization
+ Optional notifications (altitude alert)

##License
+ [MIT License](/LICENSE)
