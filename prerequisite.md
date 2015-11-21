## Steps

* You must have a (predix)[www.predix.io/registration] account.
* Download CF Cli from [here](https://github.com/cloudfoundry/cli#downloads).
* For *NIX users, copy ``` cf  ``` to one your ``` $PATH ``` locations. At the end of this excercise, when you run the command ```  which cf ``` , you should get some output 
* Login to Predix through cf-cli, Checkout ``` cf help login ``` for available options
    ``` cf login -a https://api.system.aws-usw02-pr.ice.predix.io -u <predix_account_email>  ```
* To Save time have an [unix alias](https://en.wikipedia.org/wiki/Alias_(command))/[windows alias](http://darkforge.blogspot.com/2010/08/permanent-windows-command-line-aliases.html?_sm_au_=iVVZtT1t0ptkDqrM) for this
*  Once logged in you should see something like
  ```

Setting api endpoint to https://api.system.aws-usw02-pr.ice.predix.io...
OK

API endpoint:   https://api.system.aws-usw02-pr.ice.predix.io (API version: 2.28.0)

Not logged in. Use 'cf login' to log in.

API endpoint: https://api.system.aws-usw02-pr.ice.predix.io

Authenticating...
OK

Targeted org *******@**.com

Targeted space dev

```
* For a starter, I would suggest that you create a new [space](https://docs.cloudfoundry.org/concepts/roles.html#spaces) in your [org](https://docs.cloudfoundry.org/concepts/roles.html#orgs),
```
cf create-space predix-sample
```
* It should show some output like
``` 
cf create-space predix-sample
Creating space predix-sample in org *******@**.com as *******@**.com...
OK
Assigning role SpaceManager to user *******@**.com in org *******@**.com / space predix-sample as *******@**.com...
OK
Assigning role SpaceDeveloper to user *******@**.com in org *******@**.com / space predix-sample as *******@**.com...

````
* Now target that space with the command ``` cf target -s predix-sample ``` , To get more info on target command , try running ``` cf help target ```