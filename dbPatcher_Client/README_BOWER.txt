//D:\workspace_web_dev_workshop\dbedit_client>npm -version
//3.8.9
//D:\workspace_web_dev_workshop\dbedit_client>bower -version
//1.7.9
====================================================================================================================================

http://stackoverflow.com/questions/23939073/uninstalling-bower-installed-with-a-different-version-of-npm

I have an Ubuntu 14.04 system. I had installed npm and I installed bower using

sudo npm install -g bower
Some time after that I realized that I was using the node and npm that came fromt he ubuntu repositories, so to use the latest one, I added the repository:

$ sudo add-apt-repository ppa:chris-lea/node.js
and update the packages.

Now I was trying to uninstall bower from the system, but guess what? I get this:

$ sudo npm remove -g bower
npm WARN uninstall not installed in /usr/lib/node_modules: "bower"
So, wait, what? It cant find Bower? So I try to make

which bower
and the output here is:

/usr/local/bin/bower
I dont really understand what is going on. I suspect that I have a problem with different versions of npm and nodejs not playing nice together, since I installed bower with a different version of npm that the one I have installed now. Any idea of how I could uninstall Bower now? (Or at least come back to a clean state, so that I can begin from the beginning?)

node.js ubuntu npm bower version-compatibility
shareedit
edited May 30 '14 at 7:46
asked May 29 '14 at 17:14

Dbugger
5,4891049112
  	 	
Just a blind guess, you installed with sudo so directories may differ. And/or your current user can't search the sudo-path where it was installed. – user568109 May 30 '14 at 5:16
  	 	
just a note, in windows you can use where bower – Raphael Isidro Apr 18 '15 at 1:37
add a comment
1 Answer
active oldest votes
up vote
13
down vote
accepted
sudo npm uninstall bower -g --prefix=/usr/local


====================================================================================================================================

http://stackoverflow.com/questions/26969712/how-do-install-a-specific-version-of-bower-with-npm


I would like to install version 1.3.9 of bower with NPM. I have tried the following:

sudo npm uninstall -g bower
(successful)

sudo npm install -g bower#1.3.9
Even though bower re-installs correctly the version remains at the latest version, 1.3.12, not at the desired 1.3.9

npm version bower
shareedit
asked Nov 17 '14 at 9:43

Carl
718413
add a comment
1 Answer
active oldest votes
up vote
4
down vote
accepted
You need to use the @ sign instead of #:

npm install -g bower@1.3.9
For more details see npm-install